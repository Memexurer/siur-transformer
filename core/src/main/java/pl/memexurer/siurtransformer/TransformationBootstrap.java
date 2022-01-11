package pl.memexurer.siurtransformer;

import lombok.SneakyThrows;
import pl.memexurer.siurtransformer.exporter.clazz.ClassFileExporter;
import pl.memexurer.siurtransformer.exporter.file.TransformerFileExporter;
import pl.memexurer.siurtransformer.loader.clazz.ClassFileLoader;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;
import pl.memexurer.siurtransformer.loader.file.TransformerFileLoader;

public class TransformationBootstrap {
    private TransformerFile file;

    @SneakyThrows
    public void load(TransformerFileLoader<?> fileLoader, ClassFileLoader classFileLoader) {
        this.file = fileLoader.loadFile(classFileLoader);
    }

    public void export(TransformerFileExporter fileExporter, ClassFileExporter classFileExporter) {
        fileExporter.exportFile(file, classFileExporter);
    }
}
