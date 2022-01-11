package pl.memexurer.siurtransformer.loader.file.zip;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import pl.memexurer.siurtransformer.loader.clazz.ClassFileLoader;
import pl.memexurer.siurtransformer.loader.clazz.ClassParseException;
import pl.memexurer.siurtransformer.loader.file.TransformerFileLoader;
import pl.memexurer.siurtransformer.loader.file.TransformerFileLoadException;
import pl.memexurer.siurtransformer.util.ClassMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@AllArgsConstructor
public class ZipTransformerFileLoader implements TransformerFileLoader<ZipTransformerFile> {
    protected final ZipFile file;

    @SneakyThrows(IOException.class)
    @Override
    public ZipTransformerFile loadFile(ClassFileLoader loader) throws TransformerFileLoadException {
        if (file == null)
            throw new TransformerFileLoadException("File is not specified!");

        Set<ZipTransformerFile.ZipClass> classes = new HashSet<>();
        Set<ZipTransformerFile.ZipResource> resources = new HashSet<>();
        Set<ZipTransformerFile.ZipBrokenClass> brokenClasses = new HashSet<>();

        ZipTransformerFile transformerFile = new ZipTransformerFile(
                file,
                classes,
                resources,
                brokenClasses
        );

        Enumeration<? extends ZipEntry> enumeration = file.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry entry = enumeration.nextElement();
            InputStream stream = file.getInputStream(entry);

            if(ClassMatcher.isClassFile(entry))
            {
                try {
                    classes.add(transformerFile.new ZipClass(entry, loader.readFile(stream)));
                } catch (ClassParseException exception) {
                     brokenClasses.add(transformerFile.new ZipBrokenClass(entry, exception));
                }
            } else {
                resources.add(transformerFile.new ZipResource(entry));
            }
        }

        return transformerFile;
    }
}
