package dev.nit.quoraClone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    private record CreatePostPayload(String title, String description, List<String> topics) {
    }

    private record UpdatePostWithCommentPayload(String commentBody) {
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable String postId) {
        // get that post by id
        Post post = postService.getPost(postId);

        // if the post is null, response entity needs to return 404 status
        // otherwise, response should have post and 200 status
        ResponseEntity<Post> responseEntity = post != null ? new ResponseEntity<>(post, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        // get all the posts
        List<Post> posts = postService.getAllPosts();

        // return the response with 200 status
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Post>> getPostsByTopics(@RequestParam List<String> topics) {
        // get all posts by topics
        List<Post> posts = postService.getPostsByTopics(topics);

        // return the response with 200 status
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostPayload payload) {
        // destructure data from request body
        String title = payload.title;
        String description = payload.description;
        List<String> topics = payload.topics;

        // create the post
        Post post = postService.createPost(title, description, topics);

        // return response with 201 status
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePostWithComment(@RequestBody UpdatePostWithCommentPayload payload,
            @PathVariable String postId) {
        // destructure request body data
        String commentBody = payload.commentBody;

        // update the post with comment
        Post post = postService.updatePostWithComment(commentBody, postId);

        // if post is null, status needs to be 404
        // otherwise, status needs to be 200
        ResponseEntity<Post> responseEntity = post != null ? new ResponseEntity<>(post, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return responseEntity;
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId) {
        // delete the post
        boolean deleted = postService.deletePost(postId);

        // if deleted, status is 204. otherwise, 404
        HttpStatus status = deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(status);
    }
}
