import { Link } from "react-router-dom";

export function BlogPage() {
  return (
    <ul>
      <li>
        <Link to="/blog/kk" data-testid="post-list--item">
          Hello Blog
        </Link>
      </li>
    </ul>
  );
}
