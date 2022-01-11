package pl.memexurer.siurtransformer.transform;

import lombok.Data;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;

@Data
public class TransformerContext {
    private final TransformerFile file;
    private final TransformerConfiguration configuration;
}
