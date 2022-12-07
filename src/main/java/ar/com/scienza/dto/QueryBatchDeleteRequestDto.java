package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class QueryBatchDeleteRequestDto {

    @JsonProperty("queries")
    private ArrayList<Integer> queries;

    public ArrayList<Integer> getQueries() {
        return queries;
    }

}
