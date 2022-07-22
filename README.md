## What to do After Clone or Pull

- `yarn`
- `yarn create-tests` 

**Note**: you should keep running `yarn create-tests` while you are developing.
It will create and update all tests according the blog contents.

## Backend

Run on Java 17.

Suggested flags for faster execution: `-ea -client -XX:TieredStopAtLevel=1`

Non-fully compatible flags for even faster execution: `-ea -client -Xverify:none -noverify -XX:TieredStopAtLevel=1`

## create-tests

It is an utility to maintain the tests and create the initial context files.

Run the command:

```bash
$ yarn create-tests
```

Watches the posts and creates the test files.

- Creates `src/test/java/com/drpicox/game/Post_XXX_Test.java`
- Creates `src/test/java/com/drpicox/game/contexts/Context_XXX.java`
- Creates `src/www/__test__/Post_XXX_Test.spec.js`
- Creates `src/www/__test__/contexts/Context_XXX.js`

The possible outputs are:

- Skipped: Creates nothing, it is because the blog has no developer,
- Created: Creates all four files,
- Updated: Updates only the Post_XXX_Test files, but does not overwrite the existing Context_XXX files
