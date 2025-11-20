package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

public enum TypeException {
    I("Information"), W("WARNING"), E("Error");

    private final String value;

    private TypeException(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
