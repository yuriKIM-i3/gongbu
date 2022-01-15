let's do gongbu!

DOCKER_UID=$(id -u $USER) DOCKER_GID=$(id -g $USER) docker-compose up
docker exec -it web /bin/bash
gradle build *optional
gradle bootRun