package de.joli.cataloglib.expansion;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/**
 * Created by abel.miranda on 9/29/14.
 */
public class ExpansionDownloader extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsBcBd9w5q49EZpjAJf6MOIH3EyZbgreBgJJIRy4p+Brb1YY8wr+0FbdEyTF2nd3/8x8OB3DsB9vsy331bjLilGP9Z+M9nIEEkLydE5C+9ffq7EedStZ+HUc8vy94/42BXqXzODAAElYUYiaU0bOjZcWI0A++sbxAqN5Fedu/41OG8f0ez2PPC5JRnN/oWJjK+II9RvMrYobufKhtlcX8aw2T2DuPfieytY2l6mhGGPMdFaa4omMmJNj8imZgZPNrKRDE67M8D1XgG/kTg//HtP/p1+47k8NL5YxlpabvA4H0Au5F9jevwIJocvlX19dVO1ltGuMlvjqx8zPr6hN6DwIDAQAB";
    // You should also modify this salt
    public static final byte[] SALT = new byte[] { 1, 42, -12, -1, 54, 98,
            -100, -12, 43, 2, -8, -4, 9, 5, -106, -107, -33, 45, -1, 84
    };

    @Override
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    @Override
    public byte[] getSALT() {
        return SALT;
    }

    @Override
    public String getAlarmReceiverClassName() {
        return ExpansionAlarmReceiver.class.getName();
    }
}
