package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions.TypeException;

public interface GenericError {
    String getTitle();

    String getCode();

    String getMessage();

    TypeException getType();

}
