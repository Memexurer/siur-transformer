package pl.memexurer.siurtransformer.exporter.clazz;

import org.objectweb.asm.ClassWriter;
import pl.memexurer.siurtransformer.loader.clazz.ClassFile;

public class FramelessClassFileExporter implements ClassFileExporter {
    @Override
    public byte[] export(ClassFile classFile) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classFile.getNode().accept(writer);
        return writer.toByteArray();
    }
}
