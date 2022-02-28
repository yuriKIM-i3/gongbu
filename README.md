let's do gongbu!

```
DOCKER_UID=$(id -u $USER) DOCKER_GID=$(id -g $USER) docker-compose up

--spring boot
docker exec -it web /bin/bash
gradle build *only first time
flyway migrate *to migrate when table changed
gradle bootRun(to seeding data try this > gradle bootRun --args='seedingData')

--postgres
docker exec -it db bash
psql -d gongbu -U gongbu
```