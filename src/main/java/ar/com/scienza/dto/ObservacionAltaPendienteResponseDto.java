package ar.com.scienza.dto;

public class ObservacionAltaPendienteResponseDto extends BodyDto {

    private String texto;

    private String nombreAdministrador;

    private String apellidoAdministrador;

    private String fecha;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getApellidoAdministrador() {
        return apellidoAdministrador;
    }

    public void setApellidoAdministrador(String apellidoAdministrador) {
        this.apellidoAdministrador = apellidoAdministrador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}
