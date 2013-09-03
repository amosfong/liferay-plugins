create table SPIDefinition (
	spiDefinitionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(200) null,
	description VARCHAR(500) null,
	applications TEXT null,
	jvmArguments TEXT null,
	typeSettings TEXT null
);