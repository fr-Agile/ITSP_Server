package jp.ac.titech.itpro.sds.fragile.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class AddressChecker {
    public static boolean check(String address) {
        try {
            InternetAddress iaddr = new InternetAddress(address, true);
            return true;
        } catch (AddressException e) {
            return false;
        }
    }
}
