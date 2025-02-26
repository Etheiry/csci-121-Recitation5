public class EmailValidator {
    public static final String PRINTABLE_CHAR = "!#$%&'*+-/=?^_`{|}~";
    public static final char DOT = '.';
    public static final char HYPHEN = '-';
    public static final char AT = '@';

    // Add your enumerated states after this line
    private static final int LOCAL = 0;
    private static final int DOMAIN = 1;
    private static final int DOMAINHYPEN = 2;


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
                // If char is a dot: in local part
                if (index > 0 && ch == DOT) {
                    count++;
                    if (address.charAt(index + 1) == DOT || address.charAt(index + 1) == AT) {
                        return false;
                    }
                }
                if (Character.isLetter(ch) || Character.isDigit(ch) || PRINTABLE_CHAR.indexOf(ch) != -1) {
                    count++;
                    if (count > 63) {
                        return false;
                    }
                }

                if (ch == AT) {
                    count = 0;
                    currentState = DOMAIN;
                }
                if (!Character.isLetter(ch) && !Character.isDigit(ch) && PRINTABLE_CHAR.indexOf(ch) == -1 && ch != DOT && ch != AT) {
                    return false;
                }
            }

            if (currentState == DOMAINHYPEN) {
                count++;
                totalDomainCount++;
                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    currentState = DOMAIN;
                } else if (ch != HYPHEN) {
                    return false;
                }
            }

            if (currentState == DOMAIN) {
                if (count == 0 && (ch == HYPHEN || ch == DOT || PRINTABLE_CHAR.indexOf(ch) != -1)) {
                    return false;
                }

                // domain hyphen
                if (count > 0 && ch == HYPHEN) {
                    count++;
                    totalDomainCount++;
                    currentState = DOMAINHYPEN;
                }

                if (count > 0 && ch == DOT) {
                    if (Character.isLetter(address.charAt(index + 1)) || Character.isDigit(address.charAt(index + 1))) {
                        count++;
                        totalDomainCount++;
                    } else {
                        return false;
                    }
                }

                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    count++;
                    totalDomainCount++;
                }

                if (totalDomainCount > 253) {
                    return false;
                }

            }


        }




        return true;
    }
}
