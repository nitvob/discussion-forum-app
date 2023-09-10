import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getSinglePost, deletePost } from "../api";
import Comment from "./Comment";
import CreateCommentForm from "./CreateCommentForm";
import { Button, Container, ListGroup } from "react-bootstrap";

const Post = () => {
  const params = useParams();
  const navigate = useNavigate();
  const postId = params.postId;
  const [post, setPost] = useState(null);

  useEffect(() => {
    getSinglePost(postId).then((post) => {
      setPost(post);
    });
  }, []);

  function deletePostAndRedirect(postId) {
    deletePost(postId);
    navigate("/posts");
  }

  function updatePost(newPost) {
    setPost(newPost);
  }

  function renderPost() {
    return (
      post && (
        <Container className="mt-4">
          <h1>{post.title}</h1>
          <Button
            variant="danger"
            onClick={() => deletePostAndRedirect(post.id)}
            size="sm"
          >
            Delete Post
          </Button>
          <h3 className="mt-3">{post.description}</h3>

          <p>Topics: {post.topics}</p>

          <CreateCommentForm
            resourceId={post.id}
            resourceType={"post"}
            updatePost={updatePost}
            updateComment={null}
          />

          {renderComments()}
        </Container>
      )
    );
  }

  function renderComments() {
    return (
      post &&
      post.comments.map((comment, index) => {
        return (
          <ListGroup.Item key={index}>
            <Comment commentData={comment} />
          </ListGroup.Item>
        );
      })
    );
  }

  return <div className="container">{renderPost()}</div>;
};

export default Post;
