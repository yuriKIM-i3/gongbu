#!/bin/bash
set -e

flyway migrate
java -jar -Dspring.profiles.active=prd ./build/libs/gongbu-0.0.1-SNAPSHOT.jar 