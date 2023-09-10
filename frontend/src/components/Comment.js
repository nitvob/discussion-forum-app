import React, { useState } from "react";
import CreateCommentForm from "./CreateCommentForm";
import { deleteComment } from "../api";
import { Button, Container } from "react-bootstrap";

function Comment({ commentData }) {
  const [showCreateCommentForm, setShowCreateCommentForm] = useState(false);
  const [comment, setComment] = useState(commentData);

  function updateComment(newComment) {
    setComment(newComment);
  }

  function toggleVisibility() {
    setShowCreateCommentForm(
      (prevShowCreateCommentForm) => !prevShowCreateCommentForm
    );
  }

  function renderComments() {
    return comment.comments.map((subComment, index) => {
      return (
        <div key={index} style={{ marginLeft: "10px" }}>
          <Comment commentData={subComment} />
        </div>
      );
    });
  }

  function removeComment() {
    deleteComment(comment.id);
    setComment(null);
  }
  return comment ? (
    <Container className="mt-3 mb-3">
      <p>{comment.commentBody}</p>

      <Button variant="secondary" onClick={toggleVisibility} size="sm">
        {showCreateCommentForm ? "▲" : "▼"}
      </Button>
      {showCreateCommentForm && (
        <CreateCommentForm
          resourceId={comment.id}
          resourceType={"comment"}
          updatePost={null}
          updateComment={updateComment}
        />
      )}
      <Button variant="danger" onClick={removeComment} size="sm">
        Delete Comment
      </Button>

      {renderComments()}
    </Container>
  ) : (
    <></>
  );
}

export default Comment;
