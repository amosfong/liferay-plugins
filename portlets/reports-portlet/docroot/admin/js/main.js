Liferay.Report = {
	
	deleteParameter: function(parameterKey, parameterValue, parameterType) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-entry'))) {
			var temp = "";
			var keyArray = new Array();

			var parametersInput = AUI().one('.reportParameters');

			var reportParameters = parametersInput.get('value');

			keyArray = reportParameters.split(',');

			for (i = 0; i < keyArray.length; i++) {
				if (keyArray[i] && (keyArray[i] != (parameterKey + "=" + parameterValue + "=" + parameterType))) {
					temp += keyArray[i] + ",";
				}
			}
			
			parametersInput.set('value', temp);

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
		var reportParameters = AUI().one('.reportParameters').get('value');
		var parametersType = AUI().one('.parameters-input-type').get('value');

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

		if ((parameterKey.indexOf(',') > 0) || (parameterKey.indexOf('=') > 0) || (parameterValue.indexOf(',') > 0) || (parameterValue.indexOf('=') > 0)) {
			instance._sendMessage('one-of-your-fields-contains-invalid-characters');

			return;
		}

		if (reportParameters.length > 0) {
			var keyArray = new Array();

			keyArray = reportParameters.split(',');

			for (i = 0; i < keyArray.length; i++) {
				if (keyArray[i].split('=')[0] == parameterKey) {
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

		AUI().one('.reportParameters').set('value', reportParameters + ',' + parameterKey + '=' + parameterValue + '=' + parametersType);
		AUI().one('.parameters-key').set('value', '');
		AUI().one('.parameters-value').set('value', '');
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

	_displayParameters: function(param) {
		var instance = this;

		instance._portletMessageContainer.setStyle('display', 'none');

		AUI().one('.reportParameters').set('value', param);

		var keyArray = new Array();
		
		keyArray = param.split(',');

		for (i = 0; i < keyArray.length; i++) {
			var keyvalue = keyArray[i].split('=');

			if (keyvalue[0] && keyvalue[1]) {
				instance._addTag(keyvalue[0], keyvalue[1], keyvalue[2]);
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