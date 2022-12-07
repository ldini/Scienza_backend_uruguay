package ar.com.scienza.exception;

public class AffiliateCellphoneCompanyNotFoundException extends Exception {

    private String message;

    public AffiliateCellphoneCompanyNotFoundException(String nombreCompaniaCelular) {
        this.message = "La compañía de teléfono celular " + nombreCompaniaCelular + " no se encuentra registrada en nuestra base de datos.";
    }

    public String getMessage() {
        return message;
    }
}
