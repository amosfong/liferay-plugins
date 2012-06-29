create index IX_7F79EB6F on Reports_Definition (companyId);
create index IX_6C1481B1 on Reports_Definition (groupId);
create index IX_6510CC7B on Reports_Definition (uuid_);
create index IX_F08C2CCD on Reports_Definition (uuid_, companyId);
create unique index IX_D4E3828F on Reports_Definition (uuid_, groupId);

create index IX_C9381DA7 on Reports_Source (companyId);
create index IX_C5A9E1E9 on Reports_Source (groupId);
create index IX_9670DAB3 on Reports_Source (uuid_);
create index IX_89138B95 on Reports_Source (uuid_, companyId);
create unique index IX_A3C3F357 on Reports_Source (uuid_, groupId);