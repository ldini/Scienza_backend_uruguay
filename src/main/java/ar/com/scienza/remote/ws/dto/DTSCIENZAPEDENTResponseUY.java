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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DT_SCIENZA_PED_ENT_Response_UY complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_PED_ENT_Response_UY"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CANAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SECTOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PEDIDO_NUMERO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PEDIDO_ORIGINAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PEDIDO_SUCESOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ENTREGA_DOMICILIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ENTREGA_FARMACIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CLASIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="COORDINACION_MOBILE" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PEDIDO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FECHA_ELEGIDA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="COLECCION_POSICIONES_PEDIDO" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MATERIAL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MATERIAL_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="COLECCION_ENTREGAS" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="REMITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TXT_FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TXT_TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ENTREGA_EVENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ENTREGA_EVENTO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ENTREGA_MARCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DISTRIBUIDOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PATENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DESTINO_ESTIMADO_TIEMPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DESTINO_ESTIMADO_DISTANCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="OPERADOR_LOGISTICO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ESTADO_ENVIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="COLECCION_POSICIONES_ENTREGA" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="MATERIAL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="MATERIAL_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="COLECCION_TRAZAS" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="TRAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
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
@XmlType(name = "DT_SCIENZA_PED_ENT_Response_UY", propOrder = {
    "canal",
    "sector",
    "pedidonumero",
    "pedidooriginal",
    "pedidosucesor",
    "entregadomicilio",
    "entregafarmacia",
    "clasificacion",
    "estado",
    "coordinacionmobile",
    "pedidofecha",
    "fechaelegida",
    "coleccionposicionespedido",
    "coleccionentregas"
})
public class DTSCIENZAPEDENTResponseUY {

    @XmlElement(name = "CANAL")
    protected String canal;
    @XmlElement(name = "SECTOR")
    protected String sector;
    @XmlElement(name = "PEDIDO_NUMERO")
    protected String pedidonumero;
    @XmlElement(name = "PEDIDO_ORIGINAL")
    protected String pedidooriginal;
    @XmlElement(name = "PEDIDO_SUCESOR")
    protected String pedidosucesor;
    @XmlElement(name = "ENTREGA_DOMICILIO")
    protected String entregadomicilio;
    @XmlElement(name = "ENTREGA_FARMACIA")
    protected String entregafarmacia;
    @XmlElement(name = "CLASIFICACION")
    protected String clasificacion;
    @XmlElement(name = "ESTADO")
    protected String estado;
    @XmlElement(name = "COORDINACION_MOBILE")
    protected String coordinacionmobile;
    @XmlElement(name = "PEDIDO_FECHA")
    protected String pedidofecha;
    @XmlElement(name = "FECHA_ELEGIDA")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaelegida;
    @XmlElement(name = "COLECCION_POSICIONES_PEDIDO")
    protected List<DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO> coleccionposicionespedido;
    @XmlElement(name = "COLECCION_ENTREGAS")
    protected List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS> coleccionentregas;

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCANAL() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCANAL(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad sector.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECTOR() {
        return sector;
    }

    /**
     * Define el valor de la propiedad sector.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECTOR(String value) {
        this.sector = value;
    }

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
     * Obtiene el valor de la propiedad pedidooriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEDIDOORIGINAL() {
        return pedidooriginal;
    }

    /**
     * Define el valor de la propiedad pedidooriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEDIDOORIGINAL(String value) {
        this.pedidooriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad pedidosucesor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEDIDOSUCESOR() {
        return pedidosucesor;
    }

    /**
     * Define el valor de la propiedad pedidosucesor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEDIDOSUCESOR(String value) {
        this.pedidosucesor = value;
    }

    /**
     * Obtiene el valor de la propiedad entregadomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTREGADOMICILIO() {
        return entregadomicilio;
    }

    /**
     * Define el valor de la propiedad entregadomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTREGADOMICILIO(String value) {
        this.entregadomicilio = value;
    }

    /**
     * Obtiene el valor de la propiedad entregafarmacia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTREGAFARMACIA() {
        return entregafarmacia;
    }

    /**
     * Define el valor de la propiedad entregafarmacia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTREGAFARMACIA(String value) {
        this.entregafarmacia = value;
    }

    /**
     * Obtiene el valor de la propiedad clasificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLASIFICACION() {
        return clasificacion;
    }

    /**
     * Define el valor de la propiedad clasificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLASIFICACION(String value) {
        this.clasificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTADO() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTADO(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad coordinacionmobile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOORDINACIONMOBILE() {
        return coordinacionmobile;
    }

    /**
     * Define el valor de la propiedad coordinacionmobile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOORDINACIONMOBILE(String value) {
        this.coordinacionmobile = value;
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

    /**
     * Obtiene el valor de la propiedad fechaelegida.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHAELEGIDA() {
        return fechaelegida;
    }

    /**
     * Define el valor de la propiedad fechaelegida.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHAELEGIDA(XMLGregorianCalendar value) {
        this.fechaelegida = value;
    }

    /**
     * Gets the value of the coleccionposicionespedido property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coleccionposicionespedido property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOLECCIONPOSICIONESPEDIDO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO }
     * 
     * 
     */
    public List<DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO> getCOLECCIONPOSICIONESPEDIDO() {
        if (coleccionposicionespedido == null) {
            coleccionposicionespedido = new ArrayList<DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO>();
        }
        return this.coleccionposicionespedido;
    }

    /**
     * Gets the value of the coleccionentregas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coleccionentregas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOLECCIONENTREGAS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS }
     * 
     * 
     */
    public List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS> getCOLECCIONENTREGAS() {
        if (coleccionentregas == null) {
            coleccionentregas = new ArrayList<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS>();
        }
        return this.coleccionentregas;
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
     *         &lt;element name="ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="REMITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TXT_FECHA_ENTREGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TXT_TURNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ENTREGA_EVENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ENTREGA_EVENTO_FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ENTREGA_MARCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DISTRIBUIDOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PATENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DESTINO_ESTIMADO_TIEMPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DESTINO_ESTIMADO_DISTANCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="OPERADOR_LOGISTICO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ESTADO_ENVIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="COLECCION_POSICIONES_ENTREGA" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="MATERIAL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="MATERIAL_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="COLECCION_TRAZAS" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="TRAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    @XmlType(name = "", propOrder = {
        "entrega",
        "remito",
        "txtfechaentrega",
        "txtturno",
        "entregaevento",
        "entregaeventofecha",
        "entregamarca",
        "distribuidor",
        "patente",
        "destinoestimadotiempo",
        "destinoestimadodistancia",
        "operadorlogistico",
        "estadoenvio",
        "coleccionposicionesentrega"
    })
    public static class COLECCIONENTREGAS {

        @XmlElement(name = "ENTREGA")
        protected String entrega;
        @XmlElement(name = "REMITO")
        protected String remito;
        @XmlElement(name = "TXT_FECHA_ENTREGA")
        protected String txtfechaentrega;
        @XmlElement(name = "TXT_TURNO")
        protected String txtturno;
        @XmlElement(name = "ENTREGA_EVENTO")
        protected String entregaevento;
        @XmlElement(name = "ENTREGA_EVENTO_FECHA")
        protected String entregaeventofecha;
        @XmlElement(name = "ENTREGA_MARCA")
        protected String entregamarca;
        @XmlElement(name = "DISTRIBUIDOR")
        protected String distribuidor;
        @XmlElement(name = "PATENTE")
        protected String patente;
        @XmlElement(name = "DESTINO_ESTIMADO_TIEMPO")
        protected String destinoestimadotiempo;
        @XmlElement(name = "DESTINO_ESTIMADO_DISTANCIA")
        protected String destinoestimadodistancia;
        @XmlElement(name = "OPERADOR_LOGISTICO")
        protected String operadorlogistico;
        @XmlElement(name = "ESTADO_ENVIO")
        protected String estadoenvio;
        @XmlElement(name = "COLECCION_POSICIONES_ENTREGA")
        protected List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA> coleccionposicionesentrega;

        /**
         * Obtiene el valor de la propiedad entrega.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getENTREGA() {
            return entrega;
        }

        /**
         * Define el valor de la propiedad entrega.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setENTREGA(String value) {
            this.entrega = value;
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
         * Obtiene el valor de la propiedad txtfechaentrega.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTXTFECHAENTREGA() {
            return txtfechaentrega;
        }

        /**
         * Define el valor de la propiedad txtfechaentrega.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTXTFECHAENTREGA(String value) {
            this.txtfechaentrega = value;
        }

        /**
         * Obtiene el valor de la propiedad txtturno.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTXTTURNO() {
            return txtturno;
        }

        /**
         * Define el valor de la propiedad txtturno.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTXTTURNO(String value) {
            this.txtturno = value;
        }

        /**
         * Obtiene el valor de la propiedad entregaevento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getENTREGAEVENTO() {
            return entregaevento;
        }

        /**
         * Define el valor de la propiedad entregaevento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setENTREGAEVENTO(String value) {
            this.entregaevento = value;
        }

        /**
         * Obtiene el valor de la propiedad entregaeventofecha.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getENTREGAEVENTOFECHA() {
            return entregaeventofecha;
        }

        /**
         * Define el valor de la propiedad entregaeventofecha.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setENTREGAEVENTOFECHA(String value) {
            this.entregaeventofecha = value;
        }

        /**
         * Obtiene el valor de la propiedad entregamarca.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getENTREGAMARCA() {
            return entregamarca;
        }

        /**
         * Define el valor de la propiedad entregamarca.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setENTREGAMARCA(String value) {
            this.entregamarca = value;
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
         * Obtiene el valor de la propiedad destinoestimadotiempo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESTINOESTIMADOTIEMPO() {
            return destinoestimadotiempo;
        }

        /**
         * Define el valor de la propiedad destinoestimadotiempo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESTINOESTIMADOTIEMPO(String value) {
            this.destinoestimadotiempo = value;
        }

        /**
         * Obtiene el valor de la propiedad destinoestimadodistancia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESTINOESTIMADODISTANCIA() {
            return destinoestimadodistancia;
        }

        /**
         * Define el valor de la propiedad destinoestimadodistancia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESTINOESTIMADODISTANCIA(String value) {
            this.destinoestimadodistancia = value;
        }

        /**
         * Obtiene el valor de la propiedad operadorlogistico.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOPERADORLOGISTICO() {
            return operadorlogistico;
        }

        /**
         * Define el valor de la propiedad operadorlogistico.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOPERADORLOGISTICO(String value) {
            this.operadorlogistico = value;
        }

        /**
         * Obtiene el valor de la propiedad estadoenvio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getESTADOENVIO() {
            return estadoenvio;
        }

        /**
         * Define el valor de la propiedad estadoenvio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setESTADOENVIO(String value) {
            this.estadoenvio = value;
        }

        /**
         * Gets the value of the coleccionposicionesentrega property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the coleccionposicionesentrega property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCOLECCIONPOSICIONESENTREGA().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA }
         * 
         * 
         */
        public List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA> getCOLECCIONPOSICIONESENTREGA() {
            if (coleccionposicionesentrega == null) {
                coleccionposicionesentrega = new ArrayList<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA>();
            }
            return this.coleccionposicionesentrega;
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
         *         &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="MATERIAL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="MATERIAL_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="LABORATORIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="LOTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="COLECCION_TRAZAS" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="TRAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "posicion",
            "materialid",
            "materialnombre",
            "monodroga",
            "cantidad",
            "laboratorio",
            "lote",
            "vencimiento",
            "colecciontrazas"
        })
        public static class COLECCIONPOSICIONESENTREGA {

            @XmlElement(name = "POSICION")
            protected String posicion;
            @XmlElement(name = "MATERIAL_ID")
            protected String materialid;
            @XmlElement(name = "MATERIAL_NOMBRE")
            protected String materialnombre;
            @XmlElement(name = "MONODROGA")
            protected String monodroga;
            @XmlElement(name = "CANTIDAD")
            protected String cantidad;
            @XmlElement(name = "LABORATORIO")
            protected String laboratorio;
            @XmlElement(name = "LOTE")
            protected String lote;
            @XmlElement(name = "VENCIMIENTO")
            protected String vencimiento;
            @XmlElement(name = "COLECCION_TRAZAS")
            protected List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS> colecciontrazas;

            /**
             * Obtiene el valor de la propiedad posicion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPOSICION() {
                return posicion;
            }

            /**
             * Define el valor de la propiedad posicion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPOSICION(String value) {
                this.posicion = value;
            }

            /**
             * Obtiene el valor de la propiedad materialid.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMATERIALID() {
                return materialid;
            }

            /**
             * Define el valor de la propiedad materialid.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMATERIALID(String value) {
                this.materialid = value;
            }

            /**
             * Obtiene el valor de la propiedad materialnombre.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMATERIALNOMBRE() {
                return materialnombre;
            }

            /**
             * Define el valor de la propiedad materialnombre.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMATERIALNOMBRE(String value) {
                this.materialnombre = value;
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
             * Obtiene el valor de la propiedad cantidad.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCANTIDAD() {
                return cantidad;
            }

            /**
             * Define el valor de la propiedad cantidad.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCANTIDAD(String value) {
                this.cantidad = value;
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
             * Gets the value of the colecciontrazas property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the colecciontrazas property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCOLECCIONTRAZAS().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS }
             * 
             * 
             */
            public List<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS> getCOLECCIONTRAZAS() {
                if (colecciontrazas == null) {
                    colecciontrazas = new ArrayList<DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS>();
                }
                return this.colecciontrazas;
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
             *         &lt;element name="TRAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
                "traza"
            })
            public static class COLECCIONTRAZAS {

                @XmlElement(name = "TRAZA")
                protected String traza;

                /**
                 * Obtiene el valor de la propiedad traza.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTRAZA() {
                    return traza;
                }

                /**
                 * Define el valor de la propiedad traza.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTRAZA(String value) {
                    this.traza = value;
                }

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
     *         &lt;element name="POSICION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MATERIAL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MATERIAL_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MONODROGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "materialid",
        "materialnombre",
        "monodroga",
        "cantidad",
        "laboratorio"
    })
    public static class COLECCIONPOSICIONESPEDIDO {

        @XmlElement(name = "POSICION")
        protected String posicion;
        @XmlElement(name = "MATERIAL_ID")
        protected String materialid;
        @XmlElement(name = "MATERIAL_NOMBRE")
        protected String materialnombre;
        @XmlElement(name = "MONODROGA")
        protected String monodroga;
        @XmlElement(name = "CANTIDAD")
        protected String cantidad;
        @XmlElement(name = "LABORATORIO")
        protected String laboratorio;

        /**
         * Obtiene el valor de la propiedad posicion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSICION() {
            return posicion;
        }

        /**
         * Define el valor de la propiedad posicion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSICION(String value) {
            this.posicion = value;
        }

        /**
         * Obtiene el valor de la propiedad materialid.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMATERIALID() {
            return materialid;
        }

        /**
         * Define el valor de la propiedad materialid.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMATERIALID(String value) {
            this.materialid = value;
        }

        /**
         * Obtiene el valor de la propiedad materialnombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMATERIALNOMBRE() {
            return materialnombre;
        }

        /**
         * Define el valor de la propiedad materialnombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMATERIALNOMBRE(String value) {
            this.materialnombre = value;
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
         * Obtiene el valor de la propiedad cantidad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCANTIDAD() {
            return cantidad;
        }

        /**
         * Define el valor de la propiedad cantidad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCANTIDAD(String value) {
            this.cantidad = value;
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
