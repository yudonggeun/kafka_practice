package org.example.connector.source;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.List;
import java.util.Map;

public class TestSourceTask extends SourceTask {
    // 태스크의 버전을 지정한다. 보통 커넥터의 version() 메서드에서 지정한 버전과 동일한 버전으로 작성한는 것이 일반적이다.
    @Override
    public String version() {
        return null;
    }

    /*
    태스크가 시작할 때 필요한 로직을 작성한다.
    태스크는 실질적으로 데이터를 처리하는 역할을 하므로 데이터 처리에 필요한 모든 리소스를 여기서 초기화하면 좋다. 예를 들어, JDBC 소스 커넥터를 구현한다면
    이 메서드에서 JDBC 커넥션을 맺는다.
     */
    @Override
    public void start(Map<String, String> props) {

    }

    /*
    소스 애플리케이션 또는 소스 파일로부터 데이터를 읽어오는 로직을 작성한다. 데이터를 읽어오면 토픽으로 보낼 데이터를 SourceRecord로 정의한다.
    데이터를 읽어오면 토픽으로 보낼 데이터를 SourceRecord로 정의한다.
     */
    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        return null;
    }

    @Override
    public void stop() {

    }
}
