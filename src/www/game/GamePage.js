import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Card } from "../cards/Card";
import { selectAllCardIds } from "../cards/cardsSlice";
import { requestEndMoon, requestGame } from "./gameSlice";

export function GamePage() {
  const dispatch = useDispatch();
  const cardIds = useSelector(selectAllCardIds);

  const endMoon = useCallback(() => dispatch(requestEndMoon()), [dispatch]);
  const reload = useCallback(() => dispatch(requestGame()), [dispatch]);

  return (
    <div>
      <button onClick={endMoon}>End Moon</button>
      <button onClick={reload}>Reload</button>;
      {cardIds.map((id) => (
        <Card key={id} cardId={id} />
      ))}
    </div>
  );
}
