package pl.memexurer.siurtransformer.exporter.clazz;

import pl.memexurer.siurtransformer.loader.clazz.ClassFile;

public interface ClassFileExporter {
    byte[] export(ClassFile classFile);
}
