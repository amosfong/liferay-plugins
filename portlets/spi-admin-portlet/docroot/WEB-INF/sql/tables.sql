create table SPIDefinition (
	spiDefinitionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	applications VARCHAR(75) null,
	jvmArguments VARCHAR(75) null,
	typeSettings VARCHAR(75) null
);