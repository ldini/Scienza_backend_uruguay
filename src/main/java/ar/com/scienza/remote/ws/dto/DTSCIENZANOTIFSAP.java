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
 * <p>Clase Java para DT_SCIENZA_NOTIF_SAP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_NOTIF_SAP"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PACIENTE_ID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TITULO"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TEXTO"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TEXTO_NOTIFICACION_PUSH"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ACCION"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="MENSAJE"/&gt;
 *               &lt;enumeration value="DETALLE_ENTREGA"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DATO_CLAVE" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VENCIMIENTO" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
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
@XmlType(name = "DT_SCIENZA_NOTIF_SAP", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP", propOrder = {
    "pacienteid",
    "titulo",
    "texto",
    "textonotificacionpush",
    "accion",
    "datoclave",
    "vencimiento"
})
public class DTSCIENZANOTIFSAP {

    @XmlElement(name = "PACIENTE_ID", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP")
    protected long pacienteid;
    @XmlElement(name = "TITULO", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP", required = true)
    protected String titulo;
    @XmlElement(name = "TEXTO", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP", required = true)
    protected String texto;
    @XmlElement(name = "TEXTO_NOTIFICACION_PUSH", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP", required = true)
    protected String textonotificacionpush;
    @XmlElement(name = "ACCION", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP", required = true)
    protected String accion;
    @XmlElement(name = "DATO_CLAVE", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP")
    protected String datoclave;
    @XmlElement(name = "VENCIMIENTO", namespace = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP")
    protected String vencimiento;

    /**
     * Obtiene el valor de la propiedad pacienteid.
     * 
     */
    public long getPACIENTEID() {
        return pacienteid;
    }

    /**
     * Define el valor de la propiedad pacienteid.
     * 
     */
    public void setPACIENTEID(long value) {
        this.pacienteid = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITULO() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITULO(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad texto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTO() {
        return texto;
    }

    /**
     * Define el valor de la propiedad texto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTO(String value) {
        this.texto = value;
    }

    /**
     * Obtiene el valor de la propiedad textonotificacionpush.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTONOTIFICACIONPUSH() {
        return textonotificacionpush;
    }

    /**
     * Define el valor de la propiedad textonotificacionpush.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTONOTIFICACIONPUSH(String value) {
        this.textonotificacionpush = value;
    }

    /**
     * Obtiene el valor de la propiedad accion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACCION() {
        return accion;
    }

    /**
     * Define el valor de la propiedad accion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACCION(String value) {
        this.accion = value;
    }

    /**
     * Obtiene el valor de la propiedad datoclave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATOCLAVE() {
        return datoclave;
    }

    /**
     * Define el valor de la propiedad datoclave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATOCLAVE(String value) {
        this.datoclave = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENCIMIENTO() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENCIMIENTO(String value) {
        this.vencimiento = value;
    }

}
