package helpers;

public class OutputHelper {
    public static final String repeat(String character, int times){
        String theText = "";
        for(int num = 0; num < times; num++){
            theText += character;
        }
        return theText;
    }
}
