//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:35:43 AM MESZ 
//


package gasmas.transfer.zib.net;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for per_minUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="per_minUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="per_min"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "per_minUnit", namespace = "http://mathematik.tu-darmstadt.de/opt/gas/XMLSchema")
@XmlEnum
public enum PerMinUnit {

    @XmlEnumValue("per_min")
    PER_MIN("per_min");
    private final String value;

    PerMinUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PerMinUnit fromValue(String v) {
        for (PerMinUnit c: PerMinUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
