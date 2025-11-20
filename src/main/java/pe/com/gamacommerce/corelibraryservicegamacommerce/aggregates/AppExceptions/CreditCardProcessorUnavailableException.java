package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

public class CreditCardProcessorUnavailableException extends RuntimeException {
    public CreditCardProcessorUnavailableException(Throwable cause) {
        super(cause);
    }
}
