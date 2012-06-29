create index IX_4DBA0C4E on KaleoDraftDefinition (companyId);
create index IX_94487B1B on KaleoDraftDefinition (companyId, name, version);
create unique index IX_A500C06 on KaleoDraftDefinition (companyId, name, version, draftVersion);