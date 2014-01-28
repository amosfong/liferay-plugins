AUI.add(
	'liferay-kaleo-forms',
	function(A) {
		var KaleoForms = {
			onCompleteTask: function(event, namespace, randomId) {
				var instance = this;

				var icon = event.currentTarget;

				event.preventDefault();

				var form = A.one('#' + namespace + randomId + 'fm');

				var url = icon.attr('href');

				form.attr(
					{
						action: url,
						method: 'POST'
					}
				);

				var content = A.one('#' + randomId + 'editForm');

				content.show();

				var dialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							bodyContent: content,
							centered: true,
							constrain: true,
							constrain2view: true,
							modal: true,
							title: Liferay.Util.escapeHTML(icon.text()),
							width: 400
						}
					}
				).render();
			}
		};

		Liferay.KaleoForms = KaleoForms;
	},
	'',
	{
		requires: ['liferay-util-window']
	}
);