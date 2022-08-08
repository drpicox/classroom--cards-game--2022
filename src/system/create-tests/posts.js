const levenshtein = require("fast-levenshtein");

const posts = {};

Object.assign(exports, {
  registerPost(post) {
    posts[post.id] = post;
  },

  findClosestMethod(post, method) {
    const usages = findMethodUsages(findOtherPosts(post));
    const targetName = method.name;

    let candidate = usages[0];
    let candidateDistance = levenshtein.get(candidate.name, targetName);
    for (let i = 1; i < usages.length; i += 1) {
      let newCandidate = usages[i];
      let newDistance = levenshtein.get(newCandidate.name, targetName);
      if (newDistance < candidateDistance) {
        candidate = newCandidate;
        candidateDistance = newDistance;
      }
    }

    return { ...candidate, distance: candidateDistance };
  },
});

function findMethodUsages(posts) {
  posts = posts.slice().sort().reverse();
  const usages = {};
  posts.forEach((post) => {
    post.contextMethods.forEach((method) => {
      if (!usages[method.name]) {
        usages[method.name] = {
          ...method,
          postIds: [],
        };
      }
      usages[method.name].postIds.push(post.id);
    });
  });
  return Object.values(usages);
}

function findOtherPosts(post) {
  return Object.values(posts).filter((p) => p !== post);
}
