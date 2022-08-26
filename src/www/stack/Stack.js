import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Card } from "../card/Card";
import { requestMoveActiveCard, selectStack } from "./stackSlice";

export function Stack({ position }) {
  const stack = useSelector((s) => selectStack(s, position));
  const dispatch = useDispatch();
  const move = useCallback(
    () => dispatch(requestMoveActiveCard(position, 0)),
    [dispatch, position],
  );

  return (
    <div
      data-testid="stack"
      data-stackposition={stack.position}
      onMouseUp={move}
    >
      {stack.cardIds.map((cardId) => (
        <Card key={cardId} cardId={cardId} />
      ))}
    </div>
  );
}
