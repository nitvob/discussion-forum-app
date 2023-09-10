import axios from "axios";

export function getSinglePost(postId) {
  return axios
    .get(`/api/posts/${postId}`, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      return null;
    });
}

export function getAllPosts() {
  return axios
    .get("/api/posts", {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      const data = response.data;
      return data.map((post) => {
        return {
          id: post.id,
          title: post.title,
        };
      });
    })
    .catch((error) => {
      console.log(error);
      return [];
    });
}

export function createPost(payload) {
  return axios
    .post("/api/posts", JSON.stringify(payload), {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      return null;
    });
}

export function deletePost(postId) {
  return axios
    .delete(`/api/posts/${postId}`, {
      headers: {
        "Content-Type": "appliction/json",
      },
    })
    .catch((error) => console.log(error));
}

export function updatePostWithComment(postId, payload) {
  return axios
    .put(`/api/posts/${postId}`, JSON.stringify(payload), {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      return null;
    });
}

export function updateCommentWithComment(commentId, payload) {
  return axios
    .put(`/api/comments/${commentId}`, JSON.stringify(payload), {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      return null;
    });
}

export function deleteComment(commentId) {
  return axios
    .delete(`/api/comments/${commentId}`, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .catch((error) => console.log(error));
}
