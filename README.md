## What to do After Clone or Pull

- `yarn`
- `yarn create-tests`

**Note**: you should keep running `yarn create-tests` while you are developing.
It will create and update all tests according the blog contents.

## Java

It works based on Java 17.

**MacOS**

Tested with correto-17 17.0.4.1 (aarch64)

In mac, add to ~/.zshrc:

```
$ export JAVA_HOME=$(/usr/libexec/java_home -v 1.17.0.4)
```

## Javascript

It requires at least Node 18.6.0 and yarn.

You can enable yarn by executing:

```
$ corepack enable
```

## Backend

Run on Java 17.

Suggested flags for faster execution: `-ea -client -XX:TieredStopAtLevel=1`

Non-fully compatible flags for even faster execution: `-ea -client -Xverify:none -noverify -XX:TieredStopAtLevel=1`

## create-tests

It is a utility to maintain the tests and create the initial context files.

Run the command:

```bash
$ yarn create-tests
```

Watches the posts and creates the test files.

- Creates `src/test/java/com/drpicox/game/Post_XXX_Test.java`
- Creates `src/test/java/com/drpicox/game/Post_XXX_Context.java`
- Creates `src/www/__test__/Post_XXX_Test.spec.js`
- Creates `src/www/__test__/Post_XXX_Context.js`

The possible outputs are:

- Skipped: Creates nothing, it is because the blog has no developer,
- Created: Creates all four files,
- Updated: Updates only the Post_XXX_Test files, but does not overwrite the existing Context_XXX files

## Frontend DOM Queries

Read the docs here: https://testing-library.com/docs/vue-testing-library/cheatsheet/#queries

Frontend queries are named as follows:

| Name          | No match | 1 match | 1+ match | await |
| ------------- | -------- | ------- | -------- | ----- |
| getBy...      | throw    | return  | throw    | No    |
| findBy...     | throw    | return  | throw    | Yes   |
| queryBy...    | null     | return  | throw    | No    |
| getAllBy...   | throw    | array   | array    | No    |
| findAllBy...  | throw    | array   | array    | Yes   |
| queryAllBy... | []       | array   | array    | No    |

- `getBy`: looks for exactly one element, if it founds it, it returns the element,
otherwise, it throws an error. 
Example _getTagByName_: gets the DOM element of the only tag with that name, 
if there is none, or more than one, it throws an exception.

- `findBy`: equivalent to `getBy`, but waits the element to appear.
Do not use, use `await userSimulator.waitForLoading();` instead.

- `queryBy`: looks for zero or one element, returns null if there is none,
the DOM element if it founds it, and if there is more than one, it throws an error. 
Example _queryCardByName_: returns the DOM element of the only card with that name, if there is none, returns null, if there is more than one, it throws an exception.

- `getAllBy`: looks for one or more elements, 
if there is any, it returns a list with all of them,
if there is none, it throws an error. 
Example _getAllCardsByName_.

- `findAllBy`: equivalent to `getAllBy`, but waits the elements to appear.
Do not use, use `await userSimulator.waitForLoading();` instead.

- `queryAllBy`: returns all elements that satisfies the query and
returns them in a list, if there was none, it returns an empty list.
Example _queryAllCardsByName_.

In some cases there is no "By", like for example: `getAllCards`. In these cases,
there is no need for "By".

All this queries, have a first argument that is a dom element. Examples:

```js
// Assigns to berry the DOM element of the only berry card, or throws
const theBerry = getCardByName(mainView, 'berry');
// Assings to food the DOM element of the tag food inside theBerry
const theBerryFood = getTagByName(theBerry, 'food');
// Assings to foodTags all the DOM elements that are tags for food
const doods = getAllTagByName(mainView, 'food');
```

> How to use DOM elements, look ad MDN, ex:
> https://developer.mozilla.org/en-US/docs/Web/API/Node/textContent

## Frontend Wait In Tests

All test methods are `async` because they may want to wait 
for loading a data.

Please, do not use any function to load but:

```js
await userSimulator.waitForLoading();
```

The idea is that the middleware shows the spinner before making
the Backend API Call, then it resolves the call, and once
it has finished, it hides the spinner. The middleware should
look like:

```js
export const myMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === MY_ACTION_TYPE) {
    store.dispatch(showLoadingSpinner());   // <<<<<<< Add This
    const data = await fetchMyData();
    store.dispatch(replaceMyData(data));
    store.dispatch(hideLoadingSpinner());  // <<<<<<< Add This
  }
};
```

Please notice the two store dispatch, first _showLoadingSpinner_,
and at the end, _hideLoadingSpinner_.

The function waitForLoading leverages on that, first to be sure
that an Backend Api Call has began, and then, to wait for its
completion.

Note that any other way for data is very dangerous and can 
hide a lot of bugs, plus lots of error messages like:

```
When testing, code that causes React state updates should be wrapped into act(...):
     
     act(() => {
       /* fire events that update state */
     });
     /* assert on the output */
     
This ensures that you're testing the behavior the user would see in the browser. Learn more at https://fb.me/react-wrap-tests-with-act
```

## About Natural Testing Language

* **Given**: They are sentences that explain something that is true at that moment
  and we will no assert, because our code will make that happen. 
  We can see them as pre-requisites.
  In Givens we often do direct *Service calls to make sure that what we want is satisfied.
  In some cases, it might also make apiCalls so the backend can send the
  resulting state to the frontend, and then, the frontend should refresh
  the game.
  They are what we prepare for the execution.
* **Should**: They are sentences that assert that the results are correct.
  We can see them as post-conditions.
  Shoulds, unlike Givens, should not use any *Service calls, 
  and should neither use apiCalls, but instead, 
  it should assert over the last game response.
  They are what we verify from the execution.
* _The Rest_: Sentences without Given or Should are actions: things that the user do.
  These actions will always perform any apiCall and will save the result for future
  assertions. They should never use *Service calls.
  They are what we execute.
 
