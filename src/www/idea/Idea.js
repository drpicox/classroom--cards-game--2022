import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectIdea, requestDrawIdea } from "./ideaSlice";

export function Idea({ ideaName }) {
  const idea = useSelector((s) => selectIdea(s, ideaName));
  const dispatch = useDispatch();
  const drawIdea = useCallback(
    () => dispatch(requestDrawIdea(idea.id)),
    [dispatch, idea.id],
  );

  return (
    <div data-testid="idea" data-ideaname={idea.name} onClick={drawIdea}>
      {idea.name}
      <div data-testid="level">Level: {idea.level}</div>
      <div data-testid="xp">XP: {idea.xp}</div>
      {idea.tagRequirements.map((tagRequirement) => (
        <IdeaTagRequirement
          key={tagRequirement.tagName}
          tagRequirement={tagRequirement}
        />
      ))}
      {idea.cardRewards.length > 0 && (
        <ul>
          May create cards:
          {idea.cardRewards.map((cardReward) => (
            <li key={cardReward} data-testid="maycreatecard">
              {cardReward}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

function IdeaTagRequirement({ tagRequirement }) {
  return (
    <div data-testid="ideaTagRequirement">
      {tagRequirement.cardCount} x {tagRequirement.tagValue}
      {" of "}
      {tagRequirement.tagName}
    </div>
  );
}
