/**
 * QueryServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices.SharePoint.QueryService;

public interface QueryServiceSoap extends java.rmi.Remote {
    public java.lang.String query(java.lang.String queryXml) throws java.rmi.RemoteException;
    public com.microsoft.webservices.SharePoint.QueryService.QueryExResponseQueryExResult queryEx(java.lang.String queryXml) throws java.rmi.RemoteException;
    public java.lang.String registration(java.lang.String registrationXml) throws java.rmi.RemoteException;
    public java.lang.String status() throws java.rmi.RemoteException;
}
