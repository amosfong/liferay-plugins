create table BBB_BBBMeeting (
	bbbMeetingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bbbServerId LONG,
	name VARCHAR(75) null,
	attendeePassword VARCHAR(75) null,
	moderatorPassword VARCHAR(75) null,
	status INTEGER
);

create table BBB_BBBParticipant (
	bbbParticipantId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bbbMeetingId LONG,
	name VARCHAR(75) null,
	emailAddress VARCHAR(75) null
);

create table BBB_BBBServer (
	bbbServerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url VARCHAR(75) null,
	secret VARCHAR(75) null
);