//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.02.17 a las 10:42:45 AM ART 
//


package ar.com.scienza.remote.ws.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_SCIENZA_IND_PEDIDOS_UY_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_IND_PEDIDOS_UY_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="COLECCION_DE_PEDIDOS" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="PEDIDO_NUMERO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PEDIDO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "DT_SCIENZA_IND_PEDIDOS_UY_Response", namespace = "urn:scienza.com.ar:Indice_Pedidos_UY", propOrder = {
    "colecciondepedidos"
})
public class DTSCIENZAINDPEDIDOSUYResponse {

    @XmlElement(name = "COLECCION_DE_PEDIDOS")
    protected List<DTSCIENZAINDPEDIDOSUYResponse.COLECCIONDEPEDIDOS> colecciondepedidos;

    /**
     * Gets the value of the colecciondepedidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colecciondepedidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOLECCIONDEPEDIDOS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCIENZAINDPEDIDOSUYResponse.COLECCIONDEPEDIDOS }
     * 
     * 
     */
    public List<DTSCIENZAINDPEDIDOSUYResponse.COLECCIONDEPEDIDOS> getCOLECCIONDEPEDIDOS() {
        if (colecciondepedidos == null) {
            colecciondepedidos = new ArrayList<DTSCIENZAINDPEDIDOSUYResponse.COLECCIONDEPEDIDOS>();
        }
        return this.colecciondepedidos;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="PEDIDO_NUMERO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PEDIDO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pedidonumero",
        "pedidofecha"
    })
    public static class COLECCIONDEPEDIDOS {

        @XmlElement(name = "PEDIDO_NUMERO")
        protected String pedidonumero;
        @XmlElement(name = "PEDIDO_FECHA")
        protected String pedidofecha;

        /**
         * Obtiene el valor de la propiedad pedidonumero.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPEDIDONUMERO() {
            return pedidonumero;
        }

        /**
         * Define el valor de la propiedad pedidonumero.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPEDIDONUMERO(String value) {
            this.pedidonumero = value;
        }

        /**
         * Obtiene el valor de la propiedad pedidofecha.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPEDIDOFECHA() {
            return pedidofecha;
        }

        /**
         * Define el valor de la propiedad pedidofecha.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPEDIDOFECHA(String value) {
            this.pedidofecha = value;
        }

    }

}
