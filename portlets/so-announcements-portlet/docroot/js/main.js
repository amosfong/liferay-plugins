AUI().use(
	'aui-base',
	'transition',
	function(A) {
		Liferay.namespace('Announcements');

		Liferay.Announcements = {
			init: function(config) {
				var instance = this;

				instance._namespace = config.namespace;
				instance._viewEntriesURL = config.viewEntriesURL;
			},

			toggleEntry: function(event) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.one('#' + instance._namespace + entryId);

				var content = entry.one('.entry-content');
				var contentContainer = entry.one('.entry-content-container');
				var control = entry.one('.toggle-entry');

				var contentHeight = '75px';

				if (entry.hasClass('announcement-collapsed')) {
					entry.removeClass('announcement-collapsed');

					contentContainer.setStyle('height', '75px');
					contentContainer.setStyle('maxHeight', 'none');

					contentHeight = content.getComputedStyle('height');

					if (control) {
						control.html(Liferay.Language.get('view-less'));
					}
				}
				else {
					entry.addClass('announcement-collapsed');

					if (control) {
						control.html(Liferay.Language.get('view-more'));
					}
				}

				contentContainer.transition(
					{
						duration: 0.5,
						easing: 'ease-in-out',
						height: contentHeight
					}
				);
			},

			updateEntries: function(readEntries, start) {
				var instance = this;

				var url = Liferay.Util.addParams('readEntries=' + readEntries, instance._viewEntriesURL) || instance._viewEntriesURL;

				if (readEntries) {
					var node = AUI().one('#' + instance._namespace + 'readEntriesContainer');
				}
				else {
					var node = AUI().one('#' + instance._namespace + 'unreadEntriesContainer');
				}

				if (node) {
					var entries = node.one('.entries');

					if (entries) {
						var start = (start == null) ? entries.attr('data-start') : start;

						url = Liferay.Util.addParams('start=' + start, url) || url;
					}

					if (!node.io) {
						node.plug(
							A.Plugin.IO,
							{
							autoLoad: false
							}
						);
					}

					node.io.set('uri', url);
					node.io.start();
				}
			}
		};
	}
);