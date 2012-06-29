create table KaleoProcess (
	kaleoProcessId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	DDLRecordSetId LONG,
	DDMTemplateId LONG
);

create table KaleoProcessLink (
	kaleoProcessLinkId LONG not null primary key,
	kaleoProcessId LONG,
	workflowTaskName VARCHAR(75) null,
	DDMTemplateId LONG
);