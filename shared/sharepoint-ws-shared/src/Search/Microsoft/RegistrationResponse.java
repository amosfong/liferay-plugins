/**
 * RegistrationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Search.Microsoft;

public class RegistrationResponse  implements java.io.Serializable {
    private java.lang.String registrationResult;

    public RegistrationResponse() {
    }

    public RegistrationResponse(
           java.lang.String registrationResult) {
           this.registrationResult = registrationResult;
    }


    /**
     * Gets the registrationResult value for this RegistrationResponse.
     * 
     * @return registrationResult
     */
    public java.lang.String getRegistrationResult() {
        return registrationResult;
    }


    /**
     * Sets the registrationResult value for this RegistrationResponse.
     * 
     * @param registrationResult
     */
    public void setRegistrationResult(java.lang.String registrationResult) {
        this.registrationResult = registrationResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistrationResponse)) return false;
        RegistrationResponse other = (RegistrationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.registrationResult==null && other.getRegistrationResult()==null) || 
             (this.registrationResult!=null &&
              this.registrationResult.equals(other.getRegistrationResult())));
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
        if (getRegistrationResult() != null) {
            _hashCode += getRegistrationResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistrationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Microsoft.Search", ">RegistrationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationResult");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:Microsoft.Search", "RegistrationResult"));
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
