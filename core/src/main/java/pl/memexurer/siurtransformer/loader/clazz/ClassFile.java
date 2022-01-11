package pl.memexurer.siurtransformer.loader.clazz;

import lombok.Data;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

@Data
public class ClassFile {
    private final ClassReader reader;
    private final ClassNode node;
}
