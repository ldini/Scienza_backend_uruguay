package ar.com.scienza.enumerator;

public enum MedioDePagoEnum {

    EFECTIVO("EFECTIVO", "Efectivo", "EFECTIVO"),
    TARJETA_CREDITO("TC", "Tarjeta de Crédito", "TC"),
    TARJETA_DEBITO("TD", "Tarjeta de Débito", "TD"),
    DEPOSITO_BANCARIO("DB", "Depósito Bancario", "DB"),
    TRANSFERENCIA("TRANSFERENCIA", "Transferencia", "TRANSFERENCIA"),
    RETIRO_FARMACIA("FARMACIA", "Pago en Farmacia", "FARMACIA");

    private String codigo;

    private String descripcion;

    private String codigoSap;


    /**
     * Obtiene el enumerador del medio de pago
     *
     * @param value
     * @return
     * @throws Exception
     */
    private MedioDePagoEnum(String codigo, String descripcion, String codigoSap) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoSap = codigoSap;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(String codigoSap) {
        this.codigoSap = codigoSap;
    }


    /**
     * Obtiene el enumerador del medio de pago
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static MedioDePagoEnum fromSAP(String value) throws Exception {

        if (value == null)
            return null;

        switch (value) {
            case "EFECTIVO":
                return MedioDePagoEnum.EFECTIVO;
            case "EFVO":
                return MedioDePagoEnum.EFECTIVO;

            case "TC":
                return MedioDePagoEnum.TARJETA_CREDITO;

            case "TD":
                return MedioDePagoEnum.TARJETA_DEBITO;

            case "DB":
                return MedioDePagoEnum.DEPOSITO_BANCARIO;
                
            case "TRANSFERENCIA":
                return MedioDePagoEnum.TRANSFERENCIA;
                
            case "FARMACIA":
                return MedioDePagoEnum.RETIRO_FARMACIA;

            default:
                throw new Exception("Medio de Pago desconocido: " + value);

        }
    }

}
