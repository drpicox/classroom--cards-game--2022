import { useSelector } from "react-redux";
import { selectCard } from "./cardsSlice";

export function Card({ cardId }) {
  const card = useSelector((s) => selectCard(s, cardId));

  return (
    <div data-testid="card" data-cardname={card.name}>
      {card.name}
    </div>
  );
}
