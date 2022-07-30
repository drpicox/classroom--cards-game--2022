import { useCallback } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router";
import { requestGame } from "./game/gameSlice";

export function HomePage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const enterTheGame = useCallback(() => {
    dispatch(requestGame());
    navigate("/game");
  }, [dispatch, navigate]);

  return (
    <div>
      <h1>Welcome</h1>
      <button onClick={enterTheGame}>Enter the Game</button>
    </div>
  );
}
