package ar.com.scienza.exception;

public class InvalidAffiliateSexException extends Exception {

    private String message;

    public InvalidAffiliateSexException(){
        this.message = "El sexo del paciente es incorrecto.";
    }

    public String getMessage() {
        return message;
    }
}
