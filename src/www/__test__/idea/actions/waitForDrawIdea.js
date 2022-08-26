import { mainView } from "../../main";
import { click, waitForLoading } from "../../main/actions";
import { getIdeaByName } from "../queries";

export async function waitForDrawIdea(ideaName) {
  var idea = getIdeaByName(mainView, ideaName);
  click(idea);
  await waitForLoading();
}
