package org.prokarma.web.demo.api;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.log4j.Logger;

public class CertificateConfig {

    private static final Logger LOGGER = Logger.getLogger(CertificateConfig.class);

    static {
        System.setProperty("javax.net.ssl.keyStore",
                "C:\\Program Files\\Java\\jre7\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
    }

    public static SSLSocketFactory setCertificate() {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        SSLSocketFactory sslSocketFactory = null;

        final char[] passphrase = "changeit".toCharArray(); // password
        try {
            final KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream(
                    "C:\\Program Files\\Java\\jre7\\lib\\security\\cacerts"), passphrase);
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm());
            tmf.init(keystore);
            final SSLContext context = SSLContext.getInstance("TLSv1.2");
            final TrustManager[] trustManagers = tmf.getTrustManagers();

            context.init(null, trustManagers, null);

            sslSocketFactory = new SSLSocketFactory(context);

        } catch (final Exception e) {
            LOGGER.error("Exception occurred while getting keystore" + e.getMessage(), e);
        }
        return sslSocketFactory;
    }
}
