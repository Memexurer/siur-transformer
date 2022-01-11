package pl.memexurer.siurtransformer.loader.file;

import pl.memexurer.siurtransformer.loader.clazz.ClassFileLoader;
import pl.memexurer.siurtransformer.loader.file.zip.ZipTransformerFileLoader;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

public interface TransformerFileLoader<T extends TransformerFile> {
    T loadFile(ClassFileLoader loader) throws TransformerFileLoadException;

    static TransformerFileLoader<? extends TransformerFile> getFileLoader(File file) throws TransformerFileLoadException {
        if(file.getName().endsWith(".zip") || file.getName().endsWith(".jar")) {
            try {
                return new ZipTransformerFileLoader(new ZipFile(file));
            } catch (IOException e) {
                throw new TransformerFileLoadException(e);
            }
        }
        else
            throw new TransformerFileLoadException("Unsupported file type!");
    }
}
