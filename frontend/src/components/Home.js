import { useEffect, useState } from "react";
import { getAllPosts } from "../api";
import CreatePostForm from "./CreatePostForm";
import { Card, Button } from "react-bootstrap";

function Home() {
  const [posts, setPosts] = useState([]);
  const currentUrl = window.location.href;

  useEffect(() => {
    getAllPosts().then((posts) => setPosts(posts));
  }, []);

  function addPost(id, title) {
    setPosts((prevPosts) => [{ id: id, title: title }, ...prevPosts]);
  }

  function renderPostLinks() {
    return posts.map((post, index) => {
      const link = `${currentUrl}/${post.id}`;
      return (
        <Card className="mb-3" key={index}>
          <Card.Body>
            <Card.Title>{post.title}</Card.Title>
            <Button variant="primary" href={link}>
              Read More
            </Button>
          </Card.Body>
        </Card>
      );
    });
  }

  return (
    <div className="Home container">
      <h1 className="my-5 text-center">Home</h1>
      <CreatePostForm addPost={addPost} />
      <div className="mt-5">{renderPostLinks()}</div>
    </div>
  );
}

export default Home;
