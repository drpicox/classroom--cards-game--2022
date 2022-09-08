import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { selectPost, requestPost } from "./blogSlice";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import { PostSummary } from "./PostSummary";

const remarkPlugins = [remarkGfm];

export function PostPage() {
  const { postId } = useParams();
  const dispatch = useDispatch();
  const post = useSelector((state) => selectPost(state, postId));

  useEffect(() => {
    dispatch(requestPost(postId));
  }, [dispatch, postId]);

  return (
    <div className="post">
      <h1 className="post-title" data-testid="post-title">
        {post.title}
      </h1>
      <small>
        <PostSummary post={post} />
      </small>
      <div className="post-body" data-testid="post-body">
        <ReactMarkdown children={post.body} remarkPlugins={remarkPlugins} />
        <br />
        <hr />
        <br />
      </div>
    </div>
  );
}
