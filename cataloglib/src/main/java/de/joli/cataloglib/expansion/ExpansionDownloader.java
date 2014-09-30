package de.joli.cataloglib.expansion;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/**
 * Created by abel.miranda on 9/29/14.
 */
public class ExpansionDownloader extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final int EXPANSION_VERSION = 2;
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmAV0pAJ+fFqkXrferkbeBUx/jNRsKQYD1J3haVzpLUaUC/QREWLKqRJm85/TdjyN0fDM57eLjAUTtIY5bnEawbhPsiqjjWezbFaX1i0M4FKgvzGoNIaLrL99OiRTNOj6uCmpEwVVCQnN7yiqt/03l9VptUtpB4CslLQLpZhWh59ufhNS9V5U9IIJAQDNFwKQhMJbKF3dtrvr+14xRsVw92gs7twF9qQmYbs81YUsWDuG84oV86LMNzc9ht3c8MgO+xXgUnZVroLKln1RrN9oqQX5g4rL7t+kLTvH8yQK+zKxcX87GstOy25OIF7s7gSxke/1DkTGppRszaShE8kp5QIDAQAB";
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
