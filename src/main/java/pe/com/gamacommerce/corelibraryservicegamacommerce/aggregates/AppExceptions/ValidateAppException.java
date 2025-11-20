package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.GenericError;

public class ValidateAppException extends AppException{

    private static final long serialVersionUID = 1L;
    public ValidateAppException(GenericError error, String message) {
        super(error, message);
    }
    public ValidateAppException(GenericError error) {
        super(error);
    }
}
