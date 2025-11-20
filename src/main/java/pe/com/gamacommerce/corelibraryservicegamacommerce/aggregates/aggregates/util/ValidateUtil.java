package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions.ValidateAppException;

public class ValidateUtil {
    public static void requerido(Object eval, GenericError errorCode) {
        requerido(eval, errorCode, null);
    }

    public static void requerido(Object eval, GenericError errorCode, String mensaje) {
        if (eval == null) {
            if (mensaje != null) {
                throw new ValidateAppException(errorCode, mensaje);
            } else {
                throw new ValidateAppException(errorCode);
            }
        }
    }

    public static void evaluar(boolean eval, GenericError errorCode) {
        evaluar(eval, errorCode, null);
    }

    public static void evaluar(boolean eval, GenericError errorCode, String mensaje) {
        if (!eval) {
            if (mensaje != null) {
                throw new ValidateAppException(errorCode, mensaje);
            } else {
                throw new ValidateAppException(errorCode);
            }
        }
    }

}
