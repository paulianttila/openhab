/**
 * Copyright (c) 2010-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.action.crypto.internal;

import java.util.Dictionary;

import org.eclipse.smarthome.io.crypto.Crypto;
import org.openhab.action.crypto.internal.CryptoAction;
import org.openhab.core.scriptengine.action.ActionService;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .
 *
 * @author Pauli Anttila
 * @since 1.11.0
 */
public class CryptoActionService implements ActionService, ManagedService {

    private static final Logger logger = LoggerFactory.getLogger(CryptoActionService.class);

    /**
     * Indicates whether this action is properly configured which means all
     * necessary configurations are set. This flag can be checked by the action
     * methods before executing code.
     */
    static boolean isProperlyConfigured = false;

    public CryptoActionService() {
        // nothing to do
    }

    public void activate() {
        logger.debug("Pushover action service activated");
    }

    public void deactivate() {
        logger.debug("Pushover action service deactivated");
    }

    protected void setCryptoModule(Crypto crypto) {
        CryptoAction.setCryptoModule(crypto);
    }

    protected void unsetCryptoModule(Crypto crypto) {
        CryptoAction.setCryptoModule(null);
    }

    @Override
    public String getActionClassName() {
        return CryptoAction.class.getCanonicalName();
    }

    @Override
    public Class<?> getActionClass() {
        return CryptoAction.class;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void updated(Dictionary<String, ?> config) throws ConfigurationException {
        logger.debug("Configuration file is being parsed.");
        if (config != null) {
            logger.debug("Configuration data exists. Parsing the paramaters.");

        } else {
            // Messages can be sent by providing API Key and User key in the action binding, so no issue here.
            logger.debug("The configurations information was empty. No defaults for Crypto loaded.");
        }

        isProperlyConfigured = true;
    }
}
