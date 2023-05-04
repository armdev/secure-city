#!/usr/bin/env bash

set -e
echo "Build the smart city system"

mvn clean package -U -Dmaven.test.skip=true

