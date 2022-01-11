package pl.memexurer.siurtransformer.loader.clazz;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.InputStream;

public class AsmClassFileLoader implements ClassFileLoader {
    private final int flags;

    public AsmClassFileLoader(int flags) {
        this.flags = flags;
    }

    public AsmClassFileLoader() {
        this.flags = ClassReader.EXPAND_FRAMES;
    }

    @Override
    public ClassFile readFile(InputStream stream) throws ClassParseException {
        try {
            ClassNode node = new ClassNode();
            ClassReader reader = new ClassReader(stream);

            reader.accept(node, this.flags);
            return new ClassFile(reader, node);
        } catch (Throwable throwable) {
            throw new ClassParseException(throwable);
        }
    }
}
