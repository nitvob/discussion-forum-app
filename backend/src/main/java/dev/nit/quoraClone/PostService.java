package dev.nit.quoraClone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private MongoTemplate template;

    @Autowired
    private CommentService commentService;

    // service method to get a single post
    public Post getPost(String postId) {
        // find the post by id
        return template.findById(postId, Post.class);
    }

    // service method to get all post titles and ids
    public List<Post> getAllPosts() {
        // create query to only retrieve title and id fields
        Query query = new Query();
        query.fields().include("title");

        // get all posts according to query defn
        return template.find(query, Post.class);
    }

    // service method to get posts by topic
    public List<Post> getPostsByTopics(List<String> topics) {
        // create query to only retreive title and id fields
        Query query = new Query();
        query.fields().include("title");

        // add criteria to query to get objects whose topics field contains at
        // least one element from topics parameter
        query.addCriteria(Criteria.where("topics").in(topics));
        return template.find(query, Post.class);
    }

    // service method to save a new post in mongo
    public Post createPost(String title, String description, List<String> topics) {
        return template.save(new Post(title, description, topics));
    }

    // service method to update a post by creating a new comment under it
    public Post updatePostWithComment(String commentBody, String postId) {
        // query the post by its id
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(postId));

        Post updatedPost = null;

        // check if post exists in db
        if (template.exists(query, Post.class)) {
            // create the new comment in db
            Comment newComment = commentService.createComment(commentBody, postId, null);

            // update post's comments field with new post
            Update update = new Update().push("comments", newComment);
            // tell mongo to return post AFTER update
            FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
            // find and modify and return updated post
            updatedPost = template.findAndModify(query, update, options, Post.class);
        }

        return updatedPost;
    }

    // service method to delete a post
    public boolean deletePost(String postId) {
        // query the post by its id and remove it
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(postId));
        Post post = template.findAndRemove(query, Post.class);

        boolean deleted = false;

        // check if post even existed
        if (post != null) {
            // delete post's comments
            List<Comment> comments = post.getComments();
            for (Comment comment : comments) {
                commentService.deleteComment(comment.getId().toHexString());
            }

            deleted = true;
        }

        return deleted;
    }
}
