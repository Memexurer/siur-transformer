package pl.memexurer.siurtransformer.util;

import java.util.zip.ZipEntry;

public final class ClassMatcher {
    private ClassMatcher() {
    }

    public static boolean isClassFile(ZipEntry entry) {
        return entry.getName().endsWith(".class");
    }
}
