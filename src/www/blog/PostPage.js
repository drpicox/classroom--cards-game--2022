import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { selectPost, requestPost } from "./blogSlice";

export function PostPage() {
  const { postId } = useParams();
  const dispatch = useDispatch();
  const post = useSelector((state) => selectPost(state, postId));

  useEffect(() => {
    dispatch(requestPost(postId));
  }, [dispatch, postId]);

  return (
    <div>
      <h1 data-testid="post-title">{post.title}</h1>
      <div data-testid="post-body">{post.body}</div>
    </div>
  );
}
