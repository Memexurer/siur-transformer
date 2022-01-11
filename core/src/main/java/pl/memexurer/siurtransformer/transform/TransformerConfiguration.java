package pl.memexurer.siurtransformer.transform;

import java.util.Map;

public class TransformerConfiguration {
    private final Map<String, Object> configuration;

    public TransformerConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    public <T> T getValue(String str) {
        return (T) configuration.get(str);
    }
}
