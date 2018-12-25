package ua.nure.kn.dobriak.usermanagement.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@SuppressWarnings("HardCodedStringLiteral")
public class Messages {
    private static final String BUNDLE_NAME = "messages";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {

    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
