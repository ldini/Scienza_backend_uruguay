//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.02.17 a las 10:42:45 AM ART 
//


package ar.com.scienza.remote.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_SCIENZA_NOTIF_ENT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_NOTIF_ENT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NRO_PEDIDO"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NRO_ENTREGA"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DIRECCION_ENTREGA"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DISTRIBUIDOR"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TIPO_VEHICULO"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PATENTE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AFILIADO_ID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;minInclusive value="10000000"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EVENTO_ID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_NOTIF_ENT", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT", propOrder = {
    "nropedido",
    "nroentrega",
    "direccionentrega",
    "distribuidor",
    "tipovehiculo",
    "patente",
    "afiliadoid",
    "eventoid"
})
public class DTSCIENZANOTIFENT {

    @XmlElement(name = "NRO_PEDIDO", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT")
    protected int nropedido;
    @XmlElement(name = "NRO_ENTREGA", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT")
    protected int nroentrega;
    @XmlElement(name = "DIRECCION_ENTREGA", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT", required = true)
    protected String direccionentrega;
    @XmlElement(name = "DISTRIBUIDOR", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT", required = true)
    protected String distribuidor;
    @XmlElement(name = "TIPO_VEHICULO", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT", required = true)
    protected String tipovehiculo;
    @XmlElement(name = "PATENTE", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT", required = true)
    protected String patente;
    @XmlElement(name = "AFILIADO_ID", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT")
    protected int afiliadoid;
    @XmlElement(name = "EVENTO_ID", namespace = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT")
    protected int eventoid;

    /**
     * Obtiene el valor de la propiedad nropedido.
     * 
     */
    public int getNROPEDIDO() {
        return nropedido;
    }

    /**
     * Define el valor de la propiedad nropedido.
     * 
     */
    public void setNROPEDIDO(int value) {
        this.nropedido = value;
    }

    /**
     * Obtiene el valor de la propiedad nroentrega.
     * 
     */
    public int getNROENTREGA() {
        return nroentrega;
    }

    /**
     * Define el valor de la propiedad nroentrega.
     * 
     */
    public void setNROENTREGA(int value) {
        this.nroentrega = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionentrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIRECCIONENTREGA() {
        return direccionentrega;
    }

    /**
     * Define el valor de la propiedad direccionentrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIRECCIONENTREGA(String value) {
        this.direccionentrega = value;
    }

    /**
     * Obtiene el valor de la propiedad distribuidor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISTRIBUIDOR() {
        return distribuidor;
    }

    /**
     * Define el valor de la propiedad distribuidor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISTRIBUIDOR(String value) {
        this.distribuidor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipovehiculo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOVEHICULO() {
        return tipovehiculo;
    }

    /**
     * Define el valor de la propiedad tipovehiculo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOVEHICULO(String value) {
        this.tipovehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad patente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPATENTE() {
        return patente;
    }

    /**
     * Define el valor de la propiedad patente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPATENTE(String value) {
        this.patente = value;
    }

    /**
     * Obtiene el valor de la propiedad afiliadoid.
     * 
     */
    public int getAFILIADOID() {
        return afiliadoid;
    }

    /**
     * Define el valor de la propiedad afiliadoid.
     * 
     */
    public void setAFILIADOID(int value) {
        this.afiliadoid = value;
    }

    /**
     * Obtiene el valor de la propiedad eventoid.
     * 
     */
    public int getEVENTOID() {
        return eventoid;
    }

    /**
     * Define el valor de la propiedad eventoid.
     * 
     */
    public void setEVENTOID(int value) {
        this.eventoid = value;
    }

}
