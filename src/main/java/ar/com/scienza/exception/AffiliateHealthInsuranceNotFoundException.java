package ar.com.scienza.exception;

public class AffiliateHealthInsuranceNotFoundException extends Exception{

    private String message;

    public AffiliateHealthInsuranceNotFoundException(String idObraSocial){
        this.message = "La obra social con ID " + idObraSocial + " no se encuentra registrada en nuestra base de datos para el uso de la plataforma mobile. Solicitar inclusión.";
    }

    public String getMessage() {
        return message;
    }
}
