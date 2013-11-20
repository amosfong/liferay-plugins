create index IX_A89D731 on BBBMeeting (bbbServerId);
create index IX_4E58622B on BBBMeeting (groupId);
create index IX_85871D45 on BBBMeeting (status);

create index IX_9D0878BF on BBBParticipant (bbbMeetingId);

create index IX_A7460C9F on BBBServer (groupId);

create index IX_3D92D134 on BBB_BBBMeeting (bbbServerId);

create index IX_AFAA489C on BBB_BBBParticipant (bbbMeetingId);

create index IX_1BB39CBC on BBB_BBBServer (groupId);