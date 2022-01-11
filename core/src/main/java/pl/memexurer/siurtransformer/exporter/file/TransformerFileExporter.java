package pl.memexurer.siurtransformer.exporter.file;

import pl.memexurer.siurtransformer.exporter.clazz.ClassFileExporter;
import pl.memexurer.siurtransformer.loader.clazz.ClassFile;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;

public interface TransformerFileExporter {
    void exportFile(TransformerFile file, ClassFileExporter exporter);
}
