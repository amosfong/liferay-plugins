create table SamlIdpSpConnection (
	samlIdpSpConnectionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	samlSpEntityId VARCHAR(75) null,
	attributeNames VARCHAR(75) null,
	attributesEnabled BOOLEAN,
	attributesNamespaceEnabled BOOLEAN,
	assertionLifetime LONG,
	enabled BOOLEAN,
	metadataUrl VARCHAR(75) null,
	metadataXml VARCHAR(75) null,
	metadataUpdatedDate DATE null,
	name VARCHAR(75) null,
	nameIdAttribute VARCHAR(75) null,
	nameIdFormat VARCHAR(75) null
);

create table SamlIdpSpSession (
	samlIdpSpSessionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	samlIdpSsoSessionId LONG,
	samlSpEntityId VARCHAR(1024) null,
	nameIdFormat VARCHAR(1024) null,
	nameIdValue VARCHAR(1024) null
);

create table SamlIdpSsoSession (
	samlIdpSsoSessionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	samlIdpSsoSessionKey VARCHAR(75) null
);

create table SamlSpAuthRequest (
	samlSpAuthnRequestId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	samlIdpEntityId VARCHAR(1024) null,
	samlSpAuthRequestKey VARCHAR(75) null
);

create table SamlSpIdpConnection (
	samlSpIdpConnectionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	samlIdpEntityId VARCHAR(75) null,
	assertionSignatureRequired BOOLEAN,
	clockSkew LONG,
	enabled BOOLEAN,
	ldapImportEnabled BOOLEAN,
	metadataUrl VARCHAR(75) null,
	metadataXml VARCHAR(75) null,
	metadataUpdatedDate DATE null,
	name VARCHAR(75) null,
	nameIdFormat VARCHAR(75) null,
	signAuthnRequest BOOLEAN,
	userAttributeMappings VARCHAR(75) null
);

create table SamlSpMessage (
	samlSpMessageId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	samlIdpEntityId VARCHAR(1024) null,
	samlIdpResponseKey VARCHAR(75) null,
	expirationDate DATE null
);

create table SamlSpSession (
	samlSpSessionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	jSessionId VARCHAR(75) null,
	nameIdFormat VARCHAR(1024) null,
	nameIdValue VARCHAR(1024) null,
	terminated_ BOOLEAN
);