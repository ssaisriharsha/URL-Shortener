package com.ssaisriharsha.UrlShortener.Utilities;
import java.security.SecureRandom;

public class ShortURLGenerator {
    private static final String characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random=new SecureRandom();
    private static final int shortUrlLength=10;
    public static String generate() {
        StringBuffer sb=new StringBuffer();
        for(int i=0; i<shortUrlLength; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
