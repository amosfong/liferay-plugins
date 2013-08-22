<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
long definitionId = ParamUtil.getLong(request, "definitionId");

Definition definition = DefinitionLocalServiceUtil.getDefinition(definitionId);
%>

<portlet:renderURL var="searchDefinitionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= searchDefinitionURL %>"
	title='<%= "new-report-entry" %>'
/>

<portlet:renderURL var="searchRequestsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="reports" />
</portlet:renderURL>

<portlet:actionURL name="generateReport" var="generateReportURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/report/generate_report.jsp" />
	<portlet:param name="redirect" value="<%= searchRequestsURL %>" />
</portlet:actionURL>

<aui:form action="<%= generateReportURL %>" method="post" name="fm">
	<aui:input name="definitionId" type="hidden" value="<%= definitionId %>" />

	<portlet:renderURL var="generatedReportsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/report/requested_report_detail.jsp" />
	</portlet:renderURL>

	<aui:input name="generatedReportsURL" type="hidden" value="<%= generatedReportsURL %>" />

	<liferay-ui:error exception="<%= EntryEmailDeliveryException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= EntryEmailNotificationsException.class %>" message="please-enter-a-valid-email-address" />

	<aui:select label="report-format" name="format">

		<%
		for (ReportFormat reportFormat : ReportFormat.values()) {
		%>

			<aui:option label="<%= reportFormat.getValue() %>" value="<%= reportFormat.getValue() %>" />

		<%
		}
		%>

	</aui:select>

	<%
	String reportParameters = definition.getReportParameters();
	%>

	<c:if test="<%= reportParameters.length() > 0 %>">
		<aui:field-wrapper helpMessage="entry-report-parameters-help" label="report-parameters">
			<table class="lfr-table">
			<tr>

			<%
			JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray(reportParameters);

			for (int i = 0; i < reportParametersJSONArray.length(); i++) {
				JSONObject reportParameterJSONObject = reportParametersJSONArray.getJSONObject(i);

				String key = reportParameterJSONObject.getString("key");
				String type = reportParameterJSONObject.getString("type");
				String value = reportParameterJSONObject.getString("value");
			%>

				<c:choose>
					<c:when test='<%= type.equals("date") %>'>
						<td>
							<%= key %>
						</td>
						<td>

							<%
							String[] date = value.split("-");

							Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

							calendar.set(Calendar.YEAR, GetterUtil.getInteger(date[0]));
							calendar.set(Calendar.MONTH, GetterUtil.getInteger(date[1]) - 1);
							calendar.set(Calendar.DATE, GetterUtil.getInteger(date[2]));
							%>

							<liferay-ui:input-date
								dayParam='<%= key + "Day" %>'
								dayValue="<%= calendar.get(Calendar.DATE) %>"
								disabled="<%= false %>"
								firstDayOfWeek="<%= calendar.getFirstDayOfWeek() - 1 %>"
								monthParam='<%= key + "Month" %>'
								monthValue="<%= calendar.get(Calendar.MONTH) %>"
								yearParam='<%= key +"Year" %>'
								yearValue="<%= calendar.get(Calendar.YEAR) %>"
							/>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<%= key %>
						</td>
						<td>
							<span class="field field-text">
								<input name="<portlet:namespace /><%= "parameterValue" + key %>" type="text" value="<%= value %>" /><br />
							</span>
						</td>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

			</tr>
			</table>
		</aui:field-wrapper>
	</c:if>

	<aui:input label="email-notifications" name="emailNotifications" type="text" />

	<aui:input label="email-recipient" name="emailDelivery" type="text" />

	<aui:field-wrapper label="permissions">
		<liferay-ui:input-permissions modelName="<%= Entry.class.getName() %>" />
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button type="submit" value="generate" />

		<aui:button href="<%= searchDefinitionURL %>" type="cancel" />
	</aui:button-row>
</aui:form>