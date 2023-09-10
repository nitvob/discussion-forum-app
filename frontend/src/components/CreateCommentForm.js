import { useState } from "react";
import { updateCommentWithComment, updatePostWithComment } from "../api";
import { Form, Button } from "react-bootstrap";

function CreateCommentForm({
  resourceId,
  resourceType,
  updatePost,
  updateComment,
}) {
  const [formData, setFormData] = useState({
    commentBody: "",
  });

  function handleSubmit(event) {
    event.preventDefault();

    const payload = formData;
    if (resourceType === "post") {
      updatePostWithComment(resourceId, payload).then((post) => {
        if (post !== null) {
          updatePost(post);
        }
      });
    } else {
      updateCommentWithComment(resourceId, payload).then((comment) => {
        if (comment !== null) {
          updateComment(comment);
        }
      });
    }

    setFormData({
      commentBody: "",
    });
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }

  return (
    <Form onSubmit={handleSubmit} className="mt-2">
      <Form.Group>
        <Form.Label>Comment: </Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          type="text"
          name="commentBody"
          required
          onChange={handleChange}
          value={formData.commentBody}
          placeholder="Comment..."
        />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
}

export default CreateCommentForm;
