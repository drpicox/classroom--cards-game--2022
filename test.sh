#!/bin/bash

error() {
  echo "!! FAILED $*"
  exit 1;
}

rm -fr $(find src -name 'Post_*_Test.*')
CI=1 yarn create-tests || error "yarn create-tests"
./mvnw test            || error "./mvwn test"
CI=1 yarn test         || error "yarn test"
