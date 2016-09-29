//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.14 at 07:42:56 PM EEST 
//


package com.example.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentState"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="New"/&gt;
 *     &lt;enumeration value="Approved"/&gt;
 *     &lt;enumeration value="Completed"/&gt;
 *     &lt;enumeration value="Canceled"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PaymentState")
@XmlEnum
public enum PaymentState {

    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Approved")
    APPROVED("Approved"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled");
    private final String value;

    PaymentState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentState fromValue(String v) {
        for (PaymentState c: PaymentState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
