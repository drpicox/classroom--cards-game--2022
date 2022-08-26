import { useCallback } from "react";
import { useDispatch } from "react-redux";
import { requestDiscardActiveCard } from "./cardSlice";

export function Discard() {
  const dispatch = useDispatch();
  const discard = useCallback(
    () => dispatch(requestDiscardActiveCard()),
    [dispatch],
  );

  return (
    <div data-testid="discard" onMouseDown={discard} onMouseUp={discard}>
      Discard
    </div>
  );
}
