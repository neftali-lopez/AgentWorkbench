//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.01 at 12:38:15 AM MESZ 
//


package gasmas.transfer.zib.cs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for onOffPattern.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="onOffPattern">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="on"/>
 *     &lt;enumeration value="off"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "onOffPattern")
@XmlEnum
public enum OnOffPattern {

    @XmlEnumValue("on")
    ON("on"),
    @XmlEnumValue("off")
    OFF("off");
    private final String value;

    OnOffPattern(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OnOffPattern fromValue(String v) {
        for (OnOffPattern c: OnOffPattern.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
