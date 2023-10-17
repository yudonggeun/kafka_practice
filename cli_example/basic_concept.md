## Intro
해당 레포의 컴포즈 파일로 환경 구성 이후 실습을 위한 명령어를 정리한 내용입니다.
모든 명령은 `cli` 서비스 컨테이너 안에서 `/kafka_*/bin` 디렉터리 에서 실행된 내용입니다.

* 컨테이너 접속
```shell
docker exec [OPTIONS] CONTAINER COMMAND [ARG...]
```
* 디렉터리 이동
```shell
cd /kafka*
cd /bin
```

## 23432
주키퍼 접속 및 실습 스크립트
```shell
./zookeeper-shell.sh zookeeper:2181
ls /
get /brokers/ids/0
get /controller
ls /brokers/topics
```
