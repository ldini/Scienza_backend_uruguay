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
 * <p>Clase Java para DT_SCIENZA_REG_USUARIO_UY_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_REG_USUARIO_UY_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RESULTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_APELLIDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_TIPO_DOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_NUM_DOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_SEXO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_IM_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_IM_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_IM_NUMERO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_FECHA_NAC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_DOMICLIO_COMPLETO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_CALLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_NUMERO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_PISO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_APARTAMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_LOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_CP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_DEPARTAMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_TEL_PARTICULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_TEL_CELULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_COMP_CELULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_TEL_LABORAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PACIENTE_EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_REG_USUARIO_UY_Response", namespace = "urn:scienza.com.ar:Registro_Usuarios_UY", propOrder = {
    "resultado",
    "pacientenombre",
    "pacienteapellido",
    "pacientetipodoc",
    "pacientenumdoc",
    "pacienteid",
    "pacientesexo",
    "pacienteimid",
    "pacienteimnombre",
    "pacienteimnumero",
    "pacientefechanac",
    "pacientedomicliocompleto",
    "pacientecalle",
    "pacientenumero",
    "pacientepiso",
    "pacienteapartamento",
    "pacientelocalidad",
    "pacientecp",
    "pacientedepartamento",
    "pacientetelparticular",
    "pacientetelcelular",
    "pacientecompcelular",
    "pacientetellaboral",
    "pacienteemail"
})
public class DTSCIENZAREGUSUARIOUYResponse {

    @XmlElement(name = "RESULTADO")
    protected String resultado;
    @XmlElement(name = "PACIENTE_NOMBRE")
    protected String pacientenombre;
    @XmlElement(name = "PACIENTE_APELLIDO")
    protected String pacienteapellido;
    @XmlElement(name = "PACIENTE_TIPO_DOC")
    protected String pacientetipodoc;
    @XmlElement(name = "PACIENTE_NUM_DOC")
    protected String pacientenumdoc;
    @XmlElement(name = "PACIENTE_ID")
    protected String pacienteid;
    @XmlElement(name = "PACIENTE_SEXO")
    protected String pacientesexo;
    @XmlElement(name = "PACIENTE_IM_ID")
    protected String pacienteimid;
    @XmlElement(name = "PACIENTE_IM_NOMBRE")
    protected String pacienteimnombre;
    @XmlElement(name = "PACIENTE_IM_NUMERO")
    protected String pacienteimnumero;
    @XmlElement(name = "PACIENTE_FECHA_NAC")
    protected String pacientefechanac;
    @XmlElement(name = "PACIENTE_DOMICLIO_COMPLETO")
    protected String pacientedomicliocompleto;
    @XmlElement(name = "PACIENTE_CALLE")
    protected String pacientecalle;
    @XmlElement(name = "PACIENTE_NUMERO")
    protected String pacientenumero;
    @XmlElement(name = "PACIENTE_PISO")
    protected String pacientepiso;
    @XmlElement(name = "PACIENTE_APARTAMENTO")
    protected String pacienteapartamento;
    @XmlElement(name = "PACIENTE_LOCALIDAD")
    protected String pacientelocalidad;
    @XmlElement(name = "PACIENTE_CP")
    protected String pacientecp;
    @XmlElement(name = "PACIENTE_DEPARTAMENTO")
    protected String pacientedepartamento;
    @XmlElement(name = "PACIENTE_TEL_PARTICULAR")
    protected String pacientetelparticular;
    @XmlElement(name = "PACIENTE_TEL_CELULAR")
    protected String pacientetelcelular;
    @XmlElement(name = "PACIENTE_COMP_CELULAR")
    protected String pacientecompcelular;
    @XmlElement(name = "PACIENTE_TEL_LABORAL")
    protected String pacientetellaboral;
    @XmlElement(name = "PACIENTE_EMAIL")
    protected String pacienteemail;

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESULTADO() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESULTADO(String value) {
        this.resultado = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientenombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTENOMBRE() {
        return pacientenombre;
    }

    /**
     * Define el valor de la propiedad pacientenombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTENOMBRE(String value) {
        this.pacientenombre = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteapellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEAPELLIDO() {
        return pacienteapellido;
    }

    /**
     * Define el valor de la propiedad pacienteapellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEAPELLIDO(String value) {
        this.pacienteapellido = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientetipodoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTETIPODOC() {
        return pacientetipodoc;
    }

    /**
     * Define el valor de la propiedad pacientetipodoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTETIPODOC(String value) {
        this.pacientetipodoc = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientenumdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTENUMDOC() {
        return pacientenumdoc;
    }

    /**
     * Define el valor de la propiedad pacientenumdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTENUMDOC(String value) {
        this.pacientenumdoc = value;
    }

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
     * Obtiene el valor de la propiedad pacientesexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTESEXO() {
        return pacientesexo;
    }

    /**
     * Define el valor de la propiedad pacientesexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTESEXO(String value) {
        this.pacientesexo = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteimid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEIMID() {
        return pacienteimid;
    }

    /**
     * Define el valor de la propiedad pacienteimid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEIMID(String value) {
        this.pacienteimid = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteimnombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEIMNOMBRE() {
        return pacienteimnombre;
    }

    /**
     * Define el valor de la propiedad pacienteimnombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEIMNOMBRE(String value) {
        this.pacienteimnombre = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteimnumero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEIMNUMERO() {
        return pacienteimnumero;
    }

    /**
     * Define el valor de la propiedad pacienteimnumero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEIMNUMERO(String value) {
        this.pacienteimnumero = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientefechanac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEFECHANAC() {
        return pacientefechanac;
    }

    /**
     * Define el valor de la propiedad pacientefechanac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEFECHANAC(String value) {
        this.pacientefechanac = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientedomicliocompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEDOMICLIOCOMPLETO() {
        return pacientedomicliocompleto;
    }

    /**
     * Define el valor de la propiedad pacientedomicliocompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEDOMICLIOCOMPLETO(String value) {
        this.pacientedomicliocompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientecalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTECALLE() {
        return pacientecalle;
    }

    /**
     * Define el valor de la propiedad pacientecalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTECALLE(String value) {
        this.pacientecalle = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientenumero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTENUMERO() {
        return pacientenumero;
    }

    /**
     * Define el valor de la propiedad pacientenumero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTENUMERO(String value) {
        this.pacientenumero = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientepiso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEPISO() {
        return pacientepiso;
    }

    /**
     * Define el valor de la propiedad pacientepiso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEPISO(String value) {
        this.pacientepiso = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteapartamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEAPARTAMENTO() {
        return pacienteapartamento;
    }

    /**
     * Define el valor de la propiedad pacienteapartamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEAPARTAMENTO(String value) {
        this.pacienteapartamento = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientelocalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTELOCALIDAD() {
        return pacientelocalidad;
    }

    /**
     * Define el valor de la propiedad pacientelocalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTELOCALIDAD(String value) {
        this.pacientelocalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientecp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTECP() {
        return pacientecp;
    }

    /**
     * Define el valor de la propiedad pacientecp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTECP(String value) {
        this.pacientecp = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientedepartamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEDEPARTAMENTO() {
        return pacientedepartamento;
    }

    /**
     * Define el valor de la propiedad pacientedepartamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEDEPARTAMENTO(String value) {
        this.pacientedepartamento = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientetelparticular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTETELPARTICULAR() {
        return pacientetelparticular;
    }

    /**
     * Define el valor de la propiedad pacientetelparticular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTETELPARTICULAR(String value) {
        this.pacientetelparticular = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientetelcelular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTETELCELULAR() {
        return pacientetelcelular;
    }

    /**
     * Define el valor de la propiedad pacientetelcelular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTETELCELULAR(String value) {
        this.pacientetelcelular = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientecompcelular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTECOMPCELULAR() {
        return pacientecompcelular;
    }

    /**
     * Define el valor de la propiedad pacientecompcelular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTECOMPCELULAR(String value) {
        this.pacientecompcelular = value;
    }

    /**
     * Obtiene el valor de la propiedad pacientetellaboral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTETELLABORAL() {
        return pacientetellaboral;
    }

    /**
     * Define el valor de la propiedad pacientetellaboral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTETELLABORAL(String value) {
        this.pacientetellaboral = value;
    }

    /**
     * Obtiene el valor de la propiedad pacienteemail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACIENTEEMAIL() {
        return pacienteemail;
    }

    /**
     * Define el valor de la propiedad pacienteemail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACIENTEEMAIL(String value) {
        this.pacienteemail = value;
    }

}
