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
 * <p>Clase Java para DT_SCIENZA_REG_USUARIO_UY complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_REG_USUARIO_UY"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PACIENTE_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_CI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="REGISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_REG_USUARIO_UY", namespace = "urn:scienza.com.ar:Registro_Usuarios_UY", propOrder = {
    "pacienteid",
    "pacienteci",
    "registracion"
})
public class DTSCIENZAREGUSUARIOUY {

    @XmlElement(name = "PACIENTE_ID")
    protected String pacienteid;
    @XmlElement(name = "PACIENTE_CI")
    protected String pacienteci;
    @XmlElement(name = "REGISTRACION")
    protected String registracion;

    /**
     * Obtiene el valor de la propiedad pacienteid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEID() {
        return pacienteid;
    }

    /**
     * Define el valor de la propiedad pacienteid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEID(String value) {
        this.pacienteid = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteci.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTECI() {
        return pacienteci;
    }

    /**
     * Define el valor de la propiedad pacienteci.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTECI(String value) {
        this.pacienteci = value;
    }

    /**
     * Obtiene el valor de la propiedad registracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGISTRACION() {
        return registracion;
    }

    /**
     * Define el valor de la propiedad registracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGISTRACION(String value) {
        this.registracion = value;
    }

}
