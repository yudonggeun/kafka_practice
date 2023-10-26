package org.example.connector.source;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.List;
import java.util.Map;

public class TestSourceConnector extends SourceConnector {

    // 사용자가 JSON 또는 config 파일 형태로 입력한 설정값을 초기화하는 메서드
    @Override
    public void start(Map<String, String> props) {

    }

    // 이 커넥터가 사용할 테스크 클래스를 지정
    @Override
    public Class<? extends Task> taskClass() {
        return null;
    }

    // 테스크 개수가 2개 이상인 경우 태스크마다 각기 다른 옵션을 설정할 때 사용한다.
    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        return null;
    }

    // 커넥터가 종료될 때 필요한 로직을 작성
    @Override
    public void stop() {

    }

    // 커넥터가 사용할 설정값에 대한 정보를 받는다. 커넥터의 설정값은 ConfigDef 클래스를 통해 각 설정의 이름, 기본값, 중요도, 설명을 정의할 수 있다.
    @Override
    public ConfigDef config() {
        return null;
    }

    // 커넥터의 버전을 리턴
    @Override
    public String version() {
        return null;
    }
}
