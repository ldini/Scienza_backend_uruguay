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
 * <p>Clase Java para DT_SCIENZA_CONSULTA_TRAZAS_UY_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_CONSULTA_TRAZAS_UY_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DATOS" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="PRODUCTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PAIS_ORIGEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="INFO_ORIGEN" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="COMPROBANTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="FECHA_HORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="INFO_DESTINO" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="DESTINO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="NRO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="REMITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_SCIENZA_CONSULTA_TRAZAS_UY_Response", namespace = "urn:scienza.com.ar:Consulta_Trazas_UY", propOrder = {
    "status",
    "datos"
})
public class DTSCIENZACONSULTATRAZASUYResponse {

    @XmlElement(name = "STATUS")
    protected String status;
    @XmlElement(name = "DATOS")
    protected DTSCIENZACONSULTATRAZASUYResponse.DATOS datos;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad datos.
     * 
     * @return
     *     possible object is
     *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS }
     *     
     */
    public DTSCIENZACONSULTATRAZASUYResponse.DATOS getDATOS() {
        return datos;
    }

    /**
     * Define el valor de la propiedad datos.
     * 
     * @param value
     *     allowed object is
     *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS }
     *     
     */
    public void setDATOS(DTSCIENZACONSULTATRAZASUYResponse.DATOS value) {
        this.datos = value;
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
     *         &lt;element name="PRODUCTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PAIS_ORIGEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="INFO_ORIGEN" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="COMPROBANTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="FECHA_HORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="INFO_DESTINO" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="DESTINO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="NRO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="REMITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "producto",
        "laboratorio",
        "lote",
        "vencimiento",
        "paisorigen",
        "infoorigen",
        "infodestino"
    })
    public static class DATOS {

        @XmlElement(name = "PRODUCTO")
        protected String producto;
        @XmlElement(name = "LABORATORIO")
        protected String laboratorio;
        @XmlElement(name = "LOTE")
        protected String lote;
        @XmlElement(name = "VENCIMIENTO")
        protected String vencimiento;
        @XmlElement(name = "PAIS_ORIGEN")
        protected String paisorigen;
        @XmlElement(name = "INFO_ORIGEN")
        protected DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFOORIGEN infoorigen;
        @XmlElement(name = "INFO_DESTINO")
        protected DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFODESTINO infodestino;

        /**
         * Obtiene el valor de la propiedad producto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRODUCTO() {
            return producto;
        }

        /**
         * Define el valor de la propiedad producto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRODUCTO(String value) {
            this.producto = value;
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

        /**
         * Obtiene el valor de la propiedad lote.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOTE() {
            return lote;
        }

        /**
         * Define el valor de la propiedad lote.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOTE(String value) {
            this.lote = value;
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

        /**
         * Obtiene el valor de la propiedad paisorigen.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAISORIGEN() {
            return paisorigen;
        }

        /**
         * Define el valor de la propiedad paisorigen.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAISORIGEN(String value) {
            this.paisorigen = value;
        }

        /**
         * Obtiene el valor de la propiedad infoorigen.
         * 
         * @return
         *     possible object is
         *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFOORIGEN }
         *     
         */
        public DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFOORIGEN getINFOORIGEN() {
            return infoorigen;
        }

        /**
         * Define el valor de la propiedad infoorigen.
         * 
         * @param value
         *     allowed object is
         *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFOORIGEN }
         *     
         */
        public void setINFOORIGEN(DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFOORIGEN value) {
            this.infoorigen = value;
        }

        /**
         * Obtiene el valor de la propiedad infodestino.
         * 
         * @return
         *     possible object is
         *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFODESTINO }
         *     
         */
        public DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFODESTINO getINFODESTINO() {
            return infodestino;
        }

        /**
         * Define el valor de la propiedad infodestino.
         * 
         * @param value
         *     allowed object is
         *     {@link DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFODESTINO }
         *     
         */
        public void setINFODESTINO(DTSCIENZACONSULTATRAZASUYResponse.DATOS.INFODESTINO value) {
            this.infodestino = value;
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
         *         &lt;element name="DESTINO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="NRO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="REMITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "destino",
            "nropedido",
            "remito",
            "fechaentrega",
            "cliente"
        })
        public static class INFODESTINO {

            @XmlElement(name = "DESTINO")
            protected String destino;
            @XmlElement(name = "NRO_PEDIDO")
            protected String nropedido;
            @XmlElement(name = "REMITO")
            protected String remito;
            @XmlElement(name = "FECHA_ENTREGA")
            protected String fechaentrega;
            @XmlElement(name = "CLIENTE")
            protected String cliente;

            /**
             * Obtiene el valor de la propiedad destino.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESTINO() {
                return destino;
            }

            /**
             * Define el valor de la propiedad destino.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESTINO(String value) {
                this.destino = value;
            }

            /**
             * Obtiene el valor de la propiedad nropedido.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNROPEDIDO() {
                return nropedido;
            }

            /**
             * Define el valor de la propiedad nropedido.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNROPEDIDO(String value) {
                this.nropedido = value;
            }

            /**
             * Obtiene el valor de la propiedad remito.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getREMITO() {
                return remito;
            }

            /**
             * Define el valor de la propiedad remito.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setREMITO(String value) {
                this.remito = value;
            }

            /**
             * Obtiene el valor de la propiedad fechaentrega.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFECHAENTREGA() {
                return fechaentrega;
            }

            /**
             * Define el valor de la propiedad fechaentrega.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFECHAENTREGA(String value) {
                this.fechaentrega = value;
            }

            /**
             * Obtiene el valor de la propiedad cliente.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCLIENTE() {
                return cliente;
            }

            /**
             * Define el valor de la propiedad cliente.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCLIENTE(String value) {
                this.cliente = value;
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
         *         &lt;element name="COMPROBANTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="FECHA_HORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "comprobante",
            "fechahora"
        })
        public static class INFOORIGEN {

            @XmlElement(name = "COMPROBANTE")
            protected String comprobante;
            @XmlElement(name = "FECHA_HORA")
            protected String fechahora;

            /**
             * Obtiene el valor de la propiedad comprobante.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCOMPROBANTE() {
                return comprobante;
            }

            /**
             * Define el valor de la propiedad comprobante.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCOMPROBANTE(String value) {
                this.comprobante = value;
            }

            /**
             * Obtiene el valor de la propiedad fechahora.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFECHAHORA() {
                return fechahora;
            }

            /**
             * Define el valor de la propiedad fechahora.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFECHAHORA(String value) {
                this.fechahora = value;
            }

        }

    }

}
