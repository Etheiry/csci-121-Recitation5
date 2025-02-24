public class EmailValidator {
    public static final String PRINTABLE_CHAR = "!#$%&'*+-/=?^_`{|}~";
    public static final char DOT = '.';
    public static final char HYPHEN = '-';
    public static final char AT = '@';

    // Add your enumerated states after this line
    private static final int LOCAL = 0;
    private static final int DOMAIN = 1;


    public static boolean isEmailValid(String address){
        int count = 0;
        int totalDomainCount = 0;
        int currentState = LOCAL;
        char ch;

        for (int index = 0; index < address.length(); index++){
            ch = address.charAt(index);

            if (currentState == LOCAL) {
                // if first char == dot
                if (index == 0 && ch == DOT) {
                    return false;
                }
                // If char is a dot
                if (index > 0 && ch == DOT) {

                    if (address.charAt(index + 1) == DOT || address.charAt(index + 1) == AT) {
                        return false;
                    }
                    currentState = DOMAIN;
                }
                count++;
            }


        }



        return false;
    }
}
