package org.example.processor;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

public class FilterProcessor implements Processor<String, String, String, String> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext<String, String> context) {
        this.context = context;
    }

    @Override
    public void process(Record<String, String> record) {
        if (record.value().length() > 5){
            context.forward(record);
        }
        context.commit();
    }

    @Override
    public void close() {
    }
}
