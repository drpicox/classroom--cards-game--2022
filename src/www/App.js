import { Routes, Route } from "react-router-dom";
import { Header } from "./layout/Header";
import { BlogPage } from "./blog/BlogPage";
import { PostPage } from "./blog/PostPage";

function HomePage() {
  return <h1>HomePage</h1>;
}

export function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/blog" element={<BlogPage />} />
        <Route path="/blog/:postId" element={<PostPage />} />
      </Routes>
    </div>
  );
}
