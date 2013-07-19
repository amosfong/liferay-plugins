create table SyncDLObject (
	objectId LONG not null primary key,
	companyId LONG,
	createTime LONG,
	modifiedTime LONG,
	repositoryId LONG,
	parentFolderId LONG,
	name VARCHAR(255) null,
	description STRING null,
	checksum VARCHAR(75) null,
	event VARCHAR(75) null,
	lockUserId LONG,
	lockUserName VARCHAR(75) null,
	size_ LONG,
	type_ VARCHAR(75) null,
	typePK LONG,
	typeUuid VARCHAR(75) null,
	version VARCHAR(75) null
);