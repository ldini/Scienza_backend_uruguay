package ar.com.scienza.dto;

import java.util.ArrayList;

public class QueryBatchDeleteResponseDto extends BodyDto{

    private String message;
    private ArrayList<String> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
