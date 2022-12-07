package ar.com.scienza.exception;

public class InvalidAffiliateStreetNumberException extends Exception {

    private String message;

    public InvalidAffiliateStreetNumberException(){
        this.message = "El formato del número de la dirección es incorrecto.";
    }

    public String getMessage() {
        return message;
    }
}
