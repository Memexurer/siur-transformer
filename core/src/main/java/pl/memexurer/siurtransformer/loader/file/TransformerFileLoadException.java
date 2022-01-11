package pl.memexurer.siurtransformer.loader.file;

public class TransformerFileLoadException extends Exception {
    public TransformerFileLoadException(String message) {
        super(message);
    }

    public TransformerFileLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformerFileLoadException(Throwable cause) {
        super(cause);
    }
}
