package ar.com.scienza.entity;

import ar.com.scienza.base.ScienzaEntity;

import javax.persistence.*;

@Entity
@Table(name="observacion_alta_pendiente")
public class ObservacionAltaPendiente extends ScienzaEntity {

    @Column(name="texto", nullable = false, length = 500)
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alta_pendiente_id", nullable = false, referencedColumnName = "id")
    private BandejaAltaPendiente bandejaAltaPendiente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrador_id", nullable = false, referencedColumnName = "id")
    private Administrador administrador;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public BandejaAltaPendiente getBandejaAltaPendiente() {
        return bandejaAltaPendiente;
    }

    public void setBandejaAltaPendiente(BandejaAltaPendiente bandejaAltaPendiente) {
        this.bandejaAltaPendiente = bandejaAltaPendiente;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

}
