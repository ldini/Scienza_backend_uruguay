package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationCreateResponseDto extends  BodyDto{

    @JsonProperty("idDocumentation")
    private Integer documentationId;

    public Integer getDocumentationId() {
        return documentationId;
    }

    public void setDocumentationId(Integer documentationId) {
        this.documentationId = documentationId;
    }
}
