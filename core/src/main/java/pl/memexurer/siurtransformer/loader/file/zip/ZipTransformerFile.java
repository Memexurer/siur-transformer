package pl.memexurer.siurtransformer.loader.file.zip;

import lombok.Data;
import lombok.Getter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import pl.memexurer.siurtransformer.loader.clazz.ClassFile;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Getter
public class ZipTransformerFile implements TransformerFile {
    private final ZipFile file;

    private final Collection<ZipClass> classes;
    private final Collection<ZipResource> resources;
    private final Collection<ZipBrokenClass> brokenClasses;

    ZipTransformerFile(ZipFile file, Collection<ZipClass> classes, Collection<ZipResource> resources, Collection<ZipBrokenClass> brokenClasses) {
        this.file = file;
        this.classes = classes;
        this.resources = resources;
        this.brokenClasses = brokenClasses;
    }

    @Data
    public class ZipResource implements Resource {
        private final ZipEntry entry;

        @Override
        public InputStream getContents() throws IOException {
            return file.getInputStream(entry);
        }

        @Override
        public String getName() {
            return entry.getName();
        }
    }

    @Getter
    public class ZipBrokenClass extends ZipResource implements BrokenClass {
        private final Throwable error;

        public ZipBrokenClass(ZipEntry entry, Throwable error) {
            super(entry);
            this.error = error;
        }
    }

    @Getter
    public class ZipClass extends ZipResource implements Class {
        private final ClassReader classReader;
        private final ClassNode parsedNode;

        public ZipClass(ZipEntry entry, ClassFile file) {
            super(entry);
            this.classReader = file.getReader();
            this.parsedNode = file.getNode();
        }
    }
}
