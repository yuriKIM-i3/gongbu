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


docker-compose -f docker-compose-gitaction.yml up

## 開発環境設定
- S3から `application-dev.properties` をダウンロードし、 `src/main/resources/application-dev.properties` のように配置お願いします！
  - S3に関する情報は管理者にお尋ねください！
  - 正しいファイルがダウンロード出来たら→ `application-xxx-exam.properties` のような構成になります。