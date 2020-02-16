package monsterfighter;

public class Utils {
    public static boolean choice_valid(String choice){
        for (int i = 0; i < choice.length(); i++) {
            if(!(choice.charAt(i) <= '9' && choice.charAt(i) >= '0')) {
                return false; /* even if one isn't a number that terminates the whole check and returns false */
            }
        }
        return true; /* if the function hasn't terminated return true */
    }
}
