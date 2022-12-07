//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.02.17 a las 10:42:45 AM ART 
//


package ar.com.scienza.remote.ws.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DT_SCIENZA_CONFIR_COORDINACION complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_CONFIR_COORDINACION"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID_AFILIADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUMERO_PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ID_PROPUESTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MARCA_PROPUESTA_ACEPTADA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="COMENTARIOS_CANAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DESTINATARIO_MERCADERIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IMPORTE_ABONADO" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;totalDigits value="17"/&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MEDIO_PAGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUMERO_COMPROBANTE_PAGO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FECHA_PAGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TIPO_TARJETA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NUMERO_TARJETA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MES_VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ANIO_VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MARCA_TARJETA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CUOTAS" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_CONFIR_COORDINACION", namespace = "urn:scienza.com.ar:Confirmacion_Coordinacion", propOrder = {
    "idafiliado",
    "numeropedido",
    "idpropuesta",
    "marcapropuestaaceptada",
    "comentarioscanal",
    "destinatariomercaderia",
    "fechaentrega",
    "turno",
    "vencimiento",
    "importeabonado",
    "mediopago",
    "numerocomprobantepago",
    "lote",
    "fechapago",
    "tipotarjeta",
    "numerotarjeta",
    "mesvencimiento",
    "aniovencimiento",
    "marcatarjeta",
    "cuotas"
})
public class DTSCIENZACONFIRCOORDINACION {

    @XmlElement(name = "ID_AFILIADO")
    protected String idafiliado;
    @XmlElement(name = "NUMERO_PEDIDO")
    protected String numeropedido;
    @XmlElement(name = "ID_PROPUESTA")
    protected String idpropuesta;
    @XmlElement(name = "MARCA_PROPUESTA_ACEPTADA")
    protected String marcapropuestaaceptada;
    @XmlElement(name = "COMENTARIOS_CANAL")
    protected String comentarioscanal;
    @XmlElement(name = "DESTINATARIO_MERCADERIA")
    protected String destinatariomercaderia;
    @XmlElement(name = "FECHA_ENTREGA")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaentrega;
    @XmlElement(name = "TURNO")
    protected String turno;
    @XmlElement(name = "VENCIMIENTO")
    protected String vencimiento;
    @XmlElement(name = "IMPORTE_ABONADO")
    protected BigDecimal importeabonado;
    @XmlElement(name = "MEDIO_PAGO")
    protected String mediopago;
    @XmlElement(name = "NUMERO_COMPROBANTE_PAGO")
    protected BigInteger numerocomprobantepago;
    @XmlElement(name = "LOTE")
    protected String lote;
    @XmlElement(name = "FECHA_PAGO")
    protected String fechapago;
    @XmlElement(name = "TIPO_TARJETA")
    protected String tipotarjeta;
    @XmlElement(name = "NUMERO_TARJETA")
    protected String numerotarjeta;
    @XmlElement(name = "MES_VENCIMIENTO")
    protected BigInteger mesvencimiento;
    @XmlElement(name = "ANIO_VENCIMIENTO")
    protected BigInteger aniovencimiento;
    @XmlElement(name = "MARCA_TARJETA")
    protected String marcatarjeta;
    @XmlElement(name = "CUOTAS")
    protected BigInteger cuotas;

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
     * Obtiene el valor de la propiedad marcapropuestaaceptada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMARCAPROPUESTAACEPTADA() {
        return marcapropuestaaceptada;
    }

    /**
     * Define el valor de la propiedad marcapropuestaaceptada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMARCAPROPUESTAACEPTADA(String value) {
        this.marcapropuestaaceptada = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioscanal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMENTARIOSCANAL() {
        return comentarioscanal;
    }

    /**
     * Define el valor de la propiedad comentarioscanal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMENTARIOSCANAL(String value) {
        this.comentarioscanal = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariomercaderia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESTINATARIOMERCADERIA() {
        return destinatariomercaderia;
    }

    /**
     * Define el valor de la propiedad destinatariomercaderia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESTINATARIOMERCADERIA(String value) {
        this.destinatariomercaderia = value;
    }

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
     * Obtiene el valor de la propiedad importeabonado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTEABONADO() {
        return importeabonado;
    }

    /**
     * Define el valor de la propiedad importeabonado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTEABONADO(BigDecimal value) {
        this.importeabonado = value;
    }

    /**
     * Obtiene el valor de la propiedad mediopago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEDIOPAGO() {
        return mediopago;
    }

    /**
     * Define el valor de la propiedad mediopago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEDIOPAGO(String value) {
        this.mediopago = value;
    }

    /**
     * Obtiene el valor de la propiedad numerocomprobantepago.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNUMEROCOMPROBANTEPAGO() {
        return numerocomprobantepago;
    }

    /**
     * Define el valor de la propiedad numerocomprobantepago.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNUMEROCOMPROBANTEPAGO(BigInteger value) {
        this.numerocomprobantepago = value;
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
     * Obtiene el valor de la propiedad fechapago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAPAGO() {
        return fechapago;
    }

    /**
     * Define el valor de la propiedad fechapago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAPAGO(String value) {
        this.fechapago = value;
    }

    /**
     * Obtiene el valor de la propiedad tipotarjeta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOTARJETA() {
        return tipotarjeta;
    }

    /**
     * Define el valor de la propiedad tipotarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOTARJETA(String value) {
        this.tipotarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad numerotarjeta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMEROTARJETA() {
        return numerotarjeta;
    }

    /**
     * Define el valor de la propiedad numerotarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMEROTARJETA(String value) {
        this.numerotarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad mesvencimiento.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMESVENCIMIENTO() {
        return mesvencimiento;
    }

    /**
     * Define el valor de la propiedad mesvencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMESVENCIMIENTO(BigInteger value) {
        this.mesvencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad aniovencimiento.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getANIOVENCIMIENTO() {
        return aniovencimiento;
    }

    /**
     * Define el valor de la propiedad aniovencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setANIOVENCIMIENTO(BigInteger value) {
        this.aniovencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad marcatarjeta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMARCATARJETA() {
        return marcatarjeta;
    }

    /**
     * Define el valor de la propiedad marcatarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMARCATARJETA(String value) {
        this.marcatarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad cuotas.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCUOTAS() {
        return cuotas;
    }

    /**
     * Define el valor de la propiedad cuotas.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCUOTAS(BigInteger value) {
        this.cuotas = value;
    }

}
