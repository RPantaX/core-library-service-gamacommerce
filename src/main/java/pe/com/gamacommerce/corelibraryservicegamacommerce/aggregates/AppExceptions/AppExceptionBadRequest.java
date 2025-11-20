package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

public class AppExceptionBadRequest extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public AppExceptionBadRequest(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
