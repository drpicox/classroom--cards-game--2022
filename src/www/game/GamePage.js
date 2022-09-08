import { useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Discard } from "../card/Discard";
import { Idea } from "../idea/Idea";
import { selectAllIdeaNames } from "../idea/ideaSlice";
import { Stack } from "../stack/Stack";
import { selectAllStackPositions } from "../stack/stackSlice";
import { requestEndMoon, requestGame } from "./gameSlice";

export function GamePage() {
  const dispatch = useDispatch();
  const stackPositions = useSelector(selectAllStackPositions);
  const ideaNames = useSelector(selectAllIdeaNames);

  const endMoon = useCallback(() => dispatch(requestEndMoon()), [dispatch]);
  const reload = useCallback(() => dispatch(requestGame()), [dispatch]);

  return (
    <div>
      <div className="game-header">
        <button onClick={endMoon}>End Moon</button>
        <button onClick={reload}>Reload</button>
      </div>
      <div className="idea-sidebar">
        <Discard />
        {ideaNames.map((name) => (
          <Idea key={name} ideaName={name} />
        ))}
      </div>
      <div className="table">
        {stackPositions.map((position) => (
          <Stack key={position} position={position} />
        ))}
      </div>
    </div>
  );
}
