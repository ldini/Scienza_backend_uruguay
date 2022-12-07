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
 * <p>Clase Java para DT_SCIENZA_Busqueda_Dispone complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_Busqueda_Dispone"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID_AFILIADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUMERO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_Busqueda_Dispone", namespace = "urn:scienza.com.ar:Busqueda_Dispone", propOrder = {
    "idafiliado",
    "numeropedido"
})
public class DTSCIENZABusquedaDispone {

    @XmlElement(name = "ID_AFILIADO")
    protected String idafiliado;
    @XmlElement(name = "NUMERO_PEDIDO")
    protected String numeropedido;

    /**
     * Obtiene el valor de la propiedad idafiliado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDAFILIADO() {
        return idafiliado;
    }

    /**
     * Define el valor de la propiedad idafiliado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDAFILIADO(String value) {
        this.idafiliado = value;
    }

    /**
     * Obtiene el valor de la propiedad numeropedido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMEROPEDIDO() {
        return numeropedido;
    }

    /**
     * Define el valor de la propiedad numeropedido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMEROPEDIDO(String value) {
        this.numeropedido = value;
    }

}
