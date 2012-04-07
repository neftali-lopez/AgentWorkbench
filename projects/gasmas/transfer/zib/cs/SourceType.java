//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:38:15 AM MESZ 
//


package gasmas.transfer.zib.cs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  sourceType defines a source node in the gas network. 
 * 
 * <p>Java class for sourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sourceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema}boundaryNodeType">
 *       &lt;sequence>
 *         &lt;element name="gasTemperature" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}temperatureType"/>
 *         &lt;element name="calorificValue" type="{http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema}calorificValueType"/>
 *         &lt;element name="normDensity" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}densityType"/>
 *         &lt;element name="coefficient-A-heatCapacity" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}noType"/>
 *         &lt;element name="coefficient-B-heatCapacity" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}noType"/>
 *         &lt;element name="coefficient-C-heatCapacity" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}noType"/>
 *         &lt;element name="molarMass" type="{http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema}molarMassType"/>
 *         &lt;element name="pseudocriticalPressure" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}pressureType"/>
 *         &lt;element name="pseudocriticalTemperature" type="{http://mathematik.tu-darmstadt.de/opt/framework/XMLSchema}temperatureType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sourceType", namespace = "http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema", propOrder = {
    "gasTemperature",
    "calorificValue",
    "normDensity",
    "coefficientAHeatCapacity",
    "coefficientBHeatCapacity",
    "coefficientCHeatCapacity",
    "molarMass",
    "pseudocriticalPressure",
    "pseudocriticalTemperature"
})
public class SourceType
    extends BoundaryNodeType
{

    @XmlElement(required = true)
    protected TemperatureType gasTemperature;
    @XmlElement(required = true)
    protected CalorificValueType calorificValue;
    @XmlElement(required = true)
    protected DensityType normDensity;
    @XmlElement(name = "coefficient-A-heatCapacity", required = true)
    protected NoType coefficientAHeatCapacity;
    @XmlElement(name = "coefficient-B-heatCapacity", required = true)
    protected NoType coefficientBHeatCapacity;
    @XmlElement(name = "coefficient-C-heatCapacity", required = true)
    protected NoType coefficientCHeatCapacity;
    @XmlElement(required = true)
    protected MolarMassType molarMass;
    @XmlElement(required = true)
    protected PressureType pseudocriticalPressure;
    @XmlElement(required = true)
    protected TemperatureType pseudocriticalTemperature;

    /**
     * Gets the value of the gasTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureType }
     *     
     */
    public TemperatureType getGasTemperature() {
        return gasTemperature;
    }

    /**
     * Sets the value of the gasTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureType }
     *     
     */
    public void setGasTemperature(TemperatureType value) {
        this.gasTemperature = value;
    }

    /**
     * Gets the value of the calorificValue property.
     * 
     * @return
     *     possible object is
     *     {@link CalorificValueType }
     *     
     */
    public CalorificValueType getCalorificValue() {
        return calorificValue;
    }

    /**
     * Sets the value of the calorificValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalorificValueType }
     *     
     */
    public void setCalorificValue(CalorificValueType value) {
        this.calorificValue = value;
    }

    /**
     * Gets the value of the normDensity property.
     * 
     * @return
     *     possible object is
     *     {@link DensityType }
     *     
     */
    public DensityType getNormDensity() {
        return normDensity;
    }

    /**
     * Sets the value of the normDensity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DensityType }
     *     
     */
    public void setNormDensity(DensityType value) {
        this.normDensity = value;
    }

    /**
     * Gets the value of the coefficientAHeatCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link NoType }
     *     
     */
    public NoType getCoefficientAHeatCapacity() {
        return coefficientAHeatCapacity;
    }

    /**
     * Sets the value of the coefficientAHeatCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoType }
     *     
     */
    public void setCoefficientAHeatCapacity(NoType value) {
        this.coefficientAHeatCapacity = value;
    }

    /**
     * Gets the value of the coefficientBHeatCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link NoType }
     *     
     */
    public NoType getCoefficientBHeatCapacity() {
        return coefficientBHeatCapacity;
    }

    /**
     * Sets the value of the coefficientBHeatCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoType }
     *     
     */
    public void setCoefficientBHeatCapacity(NoType value) {
        this.coefficientBHeatCapacity = value;
    }

    /**
     * Gets the value of the coefficientCHeatCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link NoType }
     *     
     */
    public NoType getCoefficientCHeatCapacity() {
        return coefficientCHeatCapacity;
    }

    /**
     * Sets the value of the coefficientCHeatCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoType }
     *     
     */
    public void setCoefficientCHeatCapacity(NoType value) {
        this.coefficientCHeatCapacity = value;
    }

    /**
     * Gets the value of the molarMass property.
     * 
     * @return
     *     possible object is
     *     {@link MolarMassType }
     *     
     */
    public MolarMassType getMolarMass() {
        return molarMass;
    }

    /**
     * Sets the value of the molarMass property.
     * 
     * @param value
     *     allowed object is
     *     {@link MolarMassType }
     *     
     */
    public void setMolarMass(MolarMassType value) {
        this.molarMass = value;
    }

    /**
     * Gets the value of the pseudocriticalPressure property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPseudocriticalPressure() {
        return pseudocriticalPressure;
    }

    /**
     * Sets the value of the pseudocriticalPressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPseudocriticalPressure(PressureType value) {
        this.pseudocriticalPressure = value;
    }

    /**
     * Gets the value of the pseudocriticalTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureType }
     *     
     */
    public TemperatureType getPseudocriticalTemperature() {
        return pseudocriticalTemperature;
    }

    /**
     * Sets the value of the pseudocriticalTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureType }
     *     
     */
    public void setPseudocriticalTemperature(TemperatureType value) {
        this.pseudocriticalTemperature = value;
    }

}