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

package com.liferay.chat.video;

import java.util.List;

/**
 * @author Philippe Proulx
 */
public class WebRTCManager {

    public boolean clientIsAvailable(long userId) {
        return false;
    }

    protected WebRTCClient getClientUnsafe(long userId) {
        return null;
    }

    public List<Long> getAvailableClientsIds() {
        return null;
    }

    public WebRTCClient getClient(long userId) {
        return null;
    }

    public void removeClient(long userId) {
    }

    protected void addNonExistingClient(long userId) {
    }

    public void updatePresence(long userId) {
    }

    public static void checkAllManagersPresences() {
    }

    public static void checkAllManagersConnectionsStates() {
    }

    protected void checkConnectionsStates() {
    }

    protected void checkPresences() {
    }

}