package pl.memexurer.siurtransformer.loader.clazz;

import java.io.InputStream;

public interface ClassFileLoader {
    ClassFile readFile(InputStream stream) throws ClassParseException;
}
