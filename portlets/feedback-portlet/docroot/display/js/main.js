AUI().use(
	'aui-base',
	'aui-io-request-deprecated',
	function(A) {
		Liferay.namespace('Feedbacks');

		Liferay.Feedbacks = {
			getPopup: function(url, id, title, modal, width, height) {
				var instance = this;

				if (!instance._popup) {
					instance._popup = Liferay.Util.openWindow(
						{
							cache: false,
							dialog: {
								align: Liferay.Util.Window.ALIGN_CENTER,
								height: height,
								modal: modal,
								width: width
							},
							id: id,
							title: title,
							uri: url
						}
					);
				}

				return instance._popup;
			}
		};
	}
);