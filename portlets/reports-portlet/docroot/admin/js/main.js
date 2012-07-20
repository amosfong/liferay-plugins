Liferay.Report = {
	
	deleteParameter: function(parameterKey, parameterValue, parameterType) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-entry'))) {
			var reportParameters = AUI().one('.reportParameters').get('value');

			var jsonReportParameters = JSON.parse(reportParameters);

			for (i = 0; i < jsonReportParameters.length; i++) {
				var elementParam = jsonReportParameters[i];

				if (elementParam.key == parameterKey) {
					jsonReportParameters.splice(i, 1);

					break;
				}
			}

			reportParameters = JSON.stringify(jsonReportParameters);

			AUI().one('.reportParameters').set('value', reportParameters);

			var key = ('.report-tag-' + parameterKey).replace(/ /g,"BLANK");

			AUI().one(key).remove(true);
		}
	},

	initialize: function(param) {
		var instance = this;

		var namespace = param.namespace;

		instance._portletMessageContainer = AUI().one('.report-message');

		instance._displayParameters(param.parameters);

		AUI().one('.add-parameter').on(
			'click',
			function() {
				instance._addParameter(namespace);
			}
		);

		AUI().one('.removeExisting').on(
			'click',
			function() {
				AUI().one('.existingFile').setStyle('display', 'none');
				AUI().one('.templateFile').setStyle('display', 'block');
				AUI().one('.templateUpdated').set('value', 'true');
				AUI().one('.cancelUpdateTemplateFile').setStyle('display', 'block');
			}
		);
		
		AUI().one('.cancelUpdateTemplateFile').on(
			'click',
			function() {
				AUI().one('.existingFile').setStyle('display', 'block');
				AUI().one('.templateFile').setStyle('display', 'none');
				AUI().one('.templateUpdated').set('value', 'false');
				AUI().one('.cancelUpdateTemplateFile').setStyle('display', 'none');
			}
		);

		AUI().one('.parameters-input-type').on(
			'change',
			function(){
				var parametersValueFieldSet = AUI().one('.parameters-value-field-set');
				var parametersInputDate = AUI().one('.parameters-input-date');
				var parametersValue = AUI().one('.parameters-value');

				if (this.get('value') == 'text') {
					parametersValue.set('value', '');
					parametersValue.attr('disabled', '');
					parametersInputDate.setStyle('display', 'none');
					parametersValueFieldSet.setStyle('display', 'block');
				}

				if (this.get('value') == 'date') {
					parametersValueFieldSet.setStyle('display', 'none');
					parametersInputDate.setStyle('display', 'block');
				}

				if (this.get('value') == 'startDateDay') {
					parametersInputDate.setStyle('display', 'none');
					parametersValueFieldSet.setStyle('display', 'block');
					parametersValue.attr('disabled','disabled');
					parametersValue.set('value', '${startDateDay}');
				}

				if (this.get('value') == 'endDateDay') {
					parametersInputDate.setStyle('display', 'none');
					parametersValueFieldSet.setStyle('display', 'block');
					parametersValue.attr('disabled','disabled');
					parametersValue.set('value', '${endDateDay}');
				}
			}
		);
	},

	_addParameter: function(namespace) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		var parameterKey = AUI().one('.parameters-key').get('value');
		var parameterValue = AUI().one('.parameters-value').get('value');
		var parametersType = AUI().one('.parameters-input-type').get('value');
		var reportParameters = AUI().one('.reportParameters').get('value');

		// Validate

		if (parameterKey.length == 0) {
			AUI().all('.portlet-msg-error').setStyle('display', 'none');

			instance._sendMessage('please-enter-a-valid-report-parameter-key');

			return;
		}

		if (parametersType != 'date' && parameterValue.length == 0) {
			AUI().all('.portlet-msg-error').setStyle('display', 'none');

			instance._sendMessage('please-enter-a-valid-report-parameter-value');

			return;
		}

		if ((parameterKey.indexOf(',') > 0) || (parameterKey.indexOf('=') > 0) ||(parameterValue.indexOf('=') > 0)) {
			instance._sendMessage('one-of-your-fields-contains-invalid-characters');

			return;
		}

		if (reportParameters.length > 0) {
			var jsonReportParameters = JSON.parse(reportParameters);

			for (i = 0; i < jsonReportParameters.length; i++) {
				var elementParam = jsonReportParameters[i];

				if (elementParam.key == parameterKey) {
					instance._sendMessage('that-vocabulary-already-exists');

					return;
				}
			}
		}

		if (parametersType == 'date') {
			var parameterDateDay = AUI().one('#'+namespace+'parameterDateDay');
			var parameterDateMonth = AUI().one('#'+namespace+'parameterDateMonth');
			var parameterDateYear = AUI().one('#'+namespace+'parameterDateYear');

			var parameterDate = new Date();

			parameterDate.setDate(parameterDateDay.get('value'));
			parameterDate.setMonth(parameterDateMonth.get('value'));
			parameterDate.setYear(parameterDateYear.get('value'));

			parameterValue = AUI().DataType.Date.format(parameterDate);
		}

		instance._addTag(parameterKey, parameterValue, parametersType);

		instance._addReportParameter(parameterKey, parameterValue, parametersType);

		AUI().one('.parameters-key').set('value', '');
		AUI().one('.parameters-value').set('value', '');
	},

	_addReportParameter: function(parameterKey, parameterValue, parameterType) {
		var reportParameters = AUI().one('.reportParameters').get('value');

		var jsonReportParameters = eval(reportParameters);

		var jsonObject = {key: parameterKey, value: parameterValue, type: parameterType};

		jsonReportParameters.push(jsonObject);

		reportParameters = JSON.stringify(jsonReportParameters);

		AUI().one('.reportParameters').set('value', reportParameters);
	},

	_addTag: function(parameterKey, parameterValue, parameterType) {
		var tagsContainer = AUI().one(".report-tags");

		var oldTags = tagsContainer.get('innerHTML');

		var key = ('report-tag-' + parameterKey).replace(/ /g,"BLANK");

		var innerHTML = "<div class='" + key + "' >";

		innerHTML += "<input type='text' disabled='disabled' value='" + parameterKey + "' >";
		innerHTML += "<input type='text' disabled='disabled' value='" + parameterValue + "' >";
		innerHTML += "<img alt='" + Liferay.Language.get('delete') + "' src='" + themeDisplay.getPathThemeImages() + "/common/delete.png' ";
		innerHTML += " onClick=\"Liferay.Report.deleteParameter('" + parameterKey + "','" + parameterValue + "','" + parameterType + "'); \" />";
		innerHTML += "<//div>";

		tagsContainer.set('innerHTML', oldTags + innerHTML);
	},

	_displayParameters: function(parameters) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		if (!parameters) {
			parameters = '[]';
		}

		AUI().one('.reportParameters').set('value', parameters);

		var jsonReportParameters = JSON.parse(parameters);

		for (i = 0; i < jsonReportParameters.length; i++) {
			var elementParam = jsonReportParameters[i];

			if (elementParam.key && elementParam.value) {
				instance._addTag(elementParam.key, elementParam.value, elementParam.type);
			}
		}
	},

	_sendMessage: function(messageKey) {
		var instance = this;

		instance._portletMessageContainer.addClass('portlet-msg-error')
		instance._portletMessageContainer.set('innerHTML', Liferay.Language.get(messageKey));
		instance._portletMessageContainer.setStyle('display', 'block');
	},
}