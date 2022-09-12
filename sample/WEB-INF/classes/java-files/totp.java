


import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class totp {
    // public static void main(String args[]) {
    //     SecureRandom random = new SecureRandom();
    //     byte[] bytes = new byte[20];
    //     random.nextBytes(bytes);
    //     Base32 base32 = new Base32();
    //     System.out.println(base32.encodeToString(bytes));
    // }

    // public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
    //     try {
    //         return "otpauth://totp/"
    //                 + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
    //                 + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
    //                 + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
    //     } catch (UnsupportedEncodingException e) {
    //         throw new IllegalStateException(e);
    //     }
    // }
    public String main() {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode("6CKCEQCFW73KMOVC2EBEVWP7X3TDM5GF");
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey); 
    }
    
}
