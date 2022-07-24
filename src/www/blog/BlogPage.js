import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { selectPosts, requestPosts } from "./blogSlice";

export function BlogPage() {
  const posts = useSelector(selectPosts);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(requestPosts());
  }, [dispatch]);

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>
          <Link to={`/blog/${post.id}`} data-testid="post-list--item">
            {post.title}
          </Link>
        </li>
      ))}
    </ul>
  );
}
