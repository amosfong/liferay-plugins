/**
 * QueryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices.SharePoint.QueryService;

public interface QueryService extends javax.xml.rpc.Service {

/**
 * Microsoft SharePoint Foundation 2010 Search Query Web Service
 */
    public java.lang.String getQueryServiceSoapAddress();

    public com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap getQueryServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap getQueryServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
