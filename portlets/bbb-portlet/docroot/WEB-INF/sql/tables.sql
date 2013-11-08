create table BBB_MeetingEntry (
	meetingEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	meetingServerId LONG,
	name VARCHAR(75) null,
	attendeePassword VARCHAR(75) null,
	moderatorPassword VARCHAR(75) null,
	status INTEGER
);

create table BBB_MeetingParticipant (
	meetingParticipantId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	meetingEntryId LONG,
	name VARCHAR(75) null,
	emailAddress VARCHAR(75) null
);

create table BBB_MeetingServer (
	meetingServerId LONG not null primary key,
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