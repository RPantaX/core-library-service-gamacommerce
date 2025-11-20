package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.GenericError;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String codigo;
    private final TypeException type;

    public AppException(GenericError error) {
        super(error.getMessage());
        this.codigo = error.getCode();
        this.type = error.getType();

    }
    public AppException(GenericError error, String message, Throwable cause) {
        super(message, cause);
        this.codigo = error.getCode();
        this.type = error.getType();

    }
    public AppException(GenericError error, String message) {
        super(message);
        this.codigo = error.getCode();
        this.type = error.getType();


    }
    public String getCodigo() {
        return codigo;
    }

    public TypeException getType() {
        return type;
    }

}
