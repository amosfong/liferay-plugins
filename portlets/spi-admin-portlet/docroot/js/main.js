AUI.add(
	'liferay-spi-definition', function(A) {
		var CSS_TABLE_DATA = '.table-data';
		var CSS_LABEL_IMPORTANT = 'label-important';
		var CSS_LABEL_INFO = 'label-info';
		var CSS_LABEL_SUCCESS = 'label-success';
		var CSS_SEARCH_CONTAINER = '#spiDefinitionsSearchContainer';

		var CACHED_STATUS = 'cachedStatus';
		var DELAY = 'delay';
		var KEY = '.spi-status-column .label[data-id="{spiDefinitionId}"]';

		var STARTED = 'started';
		var STARTING = 'starting';
		var STOPPED = 'stopped';
		var STOPPING = 'stopping';

		var SPI_STATUS_MAP = [STARTED, STARTING, STOPPED, STOPPING];

		var SEARCH_CONTAINER = 'searchContainer';
		var REGISTERED = 'registered';

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

							return instance.one(CSS_SEARCH_CONTAINER);
						}
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'liferay-spi-definition',

				prototype: {
					initializer: function() {
						var instance = this;

						instance.getDefinitionsStatus();
					},

					getDefinitionsStatus: function() {
						var instance = this;

						Liferay.Service(
							'/spi-admin-portlet.spidefinition/get-spi-definitions',
							function(response) {
								if (response.length) {
									A.Array.each(
										response,
										function(item, index, collection) {
											var name = item.name;

											var status = item.status;

											if (A.Lang.isNumber(status)) {
												var cachedStatus = instance.get(CACHED_STATUS);

												cachedStatus[name] = status;

												instance._updateRowNodeLabelStatus(item);

												instance._updateCachedStatus(name, status);
											}
										}
									);

									var getDefinitionsStatus = instance.getDefinitionsStatus.bind(instance);

									setTimeout(getDefinitionsStatus, instance.get(DELAY));
								}
							}
						);
					},

					_getSearchContainerTableData: function() {
						var instance = this;

						var searchContainerTableData = instance._searchContainerTableData;

						if (!searchContainerTableData) {
							searchContainerTableData = instance.get(SEARCH_CONTAINER).one(CSS_TABLE_DATA);

							instance._searchContainerTableData = searchContainerTableData;
						}

						return searchContainerTableData;
					},

					_getSPIStatusLabel: function(item) {
						var instance = this;

						var searchContainerTableData = instance._getSearchContainerTableData();

						var dataIdNodeSelector = A.Lang.sub(KEY, item);

						return searchContainerTableData.one(dataIdNodeSelector);
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

						if (status === 0) {
							node.addClass(CSS_LABEL_SUCCESS);
						}
						else if (status === 1 || status === 3) {
							node.addClass(CSS_LABEL_INFO);
						}
						else {
							node.addClass(CSS_LABEL_IMPORTANT);
						}
					},

					_updateRowNodeLabelStatus: function(item) {
						var instance = this;

						var labelNode = instance._getSPIStatusLabel(item);

						if (labelNode) {
							var status = SPI_STATUS_MAP[item.status];

							var statusText = Liferay.Language.get(status);

							labelNode.text(statusText);

							instance._updateLabelClass(labelNode, item.status);
						}
					}
				}
			}
		);

		Liferay.Portlet.SPIDefinition = SPIDefinition;
	},
	'',
	{
		requires: ['aui-component', 'liferay-portlet-base']
	}
);