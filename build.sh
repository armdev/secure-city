#!/usr/bin/env bash

set -e
echo "Build the secure city system"

mvn clean package -U -Dmaven.test.skip=true

