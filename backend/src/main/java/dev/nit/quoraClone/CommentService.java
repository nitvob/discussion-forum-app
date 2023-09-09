package dev.nit.quoraClone;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private MongoTemplate template;

    // service method to create a new comment directly under a post or comment
    public Comment createComment(String commentBody, String parentPostId, String parentCommentId) {
        return template.save(new Comment(commentBody, parentPostId, parentCommentId));
    }

    // service method to update a comment with a new comment under it
    public Comment updateCommentWithComment(String commentBody, String commentId) {
        // query the comment by its id
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));

        Comment updatedComment = null;

        // check if comment exists in db
        if (template.exists(query, Comment.class)) {
            // create a new sub comment in db
            Comment newComment = createComment(commentBody, null, commentId);

            // update comment's comments field with new comment
            Update update = new Update().push("comments", newComment);
            // tell mongo to return comment AFTER update
            FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
            // find and update and return updated comment
            updatedComment = template.findAndModify(query, update, options, Comment.class);
        }

        return updatedComment;
    }

    // service method to delete a comment
    public boolean deleteComment(String commentId) {
        // find and remove comment
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));
        Comment comment = template.findAndRemove(query, Comment.class);

        boolean deleted = false;

        // check if comment exists in db
        if (comment != null) {
            // remove sub comments
            List<Comment> subComments = comment.getComments();
            for (Comment subComment : subComments) {
                deleteComment(subComment.getId().toHexString());
            }

            // delete ref to comment from parent post or comment comment list

            // get parentPostId and parentCommentId
            ObjectId parentPostId = comment.getParentPostId();
            ObjectId parentCommentId = comment.getParentCommentId();

            // create a query and update for either the parent post or comment
            query = new Query();
            Update update = new Update().pull("comments", comment);

            // check if this comment is directly under a post
            if (parentPostId != null) {
                // the query criteria should look for the parent post
                query.addCriteria(Criteria.where("_id").is(parentPostId));
                // now query and update
                template.updateFirst(query, update, Post.class);
            } else if (parentCommentId != null) {
                // the query criteria should look for the parent comment
                query.addCriteria(Criteria.where("_id").is(parentCommentId));
                // now query and update
                template.updateFirst(query, update, Comment.class);
            }

            deleted = true;
        }

        return deleted;
    }
}
