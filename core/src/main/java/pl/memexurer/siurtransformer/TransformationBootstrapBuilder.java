package pl.memexurer.siurtransformer;

import lombok.SneakyThrows;
import pl.memexurer.siurtransformer.exporter.clazz.ClassFileExporter;
import pl.memexurer.siurtransformer.exporter.clazz.FramelessClassFileExporter;
import pl.memexurer.siurtransformer.exporter.file.TransformerFileExporter;
import pl.memexurer.siurtransformer.loader.clazz.ClassFileLoader;
import pl.memexurer.siurtransformer.loader.clazz.AsmClassFileLoader;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;
import pl.memexurer.siurtransformer.loader.file.TransformerFileLoader;

import java.io.File;
import java.util.function.Consumer;

public class TransformationBootstrapBuilder {
    private TransformerFile inputFile;

    @SneakyThrows
    public TransformationBootstrapBuilder withInputFile(File file, ClassFileLoader loader) {
        return withInputLoader(TransformerFileLoader.getFileLoader(file), loader);
    }

    @SneakyThrows
    public TransformationBootstrapBuilder withInputFile(File file) {
        return withInputFile(file, new AsmClassFileLoader());
    }

    @SneakyThrows
    public TransformationBootstrapBuilder withInputLoader(TransformerFileLoader<?> fileLoader, ClassFileLoader classFileLoader) {
        this.inputFile = fileLoader.loadFile(classFileLoader);
        return this;
    }

    @SneakyThrows
    public TransformationBootstrapBuilder withInputLoader(TransformerFileLoader<?> fileLoader) {
        return withInputLoader(fileLoader, new AsmClassFileLoader());
    }

    public TransformationBootstrapBuilder export(TransformerFileExporter fileExporter, ClassFileExporter classFileExporter) {
        fileExporter.exportFile(this.inputFile, classFileExporter);
        return this;
    }

    public TransformationBootstrapBuilder libraries(File... files) {
        return this;
    }

    public TransformationBootstrapBuilder export(TransformerFileExporter fileExporter) {
        return export(fileExporter, new FramelessClassFileExporter());
    }

    public TransformationBootstrapBuilder execute(Consumer<TransformerFile>... consumers) {
        for(Consumer<TransformerFile> consumer: consumers)
            consumer.accept(inputFile);
        return this;
    }

}
