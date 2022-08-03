import { Link } from "react-router-dom";
import { Loading } from "../loading/Loading";

export function Header() {
  return (
    <div>
      <Link to="/blog">Blog</Link>
      <Link to="/game">Game</Link>
      <Loading />
    </div>
  );
}
