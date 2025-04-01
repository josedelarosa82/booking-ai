/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.booking_ai.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jcdelgadop
 */
@Slf4j
public class EncriptTool {

    public static String encodeBase64(String strEntrada) throws IOException {
        String strEncoded = "";
        byte[] encoded = Base64.encodeBase64(strEntrada.getBytes());
        return new String(encoded);
    }

    public static String decodeBase64(String strEntrada) {
        String strDecoded = "";
        byte[] decoded = Base64.decodeBase64(strEntrada);
        return new String(decoded);
    }

    public static String encodeMd5Signature(String apiKey, String merchantId, String referenceCode, String tx_value, String currency) {
        String cadMd5 = "";

        try {
            cadMd5 = encodeMd5(apiKey + "~" + merchantId + "~" + referenceCode + "~" + tx_value + "~" + currency);
            //log.info("CLIENTES ==> [Utilidades.encodeMd5Signature] cadMd5 = " + cadMd5);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }

        return cadMd5;
    }

    public static String encodeMd5(String texto) {
        //log.info("CLIENTES ==> Encriptando cadena = " + texto);
        String cadMd5 = "";

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(texto.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            cadMd5 = hashtext;
            //log.info("CLIENTES ==> [Utilidades.encryptarMd5] cadMd5 = " + cadMd5);

        } catch (NoSuchAlgorithmException ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }

        return cadMd5;
    }

}
