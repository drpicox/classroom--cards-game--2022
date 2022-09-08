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
    <div className="discard" data-testid="discard" onClick={discard}>
      🗑️ Discard
    </div>
  );
}
