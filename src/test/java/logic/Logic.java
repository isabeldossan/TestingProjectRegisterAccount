package logic;

import java.util.Random;

public class Logic {

    private String finishedMail;
    public void setRandomMail() {
        String myChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = 9; //längd på mail

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(myChars.length());
            builder.append(myChars.charAt(index));
            finishedMail = builder.toString();
        }
        finishedMail = finishedMail + "@gmail.com";
    }

    public String getRandomMail() {
        return finishedMail;
    }






 /*
    public String getRandomMail() {
        String finishedMail = "";
        String myChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = 9; //längd på mail

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(myChars.length());
            builder.append(myChars.charAt(index));
            finishedMail = builder.toString();
        }
        return finishedMail + "gmail.com";
    }
    */

}
