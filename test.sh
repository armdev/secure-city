#!/usr/bin/env bash

set -e

ab -k -c 1000 -n 10000  http://localhost:12345/api/v2/events/send
