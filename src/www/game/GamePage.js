import { useSelector } from "react-redux";
import { Card } from "../cards/Card";
import { selectAllCardIds } from "../cards/cardsSlice";

export function GamePage() {
  const cardIds = useSelector(selectAllCardIds);

  return (
    <div>
      {cardIds.map((id) => (
        <Card key={id} cardId={id} />
      ))}
    </div>
  );
}
