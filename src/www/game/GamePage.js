import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Card } from "../cards/Card";
import { selectAllCardIds } from "../cards/cardsSlice";
import { requestEndMoon } from "./gameSlice";

export function GamePage() {
  const dispatch = useDispatch();
  const cardIds = useSelector(selectAllCardIds);
  const s = useSelector((s) => s);

  const endMoon = useCallback(() => dispatch(requestEndMoon()), [dispatch]);

  return (
    <div>
      <button onClick={endMoon}>End Moon</button>
      {cardIds.map((id) => (
        <Card key={id} cardId={id} />
      ))}
    </div>
  );
}
