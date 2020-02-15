package monsterfighter;

public class Utils {
    public static boolean choice_valid(String choice){
        boolean is_num = true;
        for (int i = 0; i < choice.length(); i++) {
            if(!(choice.charAt(i) <= '9' && choice.charAt(i) >= '0')){
                is_num = false;
                break;
            }
        }
        return is_num;
    }
}
