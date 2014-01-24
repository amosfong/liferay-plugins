/**
 * Registration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Search.Microsoft;

public class Registration  implements java.io.Serializable {
    private java.lang.String registrationXml;

    public Registration() {
    }

    public Registration(
           java.lang.String registrationXml) {
           this.registrationXml = registrationXml;
    }


    /**
     * Gets the registrationXml value for this Registration.
     * 
     * @return registrationXml
     */
    public java.lang.String getRegistrationXml() {
        return registrationXml;
    }


    /**
     * Sets the registrationXml value for this Registration.
     * 
     * @param registrationXml
     */
    public void setRegistrationXml(java.lang.String registrationXml) {
        this.registrationXml = registrationXml;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Registration)) return false;
        Registration other = (Registration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.registrationXml==null && other.getRegistrationXml()==null) || 
             (this.registrationXml!=null &&
              this.registrationXml.equals(other.getRegistrationXml())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRegistrationXml() != null) {
            _hashCode += getRegistrationXml().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Registration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Microsoft.Search", ">Registration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationXml");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:Microsoft.Search", "registrationXml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
