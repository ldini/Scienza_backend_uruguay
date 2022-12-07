package ar.com.scienza.transformer;

import java.util.Date;

public class TramiteTRF {

    private Integer id;

    private Date fechaInsert;


    public TramiteTRF(Integer id, Date fechaInsert) {
        super();
        this.id = id;
        this.fechaInsert = fechaInsert;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }
}
