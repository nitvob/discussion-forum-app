import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Post from "./components/Post";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/posts" element={<Home />} />
        <Route path="/posts/:postId" element={<Post />} />
        <Route path="*" element={<div>Page not found.</div>} />
      </Routes>
    </Router>
  );
}

export default App;
