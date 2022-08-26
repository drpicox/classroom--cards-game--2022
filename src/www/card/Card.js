import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { requestMoveActiveCard } from "../stack/stackSlice";
import { selectCard, activateCard } from "./cardSlice";

export function Card({ cardId }) {
  const card = useSelector((s) => selectCard(s, cardId));
  const dispatch = useDispatch();
  const activate = useCallback(
    () => dispatch(activateCard(cardId)),
    [dispatch, cardId],
  );
  const move = useCallback(
    (ev) => {
      ev.stopPropagation();
      dispatch(requestMoveActiveCard(card.position, card.zindex + 1));
    },
    [dispatch, card.position, card.zindex],
  );

  return (
    <div
      data-testid="card"
      data-cardid={card.id}
      data-cardname={card.name}
      data-zindex={card.zindex}
      onMouseDown={activate}
      onMouseUp={move}
    >
      {card.maxProgress > 1 && (
        <div data-testid="cardprogress">
          {card.progress} of {card.maxProgress}
        </div>
      )}
      {card.name}
      {Object.entries(card.tags).map(([tagName, tag]) => (
        <div key={tagName} data-tagname={tagName}>
          {tag.value}
        </div>
      ))}
      {Object.entries(card.description).map(([term, text]) => (
        <CardTermDescription key={term} term={term} text={text} />
      ))}
    </div>
  );
}

function CardTermDescription({ term, text }) {
  return (
    <div data-carddescriptionterm={term}>
      {term} is {text}
    </div>
  );
}
