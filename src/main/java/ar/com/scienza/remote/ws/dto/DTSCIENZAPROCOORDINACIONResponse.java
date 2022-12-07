//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.02.17 a las 10:42:45 AM ART 
//


package ar.com.scienza.remote.ws.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DT_SCIENZA_PRO_COORDINACION_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_PRO_COORDINACION_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID_AFILIADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUMERO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="STATUS_PROPUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TEXTO_STATUS_PROPUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IMPORTE_A_ABONAR" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;totalDigits value="17"/&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LIMITE_PAGO_EFECTIVO" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;totalDigits value="17"/&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ID_PROPUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="POSICIONES" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="ID_MATERIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CANTIDAD_A_ENTREGAR" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MARCA_DOMICILIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DOMICILIOS" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TIPO_DOMICILIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ID_DESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="TIPO_AGENTE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="NOMBRE_DE_DISPONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DIRECCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="FECHAS" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                             &lt;element name="TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="TEXTO_TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="TEXTO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_SCIENZA_PRO_COORDINACION_Response", namespace = "urn:scienza.com.ar:Propuesta_Coordinacion", propOrder = {
    "idafiliado",
    "numeropedido",
    "statuspropuesta",
    "textostatuspropuesta",
    "importeaabonar",
    "limitepagoefectivo",
    "idpropuesta",
    "posiciones",
    "marcadomicilio",
    "domicilios"
})
public class DTSCIENZAPROCOORDINACIONResponse {

    @XmlElement(name = "ID_AFILIADO")
    protected String idafiliado;
    @XmlElement(name = "NUMERO_PEDIDO")
    protected String numeropedido;
    @XmlElement(name = "STATUS_PROPUESTA")
    protected String statuspropuesta;
    @XmlElement(name = "TEXTO_STATUS_PROPUESTA")
    protected String textostatuspropuesta;
    @XmlElement(name = "IMPORTE_A_ABONAR")
    protected BigDecimal importeaabonar;
    @XmlElement(name = "LIMITE_PAGO_EFECTIVO")
    protected BigDecimal limitepagoefectivo;
    @XmlElement(name = "ID_PROPUESTA")
    protected String idpropuesta;
    @XmlElement(name = "POSICIONES", required = true)
    protected List<DTSCIENZAPROCOORDINACIONResponse.POSICIONES> posiciones;
    @XmlElement(name = "MARCA_DOMICILIO")
    protected String marcadomicilio;
    @XmlElement(name = "DOMICILIOS", required = true)
    protected List<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS> domicilios;

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

    /**
     * Obtiene el valor de la propiedad statuspropuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUSPROPUESTA() {
        return statuspropuesta;
    }

    /**
     * Define el valor de la propiedad statuspropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUSPROPUESTA(String value) {
        this.statuspropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad textostatuspropuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTOSTATUSPROPUESTA() {
        return textostatuspropuesta;
    }

    /**
     * Define el valor de la propiedad textostatuspropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTOSTATUSPROPUESTA(String value) {
        this.textostatuspropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad importeaabonar.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTEAABONAR() {
        return importeaabonar;
    }

    /**
     * Define el valor de la propiedad importeaabonar.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTEAABONAR(BigDecimal value) {
        this.importeaabonar = value;
    }

    /**
     * Obtiene el valor de la propiedad limitepagoefectivo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLIMITEPAGOEFECTIVO() {
        return limitepagoefectivo;
    }

    /**
     * Define el valor de la propiedad limitepagoefectivo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLIMITEPAGOEFECTIVO(BigDecimal value) {
        this.limitepagoefectivo = value;
    }

    /**
     * Obtiene el valor de la propiedad idpropuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPROPUESTA() {
        return idpropuesta;
    }

    /**
     * Define el valor de la propiedad idpropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPROPUESTA(String value) {
        this.idpropuesta = value;
    }

    /**
     * Gets the value of the posiciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the posiciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOSICIONES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCIENZAPROCOORDINACIONResponse.POSICIONES }
     * 
     * 
     */
    public List<DTSCIENZAPROCOORDINACIONResponse.POSICIONES> getPOSICIONES() {
        if (posiciones == null) {
            posiciones = new ArrayList<DTSCIENZAPROCOORDINACIONResponse.POSICIONES>();
        }
        return this.posiciones;
    }

    /**
     * Obtiene el valor de la propiedad marcadomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMARCADOMICILIO() {
        return marcadomicilio;
    }

    /**
     * Define el valor de la propiedad marcadomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMARCADOMICILIO(String value) {
        this.marcadomicilio = value;
    }

    /**
     * Gets the value of the domicilios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domicilios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDOMICILIOS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS }
     * 
     * 
     */
    public List<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS> getDOMICILIOS() {
        if (domicilios == null) {
            domicilios = new ArrayList<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS>();
        }
        return this.domicilios;
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
     *         &lt;element name="TIPO_DOMICILIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ID_DESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="TIPO_AGENTE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="NOMBRE_DE_DISPONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DIRECCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="FECHAS" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                   &lt;element name="TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="TEXTO_TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="TEXTO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "tipodomicilio",
        "iddestinatario",
        "tipoagente",
        "nombrededispone",
        "direccion",
        "fechas"
    })
    public static class DOMICILIOS {

        @XmlElement(name = "TIPO_DOMICILIO")
        protected String tipodomicilio;
        @XmlElement(name = "ID_DESTINATARIO")
        protected BigInteger iddestinatario;
        @XmlElement(name = "TIPO_AGENTE")
        protected BigInteger tipoagente;
        @XmlElement(name = "NOMBRE_DE_DISPONE")
        protected String nombrededispone;
        @XmlElement(name = "DIRECCION")
        protected String direccion;
        @XmlElement(name = "FECHAS")
        protected List<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS> fechas;

        /**
         * Obtiene el valor de la propiedad tipodomicilio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTIPODOMICILIO() {
            return tipodomicilio;
        }

        /**
         * Define el valor de la propiedad tipodomicilio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTIPODOMICILIO(String value) {
            this.tipodomicilio = value;
        }

        /**
         * Obtiene el valor de la propiedad iddestinatario.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getIDDESTINATARIO() {
            return iddestinatario;
        }

        /**
         * Define el valor de la propiedad iddestinatario.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setIDDESTINATARIO(BigInteger value) {
            this.iddestinatario = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoagente.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTIPOAGENTE() {
            return tipoagente;
        }

        /**
         * Define el valor de la propiedad tipoagente.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTIPOAGENTE(BigInteger value) {
            this.tipoagente = value;
        }

        /**
         * Obtiene el valor de la propiedad nombrededispone.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNOMBREDEDISPONE() {
            return nombrededispone;
        }

        /**
         * Define el valor de la propiedad nombrededispone.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNOMBREDEDISPONE(String value) {
            this.nombrededispone = value;
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
         * Gets the value of the fechas property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fechas property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFECHAS().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS }
         * 
         * 
         */
        public List<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS> getFECHAS() {
            if (fechas == null) {
                fechas = new ArrayList<DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS>();
            }
            return this.fechas;
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
         *         &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *         &lt;element name="TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="TEXTO_TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="TEXTO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "fechaentrega",
            "turno",
            "textoturno",
            "textofecha",
            "vencimiento"
        })
        public static class FECHAS {

            @XmlElement(name = "FECHA_ENTREGA")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fechaentrega;
            @XmlElement(name = "TURNO")
            protected String turno;
            @XmlElement(name = "TEXTO_TURNO")
            protected String textoturno;
            @XmlElement(name = "TEXTO_FECHA")
            protected String textofecha;
            @XmlElement(name = "VENCIMIENTO")
            protected String vencimiento;

            /**
             * Obtiene el valor de la propiedad fechaentrega.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFECHAENTREGA() {
                return fechaentrega;
            }

            /**
             * Define el valor de la propiedad fechaentrega.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFECHAENTREGA(XMLGregorianCalendar value) {
                this.fechaentrega = value;
            }

            /**
             * Obtiene el valor de la propiedad turno.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTURNO() {
                return turno;
            }

            /**
             * Define el valor de la propiedad turno.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTURNO(String value) {
                this.turno = value;
            }

            /**
             * Obtiene el valor de la propiedad textoturno.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTEXTOTURNO() {
                return textoturno;
            }

            /**
             * Define el valor de la propiedad textoturno.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTEXTOTURNO(String value) {
                this.textoturno = value;
            }

            /**
             * Obtiene el valor de la propiedad textofecha.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTEXTOFECHA() {
                return textofecha;
            }

            /**
             * Define el valor de la propiedad textofecha.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTEXTOFECHA(String value) {
                this.textofecha = value;
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
     *         &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="ID_MATERIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CANTIDAD_A_ENTREGAR" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "posicion",
        "idmaterial",
        "descripcion",
        "monodroga",
        "cantidadaentregar",
        "laboratorio"
    })
    public static class POSICIONES {

        @XmlElement(name = "POSICION")
        protected BigInteger posicion;
        @XmlElement(name = "ID_MATERIAL")
        protected String idmaterial;
        @XmlElement(name = "DESCRIPCION")
        protected String descripcion;
        @XmlElement(name = "MONODROGA")
        protected String monodroga;
        @XmlElement(name = "CANTIDAD_A_ENTREGAR")
        protected BigInteger cantidadaentregar;
        @XmlElement(name = "LABORATORIO")
        protected String laboratorio;

        /**
         * Obtiene el valor de la propiedad posicion.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPOSICION() {
            return posicion;
        }

        /**
         * Define el valor de la propiedad posicion.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPOSICION(BigInteger value) {
            this.posicion = value;
        }

        /**
         * Obtiene el valor de la propiedad idmaterial.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIDMATERIAL() {
            return idmaterial;
        }

        /**
         * Define el valor de la propiedad idmaterial.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIDMATERIAL(String value) {
            this.idmaterial = value;
        }

        /**
         * Obtiene el valor de la propiedad descripcion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESCRIPCION() {
            return descripcion;
        }

        /**
         * Define el valor de la propiedad descripcion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESCRIPCION(String value) {
            this.descripcion = value;
        }

        /**
         * Obtiene el valor de la propiedad monodroga.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMONODROGA() {
            return monodroga;
        }

        /**
         * Define el valor de la propiedad monodroga.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMONODROGA(String value) {
            this.monodroga = value;
        }

        /**
         * Obtiene el valor de la propiedad cantidadaentregar.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCANTIDADAENTREGAR() {
            return cantidadaentregar;
        }

        /**
         * Define el valor de la propiedad cantidadaentregar.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCANTIDADAENTREGAR(BigInteger value) {
            this.cantidadaentregar = value;
        }

        /**
         * Obtiene el valor de la propiedad laboratorio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLABORATORIO() {
            return laboratorio;
        }

        /**
         * Define el valor de la propiedad laboratorio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLABORATORIO(String value) {
            this.laboratorio = value;
        }

    }

}
