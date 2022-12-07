package ar.com.scienza.enumerator;

public enum CoordinacionTipoDomicilioEnum {

    AFILIADO("AFI", "Afiliado"),
    FARMACIA("FARM", "Farmacia");

    private String code;
    private String description;

    private CoordinacionTipoDomicilioEnum(String codigo, String descripcion) {
        this.code = codigo;
        this.description = descripcion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CoordinacionTipoDomicilioEnum valueSAP(String value) throws Exception {

        if(value == null)
            return null;

        switch (value) {
            case "Afiliado":
                return AFILIADO;

            case "Farmacia":
                return FARMACIA;

            default:
                throw new Exception("Tipo de domicilio desconocido: " + value);

        }
    }
}
