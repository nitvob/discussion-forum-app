package dev.nit.quoraClone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    private record UpdateCommentWithCommentPayload(String commentBody) {
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateCommentWithComment(@RequestBody UpdateCommentWithCommentPayload payload,
            @PathVariable String commentId) {
        // destructure request body data
        String commentBody = payload.commentBody;

        // update comment
        Comment comment = commentService.updateCommentWithComment(commentBody, commentId);

        // if comment is successfully updated, status is 200. otherwise, 404
        ResponseEntity<Comment> responseEntity = comment != null ? new ResponseEntity<>(comment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return responseEntity;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        // delete the comment
        boolean deleted = commentService.deleteComment(commentId);

        // status should be 204 for successful delete. otherwise, 404
        HttpStatus status = deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(status);
    }
}
