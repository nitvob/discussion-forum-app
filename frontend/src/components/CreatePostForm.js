import { useState } from "react";
import { createPost } from "../api";
import { Form, Button } from "react-bootstrap";

function CreatePostForm({ addPost }) {
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    topics: "",
  });

  function handleSubmit(event) {
    event.preventDefault();

    const validation = validateFormData();
    if (validation !== null) {
      alert(validation);
    } else {
      const payload = {
        title: formData.title,
        description: formData.description,
        topics: formData.topics.split(","),
      };

      createPost(payload).then((post) => {
        if (post !== null) {
          addPost(post.id, post.title);
        }
      });
      setFormData({
        title: "",
        description: "",
        topics: "",
      });
    }
  }

  function validateFormData() {
    if (formData.title.length < 5) {
      return "Title too short";
    } else if (formData.description.length < 10) {
      return "Description too short";
    }

    return null;
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }

  return (
    <Form onSubmit={handleSubmit} className="mt-4">
      <Form.Group className="mb-3">
        <Form.Label>Title:</Form.Label>
        <Form.Control
          type="text"
          name="title"
          required
          onChange={handleChange}
          value={formData.title}
          placeholder="Title..."
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Description:</Form.Label>
        <Form.Control
          as="textarea"
          name="description"
          required
          rows={4}
          placeholder="Description..."
          onChange={handleChange}
          value={formData.description}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Topics:</Form.Label>
        <Form.Control
          type="text"
          name="topics"
          onChange={handleChange}
          value={formData.topics}
          placeholder="Topic 1, topic 2..."
        />
      </Form.Group>

      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
}

export default CreatePostForm;
