//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:38:15 AM MESZ 
//


package gasmas.transfer.zib.cs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  controlValveType defines a pressure regulator in the gas network.
 *       
 * 
 * <p>Java class for controlValveType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="controlValveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema}connectionType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="pressureDifferentialMin" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *             &lt;element name="pressureDifferentialMax" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="pressureSet" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="pressureInMin" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *         &lt;element name="pressureOutMax" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="dragFactorIn" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}noType"/>
 *             &lt;element name="diameterIn" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}lengthType"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="pressureLossIn" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="dragFactorOut" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}noType"/>
 *             &lt;element name="diameterOut" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}lengthType"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="pressureLossOut" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="increasedOutputTemperature" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}temperatureType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gasPreheaterExisting" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" />
 *       &lt;attribute name="internalBypassRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" default="1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "controlValveType", namespace = "http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema", propOrder = {
    "pressureDifferentialMin",
    "pressureDifferentialMax",
    "pressureSet",
    "pressureInMin",
    "pressureOutMax",
    "dragFactorIn",
    "diameterIn",
    "pressureLossIn",
    "dragFactorOut",
    "diameterOut",
    "pressureLossOut",
    "increasedOutputTemperature"
})
public class ControlValveType
    extends GasConnectionType
{

    protected PressureType pressureDifferentialMin;
    protected PressureType pressureDifferentialMax;
    protected PressureType pressureSet;
    @XmlElement(required = true)
    protected PressureType pressureInMin;
    @XmlElement(required = true)
    protected PressureType pressureOutMax;
    protected NoType dragFactorIn;
    protected LengthType diameterIn;
    protected PressureType pressureLossIn;
    protected NoType dragFactorOut;
    protected LengthType diameterOut;
    protected PressureType pressureLossOut;
    protected TemperatureType increasedOutputTemperature;
    @XmlAttribute
    protected Boolean gasPreheaterExisting;
    @XmlAttribute
    protected Boolean internalBypassRequired;

    /**
     * Gets the value of the pressureDifferentialMin property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureDifferentialMin() {
        return pressureDifferentialMin;
    }

    /**
     * Sets the value of the pressureDifferentialMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureDifferentialMin(PressureType value) {
        this.pressureDifferentialMin = value;
    }

    /**
     * Gets the value of the pressureDifferentialMax property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureDifferentialMax() {
        return pressureDifferentialMax;
    }

    /**
     * Sets the value of the pressureDifferentialMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureDifferentialMax(PressureType value) {
        this.pressureDifferentialMax = value;
    }

    /**
     * Gets the value of the pressureSet property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureSet() {
        return pressureSet;
    }

    /**
     * Sets the value of the pressureSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureSet(PressureType value) {
        this.pressureSet = value;
    }

    /**
     * Gets the value of the pressureInMin property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureInMin() {
        return pressureInMin;
    }

    /**
     * Sets the value of the pressureInMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureInMin(PressureType value) {
        this.pressureInMin = value;
    }

    /**
     * Gets the value of the pressureOutMax property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureOutMax() {
        return pressureOutMax;
    }

    /**
     * Sets the value of the pressureOutMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureOutMax(PressureType value) {
        this.pressureOutMax = value;
    }

    /**
     * Gets the value of the dragFactorIn property.
     * 
     * @return
     *     possible object is
     *     {@link NoType }
     *     
     */
    public NoType getDragFactorIn() {
        return dragFactorIn;
    }

    /**
     * Sets the value of the dragFactorIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoType }
     *     
     */
    public void setDragFactorIn(NoType value) {
        this.dragFactorIn = value;
    }

    /**
     * Gets the value of the diameterIn property.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getDiameterIn() {
        return diameterIn;
    }

    /**
     * Sets the value of the diameterIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setDiameterIn(LengthType value) {
        this.diameterIn = value;
    }

    /**
     * Gets the value of the pressureLossIn property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureLossIn() {
        return pressureLossIn;
    }

    /**
     * Sets the value of the pressureLossIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureLossIn(PressureType value) {
        this.pressureLossIn = value;
    }

    /**
     * Gets the value of the dragFactorOut property.
     * 
     * @return
     *     possible object is
     *     {@link NoType }
     *     
     */
    public NoType getDragFactorOut() {
        return dragFactorOut;
    }

    /**
     * Sets the value of the dragFactorOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoType }
     *     
     */
    public void setDragFactorOut(NoType value) {
        this.dragFactorOut = value;
    }

    /**
     * Gets the value of the diameterOut property.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getDiameterOut() {
        return diameterOut;
    }

    /**
     * Sets the value of the diameterOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setDiameterOut(LengthType value) {
        this.diameterOut = value;
    }

    /**
     * Gets the value of the pressureLossOut property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressureLossOut() {
        return pressureLossOut;
    }

    /**
     * Sets the value of the pressureLossOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressureLossOut(PressureType value) {
        this.pressureLossOut = value;
    }

    /**
     * Gets the value of the increasedOutputTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureType }
     *     
     */
    public TemperatureType getIncreasedOutputTemperature() {
        return increasedOutputTemperature;
    }

    /**
     * Sets the value of the increasedOutputTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureType }
     *     
     */
    public void setIncreasedOutputTemperature(TemperatureType value) {
        this.increasedOutputTemperature = value;
    }

    /**
     * Gets the value of the gasPreheaterExisting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isGasPreheaterExisting() {
        if (gasPreheaterExisting == null) {
            return false;
        } else {
            return gasPreheaterExisting;
        }
    }

    /**
     * Sets the value of the gasPreheaterExisting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGasPreheaterExisting(Boolean value) {
        this.gasPreheaterExisting = value;
    }

    /**
     * Gets the value of the internalBypassRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isInternalBypassRequired() {
        if (internalBypassRequired == null) {
            return true;
        } else {
            return internalBypassRequired;
        }
    }

    /**
     * Sets the value of the internalBypassRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternalBypassRequired(Boolean value) {
        this.internalBypassRequired = value;
    }

}
