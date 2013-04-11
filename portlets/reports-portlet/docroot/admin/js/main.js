Liferay.Report = {

	deleteParameter: function(parameterKey, parameterValue, parameterType) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-entry'))) {
			var parametersInput = AUI().one('.report-parameters');

			var reportParameters = JSON.parse(parametersInput.get('value'));

			for (var i in reportParameters) {
				var reportParameter = reportParameters[i];

				if (reportParameter.key == parameterKey) {
					reportParameters.splice(i, 1);

					break;
				}
			}

			parametersInput.set('value', JSON.stringify(reportParameters));

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

		AUI().one('.remove-existing-report').on(
			'click',
			function() {
				AUI().one('.existing-report').setStyle('display', 'none');
				AUI().one('.template-report').setStyle('display', 'block');
				AUI().one('.cancel-update-template-report').setStyle('display', 'block');
			}
		);

		AUI().one('.cancel-update-template-report').on(
			'click',
			function() {
				AUI().one('.existing-report').setStyle('display', 'block');
				AUI().one('.template-report').setStyle('display', 'none');
				AUI().one('.cancel-update-template-report').setStyle('display', 'none');
			}
		);

		AUI().one('.parameters-input-type').on(
			'change',
			function() {
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
		var parameterType = AUI().one('.parameters-input-type').get('value');
		var parameterValue = AUI().one('.parameters-value').get('value');

		// Validate

		if (parameterKey.length == 0) {
			AUI().all('.portlet-msg-error').setStyle('display', 'none');

			instance._sendMessage('please-enter-a-valid-report-parameter-key');

			return;
		}

		if (parameterType != 'date' && parameterValue.length == 0) {
			AUI().all('.portlet-msg-error').setStyle('display', 'none');

			instance._sendMessage('please-enter-a-valid-report-parameter-value');

			return;
		}

		if ((parameterKey.indexOf(',') > 0) || (parameterKey.indexOf('=') > 0) || (parameterValue.indexOf('=') > 0)) {
			instance._sendMessage('one-of-your-fields-contains-invalid-characters');

			return;
		}

		var reportParameters = AUI().one('.report-parameters').get('value');

		if (reportParameters) {
			var reportParametersJSON = JSON.parse(reportParameters);

			for (var i in reportParametersJSON) {
				var reportParameter = reportParametersJSON[i];

				if (reportParameter.key == parameterKey) {
					instance._sendMessage('that-vocabulary-already-exists');

					return;
				}
			}
		}

		if (parameterType == 'date') {
			var parameterDateDay = AUI().one('#' + namespace + 'parameterdateday');
			var parameterDateMonth = AUI().one('#' + namespace + 'parameterdatemonth');
			var parameterDateYear = AUI().one('#' + namespace + 'parameterdateyear');

			var parameterDate = new Date();

			parameterDate.setDate(parameterDateDay.get('value'));
			parameterDate.setMonth(parameterDateMonth.get('value'));
			parameterDate.setYear(parameterDateYear.get('value'));

			parameterValue = AUI().DataType.Date.format(parameterDate);
		}

		instance._addTag(parameterKey, parameterValue, parameterType);

		instance._addReportParameter(parameterKey, parameterValue, parameterType);

		AUI().one('.parameters-key').set('value', '');
		AUI().one('.parameters-value').set('value', '');
	},

	_addReportParameter: function(parameterKey, parameterValue, parameterType) {
		var reportParameters = [];

		var parametersInput = AUI().one('.report-parameters');

		if (parametersInput.get('value')) {
			reportParameters = JSON.parse(parametersInput.get('value'));
		}

		var reportParameter = {
			key: parameterKey,
			value: parameterValue,
			type: parameterType
		};

		reportParameters.push(reportParameter);

		parametersInput.set('value', JSON.stringify(reportParameters));
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

		AUI().one('.report-parameters').set('value', parameters);

		if (!parameters) {
			return;
		}

		var reportParameters = JSON.parse(parameters);

		for (var i in reportParameters) {
			var reportParameter = reportParameters[i];

			if (reportParameter.key && reportParameter.value) {
				instance._addTag(reportParameter.key, reportParameter.value, reportParameter.type);
			}
		}
	},

	_sendMessage: function(messageKey) {
		var instance = this;

		instance._portletMessageContainer.addClass('portlet-msg-error')
		instance._portletMessageContainer.set('innerHTML', Liferay.Language.get(messageKey));
		instance._portletMessageContainer.setStyle('display', 'block');
	}
}