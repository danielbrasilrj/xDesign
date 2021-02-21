package uk.co.xdesign.munros.exeption;

public class FilterException extends RuntimeException {

    public FilterException(String errorMessage) {
        super(errorMessage);
    }

    public FilterException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
