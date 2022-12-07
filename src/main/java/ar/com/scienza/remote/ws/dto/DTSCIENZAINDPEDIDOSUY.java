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
 * <p>Clase Java para DT_SCIENZA_IND_PEDIDOS_UY complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_IND_PEDIDOS_UY"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PACIENTE_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PEDIDO_DESDE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ORDEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PEDIDOS_CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_IND_PEDIDOS_UY", namespace = "urn:scienza.com.ar:Indice_Pedidos_UY", propOrder = {
    "pacienteid",
    "pedidodesde",
    "orden",
    "pedidoscantidad"
})
public class DTSCIENZAINDPEDIDOSUY {

    @XmlElement(name = "PACIENTE_ID")
    protected String pacienteid;
    @XmlElement(name = "PEDIDO_DESDE")
    protected String pedidodesde;
    @XmlElement(name = "ORDEN")
    protected String orden;
    @XmlElement(name = "PEDIDOS_CANTIDAD")
    protected String pedidoscantidad;

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
     * Obtiene el valor de la propiedad pedidodesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEDIDODESDE() {
        return pedidodesde;
    }

    /**
     * Define el valor de la propiedad pedidodesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEDIDODESDE(String value) {
        this.pedidodesde = value;
    }

    /**
     * Obtiene el valor de la propiedad orden.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDEN() {
        return orden;
    }

    /**
     * Define el valor de la propiedad orden.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDEN(String value) {
        this.orden = value;
    }

    /**
     * Obtiene el valor de la propiedad pedidoscantidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEDIDOSCANTIDAD() {
        return pedidoscantidad;
    }

    /**
     * Define el valor de la propiedad pedidoscantidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEDIDOSCANTIDAD(String value) {
        this.pedidoscantidad = value;
    }

}
