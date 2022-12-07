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
 * <p>Clase Java para DT_SCIENZA_CONSULTA_TRAZAS_UY complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_SCIENZA_CONSULTA_TRAZAS_UY"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CODIGO_TRAZABILIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCIENZA_CONSULTA_TRAZAS_UY", namespace = "urn:scienza.com.ar:Consulta_Trazas_UY", propOrder = {
    "codigotrazabilidad"
})
public class DTSCIENZACONSULTATRAZASUY {

    @XmlElement(name = "CODIGO_TRAZABILIDAD")
    protected String codigotrazabilidad;

    /**
     * Obtiene el valor de la propiedad codigotrazabilidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGOTRAZABILIDAD() {
        return codigotrazabilidad;
    }

    /**
     * Define el valor de la propiedad codigotrazabilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGOTRAZABILIDAD(String value) {
        this.codigotrazabilidad = value;
    }

}
