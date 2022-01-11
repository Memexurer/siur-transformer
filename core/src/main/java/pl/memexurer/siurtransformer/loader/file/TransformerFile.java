package pl.memexurer.siurtransformer.loader.file;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public interface TransformerFile {
    Collection<? extends Class> getClasses();

    Collection<? extends Resource> getResources();

    Collection<? extends BrokenClass> getBrokenClasses();

    default <T extends Resource> T resolveResource(String name) {
        for (Resource resource : getResources())
            if (resource.getName().equals(name))
                return (T) resource;
        return null;
    }

    default <T extends Class> T resolveClass(String name) {
        for (Class clazz : getClasses())
            if (clazz.getName().equals(name))
                return (T) clazz;
        return null;
    }

    default <T extends BrokenClass> T resolveBrokenClass(String name) {
        for (BrokenClass clazz : getBrokenClasses())
            if (clazz.getName().equals(name))
                return (T) clazz;
        return null;
    }

    interface Resource {
        InputStream getContents() throws IOException;

        String getName();
    }

    interface BrokenClass extends Resource {
        Throwable getError();
    }

    interface Class extends Resource {
        ClassReader getClassReader();

        ClassNode getParsedNode();
    }
}
