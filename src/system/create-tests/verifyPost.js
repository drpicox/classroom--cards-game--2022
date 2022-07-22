const chalk = require("chalk");

function verifyPost(post) {
  return verifyPostId(post) && verifyPostTitle(post);
}

exports.verifyPost = verifyPost;

function verifyPostId(post) {
  const ok = /^20\d\d-[01]\d-[0123]\d(_[a-z0-9]+)+$/.test(post.id);
  if (ok) return true;

  writePostError(post);
  console.error(
    chalk.red(
      [
        `The post ${post.id}.md does not follow the expected naming format: 20XX-XX-XX_snake_case_title.md`,
        `- It has the four digit year number,`,
        `- It has a two digit month number, starting with 0 if not october or later,`,
        `- It has a two digit day number, starting with 0 if earlier than the 10th,`,
        `- There is a dash (minus sign) after the year,`,
        `- There is a dash (minus sign) after the month,`,
        `- There is an underscore after the day,`,
        `- All text is in lowercase,`,
        `- There are only letters numbers and underscores past the date.`,
      ].join("\n")
    )
  );

  return false;
}

function verifyPostTitle(post) {
  let expectedId = post.title
    .toLowerCase()
    .split(/[^a-z0-9]+/)
    .join("_");
  if (expectedId.startsWith("_")) expectedId = expectedId.slice(1);
  expectedId = post.id.slice(0, 11) + expectedId;

  const ok = expectedId === post.id;
  if (ok) return;

  writePostError(post);
  console.error(
    chalk.red(
      [
        `The post ${post.id} naming does not match the title.`,
        `- found post title  : ${post.title}`,
        `- expected post name: ${expectedId}.md`,
        `- actual post name  : ${post.id}.md`,
        `Please, rename the file accordingly.`,
      ].join("\n")
    )
  );

  return false;
}

function writePostError({ postPath }) {
  console.error(chalk.red.inverse(`ERROR ${postPath}`));
}
