package org.example.connector.source;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFileSourceConnector extends SourceConnector {

    private Map<String, String> configProperties;

    @Override
    public String version() {
        return "1.0";
    }

    // 커넥터를 생성할 때 받은 설정값들을 초기화한다.
    @Override
    public void start(Map<String, String> props) {
        this.configProperties = props;
        try {
            new SingleFileSourceConnectorConfig(props);
        } catch (ConfigException e) {
            throw new ConnectException(e.getMessage(), e);
        }
    }

    // SingleFileSourceConnector 가 사용할 테스크의 클래스 이름을 지정한다.
    @Override
    public Class<? extends Task> taskClass() {
        return SingleFileSourceTask.class;
    }

    // 테스크가 2개 이상인 경우 테스크마다 다른 설정값을 줄 때 사용한다.
    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        var taskConfigs = new ArrayList<Map<String, String>>();
        var taskProps = new HashMap<>(configProperties);

        for (int i = 0; i < maxTasks; i++) {
            taskConfigs.add(taskProps);
        }
        return taskConfigs;
    }

    // 커넥터에 사용할 설정값을 지정한다.
    @Override
    public ConfigDef config() {
        return SingleFileSourceConnectorConfig.CONFIG;
    }

    // 커넥터가 종료될 때 필요한 로직을 추가한다.
    @Override
    public void stop() {
    }
}
