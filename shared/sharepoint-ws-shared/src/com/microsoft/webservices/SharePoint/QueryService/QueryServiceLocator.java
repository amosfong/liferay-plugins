/**
 * QueryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices.SharePoint.QueryService;

public class QueryServiceLocator extends org.apache.axis.client.Service implements com.microsoft.webservices.SharePoint.QueryService.QueryService {

/**
 * Microsoft SharePoint Foundation 2010 Search Query Web Service
 */

    public QueryServiceLocator() {
    }


    public QueryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryServiceSoap
    private java.lang.String QueryServiceSoap_address = "http://liferay-20jf4ic/_vti_bin/spsearch.asmx";

    public java.lang.String getQueryServiceSoapAddress() {
        return QueryServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryServiceSoapWSDDServiceName = "QueryServiceSoap";

    public java.lang.String getQueryServiceSoapWSDDServiceName() {
        return QueryServiceSoapWSDDServiceName;
    }

    public void setQueryServiceSoapWSDDServiceName(java.lang.String name) {
        QueryServiceSoapWSDDServiceName = name;
    }

    public com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap getQueryServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryServiceSoap(endpoint);
    }

    public com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap getQueryServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoapStub _stub = new com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoapStub(portAddress, this);
            _stub.setPortName(getQueryServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryServiceSoapEndpointAddress(java.lang.String address) {
        QueryServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoapStub _stub = new com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoapStub(new java.net.URL(QueryServiceSoap_address), this);
                _stub.setPortName(getQueryServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QueryServiceSoap".equals(inputPortName)) {
            return getQueryServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://microsoft.com/webservices/SharePoint/QueryService", "QueryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://microsoft.com/webservices/SharePoint/QueryService", "QueryServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryServiceSoap".equals(portName)) {
            setQueryServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
