//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.02.17 a las 10:42:45 AM ART 
//


package ar.com.scienza.remote.ws.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_SCIENZA_Busqueda_Dispone_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_Busqueda_Dispone_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Dispones" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Dispone" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ID_DISPONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="DISPONE_PREFERIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="DIRECCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="TEXTOS" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="LATITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                             &lt;element name="LONGITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "DT_SCIENZA_Busqueda_Dispone_Response", namespace = "urn:scienza.com.ar:Busqueda_Dispone", propOrder = {
    "dispones"
})
public class DTSCIENZABusquedaDisponeResponse {

    @XmlElement(name = "Dispones")
    protected DTSCIENZABusquedaDisponeResponse.Dispones dispones;

    /**
     * Obtiene el valor de la propiedad dispones.
     * 
     * @return
     *     possible object is
     *     {@link DTSCIENZABusquedaDisponeResponse.Dispones }
     *     
     */
    public DTSCIENZABusquedaDisponeResponse.Dispones getDispones() {
        return dispones;
    }

    /**
     * Define el valor de la propiedad dispones.
     * 
     * @param value
     *     allowed object is
     *     {@link DTSCIENZABusquedaDisponeResponse.Dispones }
     *     
     */
    public void setDispones(DTSCIENZABusquedaDisponeResponse.Dispones value) {
        this.dispones = value;
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
     *         &lt;element name="Dispone" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ID_DISPONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="DISPONE_PREFERIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="DIRECCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="TEXTOS" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="LATITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *                   &lt;element name="LONGITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
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
    @XmlType(name = "", propOrder = {
        "dispone"
    })
    public static class Dispones {

        @XmlElement(name = "Dispone")
        protected List<DTSCIENZABusquedaDisponeResponse.Dispones.Dispone> dispone;

        /**
         * Gets the value of the dispone property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dispone property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDispone().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTSCIENZABusquedaDisponeResponse.Dispones.Dispone }
         * 
         * 
         */
        public List<DTSCIENZABusquedaDisponeResponse.Dispones.Dispone> getDispone() {
            if (dispone == null) {
                dispone = new ArrayList<DTSCIENZABusquedaDisponeResponse.Dispones.Dispone>();
            }
            return this.dispone;
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
         *         &lt;element name="ID_DISPONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="DISPONE_PREFERIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="DIRECCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="TEXTOS" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="LATITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
         *         &lt;element name="LONGITUD" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
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
            "iddispone",
            "disponepreferido",
            "nombre",
            "direccion",
            "textos",
            "latitud",
            "longitud"
        })
        public static class Dispone {

            @XmlElement(name = "ID_DISPONE")
            protected String iddispone;
            @XmlElement(name = "DISPONE_PREFERIDO")
            protected String disponepreferido;
            @XmlElement(name = "NOMBRE")
            protected String nombre;
            @XmlElement(name = "DIRECCION")
            protected String direccion;
            @XmlElement(name = "TEXTOS")
            protected DTSCIENZABusquedaDisponeResponse.Dispones.Dispone.TEXTOS textos;
            @XmlElement(name = "LATITUD")
            protected BigDecimal latitud;
            @XmlElement(name = "LONGITUD")
            protected BigDecimal longitud;

            /**
             * Obtiene el valor de la propiedad iddispone.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIDDISPONE() {
                return iddispone;
            }

            /**
             * Define el valor de la propiedad iddispone.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIDDISPONE(String value) {
                this.iddispone = value;
            }

            /**
             * Obtiene el valor de la propiedad disponepreferido.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDISPONEPREFERIDO() {
                return disponepreferido;
            }

            /**
             * Define el valor de la propiedad disponepreferido.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDISPONEPREFERIDO(String value) {
                this.disponepreferido = value;
            }

            /**
             * Obtiene el valor de la propiedad nombre.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBRE() {
                return nombre;
            }

            /**
             * Define el valor de la propiedad nombre.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBRE(String value) {
                this.nombre = value;
            }

            /**
             * Obtiene el valor de la propiedad direccion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDIRECCION() {
                return direccion;
            }

            /**
             * Define el valor de la propiedad direccion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDIRECCION(String value) {
                this.direccion = value;
            }

            /**
             * Obtiene el valor de la propiedad textos.
             * 
             * @return
             *     possible object is
             *     {@link DTSCIENZABusquedaDisponeResponse.Dispones.Dispone.TEXTOS }
             *     
             */
            public DTSCIENZABusquedaDisponeResponse.Dispones.Dispone.TEXTOS getTEXTOS() {
                return textos;
            }

            /**
             * Define el valor de la propiedad textos.
             * 
             * @param value
             *     allowed object is
             *     {@link DTSCIENZABusquedaDisponeResponse.Dispones.Dispone.TEXTOS }
             *     
             */
            public void setTEXTOS(DTSCIENZABusquedaDisponeResponse.Dispones.Dispone.TEXTOS value) {
                this.textos = value;
            }

            /**
             * Obtiene el valor de la propiedad latitud.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLATITUD() {
                return latitud;
            }

            /**
             * Define el valor de la propiedad latitud.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLATITUD(BigDecimal value) {
                this.latitud = value;
            }

            /**
             * Obtiene el valor de la propiedad longitud.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLONGITUD() {
                return longitud;
            }

            /**
             * Define el valor de la propiedad longitud.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLONGITUD(BigDecimal value) {
                this.longitud = value;
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
             *         &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
                "texto"
            })
            public static class TEXTOS {

                @XmlElement(name = "TEXTO")
                protected List<String> texto;

                /**
                 * Gets the value of the texto property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the texto property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTEXTO().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getTEXTO() {
                    if (texto == null) {
                        texto = new ArrayList<String>();
                    }
                    return this.texto;
                }

            }

        }

    }

}
