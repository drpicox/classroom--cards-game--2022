#!/bin/bash

error() {
  echo "!! FAILED $*"
  exit 1;
}

CI=1 yarn create-tests || error "yarn create-tests"
./mvnw test            || error "./mvwn test"
CI=1 yarn test         || error "yarn test"
