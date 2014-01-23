AUI.add(
	'liferay-spi-definition',
	function(A) {
		var Lang = A.Lang;

		var CACHED_STATUS = 'cachedStatus';

		var CSS_DROPDOWN_MENU = '.dropdown-menu';

		var CSS_LABEL_INFO = 'label-info';

		var CSS_LABEL_IMPORTANT = 'label-important';

		var CSS_LABEL_SUCCESS = 'label-success';

		var MENU_LIST_CONTAINER = 'menuListContainer';

		var TPL_SPI_DEFINITION_ID = 'spiDefinitionId={id}';

		var TPL_DATA_ID_NODE_SELECTOR = '.spi-status-column .label[data-id="{spiDefinitionId}"]';

		var URLS = 'urls';

		var SPIDefinition = A.Component.create(
			{
				ATTRS: {
					cachedStatus: {
						value: {}
					},

					delay: {
						value: 5000
					},

					searchContainer: {
						valueFn: function() {
							var instance = this;

							return instance.one('#spiDefinitionsSearchContainerSearchContainer');
						}
					},

					spiDefinitionActionURL: {
						value: ''
					},

					urls: {
						value: {}
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'liferay-spi-definition',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._getDefinitionsStatus();
					},

					_getDefinitionsStatus: function() {
						var instance = this;

						Liferay.Service(
							'/spi-admin-portlet.spidefinition/get-spi-definitions',
							function(response) {
								if (response.length) {
									var updateItemStatus = A.bind(instance._updateItemStatus, instance);

									A.Array.each(response, updateItemStatus);

									var getDefinitionsStatus = A.bind(instance._getDefinitionsStatus, instance);

									setTimeout(getDefinitionsStatus, instance.get('delay'));
								}
							}
						);
					},

					_getSearchContainerTableData: function() {
						var instance = this;

						var searchContainerTableData = instance._searchContainerTableData;

						if (!searchContainerTableData) {
							searchContainerTableData = instance.get('searchContainer').one('.table-data');

							instance._searchContainerTableData = searchContainerTableData;
						}

						return searchContainerTableData;
					},

					_getSPIStatusLabel: function(item) {
						var instance = this;

						var searchContainerTableData = instance._getSearchContainerTableData();

						var dataIdNodeSelector = Lang.sub(TPL_DATA_ID_NODE_SELECTOR, item);

						return searchContainerTableData.one(dataIdNodeSelector);
					},

					_getURL: function(id) {
						var instance = this;

						var urls = instance.get(URLS);

						var url = urls[id];

						if (!url) {
							var param = Lang.sub(
								instance.ns(TPL_SPI_DEFINITION_ID),
								{
									id: id
								}
							);

							url = Liferay.Util.addParams(param, instance.get('spiDefinitionActionURL'));

							urls[id] = url;

							instance.set(URLS, urls);
						}

						return url;
					},

					_updateCachedStatus: function(key, value) {
						var instance = this;

						var cachedStatus = instance.get(CACHED_STATUS);

						cachedStatus[key] = value;

						instance.set(CACHED_STATUS, cachedStatus);
					},

					_updateLabelClass: function(node, status) {
						var instance = this;

						node.removeClass(CSS_LABEL_INFO);
						node.removeClass(CSS_LABEL_IMPORTANT);
						node.removeClass(CSS_LABEL_SUCCESS);

						var labelCssClass = CSS_LABEL_IMPORTANT;

						if (status === 0) {
							labelCssClass = CSS_LABEL_SUCCESS;
						}
						else if (status === 1 || status === 3) {
							labelCssClass = CSS_LABEL_INFO;
						}

						node.addClass(labelCssClass);
					},

					_updateRowNodeActionMenu: function(labelNode) {
						var instance = this;

						var rowNode = labelNode.ancestor('tr');

						var actionsMenuTrigger = rowNode.one('.dropdown-toggle');

						var spiDefinitionId = rowNode.one('.spi-status-column [data-id]').getData('id');

						var actionsMenuNode = actionsMenuTrigger.getData(MENU_LIST_CONTAINER) || actionsMenuTrigger.next(CSS_DROPDOWN_MENU);

						A.io.request(
							instance._getURL(spiDefinitionId),
							{
								after: {
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										var tempNode = A.Node.create(responseData);

										var dropdownMenuFragment = tempNode.one('#p_p_id' + instance.NS + ' .portlet-body ' + CSS_DROPDOWN_MENU + '.lfr-menu-list');

										actionsMenuNode.html(dropdownMenuFragment.html());

										actionsMenuTrigger.setData(MENU_LIST_CONTAINER, actionsMenuNode);
									}
								},
								dataType: 'html'
							}
						);
					},

					_updateRowNodeLabelStatus: function(item) {
						var instance = this;

						var labelNode = instance._getSPIStatusLabel(item);

						if (labelNode) {
							var status = item.status;

							var statusText = '';

							if (status === 0) {
								statusText = Liferay.Language.get('started');
							}
							else if (status === 1) {
								statusText = Liferay.Language.get('starting');
							}
							else if (status === 2) {
								statusText = Liferay.Language.get('stopped');
							}
							else if (status === 3) {
								statusText = Liferay.Language.get('stopping');
							}

							labelNode.text(statusText);

							instance._updateLabelClass(labelNode, status);

							instance._updateRowNodeActionMenu(labelNode);
						}
					},

					_updateItemStatus: function(item) {
						var instance = this;

						var status = item.status;

						if (Lang.isNumber(status)) {
							var cachedStatus = instance.get(CACHED_STATUS);

							var name = item.name;

							var cachedValue = cachedStatus[name];

							if (!cachedValue || (cachedValue != status)) {
								instance._updateRowNodeLabelStatus(item);

								instance._updateCachedStatus(name, status);
							}
						}
					}
				}
			}
		);

		Liferay.Portlet.SPIDefinition = SPIDefinition;
	},
	'',
	{
		requires: ['aui-component', 'aui-io-request', 'liferay-portlet-base']
	}
);