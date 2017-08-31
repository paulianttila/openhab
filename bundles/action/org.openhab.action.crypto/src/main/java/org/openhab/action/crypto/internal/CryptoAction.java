/**
 * Copyright (c) 2010-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.action.crypto.internal;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.eclipse.smarthome.io.crypto.Crypto;
import org.openhab.core.scriptengine.action.ActionDoc;
import org.openhab.core.scriptengine.action.ParamDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author Pauli Anttila
 * @since 1.11.0
 */
public class CryptoAction {

    private static final Logger logger = LoggerFactory.getLogger(CryptoAction.class);
    private static Crypto crypto;

    @ActionDoc(text = "Decrypt text", returns = "Plain text")
    public static String decrypt(@ParamDoc(name = "encryptedText", text = "Encrypted text") String encryptedText) {
        try {
            String val = crypto.decrypt(encryptedText);
            return val;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("Error occured during decryption. ", e);
        }

        return "";
    }

    public static void setCryptoModule(Crypto crypto) {
        CryptoAction.crypto = crypto;
    }
}
