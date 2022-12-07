package ar.com.scienza.exception;

public class InvalidAffiliateBirthDateException extends Exception {

    private String message;

    public InvalidAffiliateBirthDateException(){
        this.message = "El formato de la fecha de nacimiento es incorrecto.";
    }

    public String getMessage() {
        return message;
    }
}
