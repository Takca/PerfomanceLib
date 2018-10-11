/**
 * Created by user1 on 11.10.2018.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(encode("azAZ"));
    }

    public static String encode(CharSequence rawPassword) {
        String encodePassword = "";
        for (int i = 0; i < rawPassword.length(); i++) {
            encodePassword += (char)((int) rawPassword.charAt(i) + 1);
        }
        return encodePassword;
    }
}
