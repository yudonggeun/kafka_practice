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

## 카프카 커멘드 라인 툴
## kafka-topics.sh
* 토픽 생성<br>
`hello.kafka` 이름의 토픽을 생성한다.
 
```shell
./kafka-topics.sh \
--create \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka
```
추가적인 옵션을 설정하지 않으면 기본 설정에 따라 토픽이 생성된다.

* 옵션을 적용하여 토픽 생성
```shell
./kafka-topics.sh \
--create \
--bootstrap-server kafka:9092 \ 
--partitions 4 \
--replication-factor 1 \
--config retention.ms=172800000
-- topic hello.kafka.2
```
|option| describe|
|--|--|
|partitions| 파티션의 개수를 설정한다.|
|replication-factor | 복제의 수를 설정한다. 최대 개수는 브로커의 수이다.|

* 토픽 리스트 조회
```shell
./kafka-topics.sh \
--bootstrap-server kafka:9092 \ 
--list
```

* 토픽 상세 조회
```shell
./kafka-topics.sh \
--bootstrap-server kafka:9092 \ 
--describe \
--topic hello.kafka.2
```

* 토픽 옵션 수정
> 파티션 개수 변경
```shell
./kafka-topics.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka \
--alter \
--partitions 5
```
> 파티션 변경 확인
```shell
./kafka-topics.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka \
--describe
```
> 보유 기간 변경
```shell
./kafka-configs.sh \
--bootstrap-server kafka:9092 \ 
--entity-type topics \
--entity-name hello.kafka \ 
--alter --add-config retention.ms=86400000
```
> 보유 기간 변경 확인
```shell
./kafka-configs.sh \
--bootstrap-server kafka:9092 \ 
--entity-type topics \
--entity-name hello.kafka \ 
--describe
```

## kafka-console-producer.sh
* 토픽에 레코드 전송하기
```shell
./kafka-console-producer.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka
```
메시지 키는 null로 설정되어 전송

* 키를 가지는 레코드 전송
```shell
./kafka-console-producer.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka \
--property "parse.key=true"
--property "key.separator=:"
```
> 데이터 입력폼
```
key1:no1
key2:no2
key3:no3
...
```
키가 동일한 경우에는 동일한 파티션으로 레코드가 전송된다.

## kafka-console-consumer.sh
* 가장 처음 데이터부터 토픽의 데이터 출력하기
```shell
./kafka-console-consumer.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka
--from-beginning
```

* 메시지 키와 값을 출력하기
```shell
./kafka-console-consumer.sh \
--bootstrap-server kafka:9092 \ 
--topic hello.kafka \
--property print.key=true \
--property key.separator="-" \
--group hello-group \
--from-beginning
```
> 컨슈머 그룹 : 컨슈머 그룹을 통해 가져간 토픽의 메시지는 가져간 메시지에 대해 커밋을 한다.

> 커밋 : 컨슈머가 특정 레코드까지 처리를 완료 했음을 알리는 것

## kafka-consumer-groups.sh
* 컨슈머 그룹 조회
```shell
./kafka-consumer-groups.sh \
--bootstrap-server kafka:9092 \
--list
```

* 그룹 상세 조회
```shell
./kafka-consumer-groups.sh \
--bootstrap-server kafka:9092 \
--group hello-group \
--describe
```

## kafka-delete-records.sh
* 데이터 삭제
```shell
echo {\"partitions\": [{\"topic\": \"verity-test\", \"partition\": 0, \"offset\": 2}], \"version\": 1} > delete-topic.json
./kafka-delete-records.sh \
--bootstrap-server kafka:9092 \
--offset-json-file delete-topic.json
```
어떤 토픽에 어떤 파티션에 어디까지 삭제할지 지정하여 데이터를 삭제한다.