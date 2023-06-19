package pl.memexurer.siurtransformer.exporter.file.zip;

import pl.memexurer.siurtransformer.exporter.clazz.ClassFileExporter;
import pl.memexurer.siurtransformer.exporter.file.TransformerFileExporter;
import pl.memexurer.siurtransformer.loader.clazz.ClassFile;
import pl.memexurer.siurtransformer.loader.file.TransformerFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTransformerFileExporter implements TransformerFileExporter {
    private final File outputFile;

    public ZipTransformerFileExporter(File outputFile) {
        this.outputFile = outputFile;
    }

    private static byte[] readInputStream(InputStream stream) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            return buffer.toByteArray();
        }
    }

    @Override
    public void exportFile(TransformerFile file, ClassFileExporter exporter) {
        try (ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(outputFile))) {
            for (TransformerFile.Resource resource : file.getResources()) {
                outputStream.putNextEntry(new ZipEntry(resource.getName()));
                outputStream.write(readInputStream(resource.getContents()));
                outputStream.closeEntry();
            }

            for (TransformerFile.Resource resource : file.getBrokenClasses()) {
                outputStream.putNextEntry(new ZipEntry(resource.getName()));
                outputStream.write(readInputStream(resource.getContents()));
                outputStream.closeEntry();
            }

            for (TransformerFile.Class clazz : file.getClasses()) {
                outputStream.putNextEntry(new ZipEntry(clazz.getName() + ".class"));
                outputStream.write(exporter.export(new ClassFile(clazz.getClassReader(), clazz.getParsedNode())));
                outputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
