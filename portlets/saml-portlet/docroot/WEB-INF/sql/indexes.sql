create index IX_8EDF9D43 on SamlIdpSpSession (samlIdpSsoSessionId);
create index IX_F2B40CDF on SamlIdpSpSession (samlIdpSsoSessionId, samlSpEntityId);

create index IX_5E8BFDF9 on SamlIdpSsoSession (samlIdpSsoSessionKey);

create index IX_10D77E09 on SamlSpAuthRequest (samlIdpEntityId, samlSpAuthRequestKey);

create index IX_5615F9DD on SamlSpMessage (samlIdpEntityId, samlIdpResponseKey);

create index IX_85F532ED on SamlSpSession (jSessionId);
create index IX_1040A689 on SamlSpSession (nameIdValue);