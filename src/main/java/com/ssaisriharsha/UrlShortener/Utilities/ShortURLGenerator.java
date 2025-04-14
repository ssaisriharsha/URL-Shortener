package com.ssaisriharsha.UrlShortener.Utilities;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class ShortURLGenerator {
    private static final String characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random=new SecureRandom();
    private static final int shortUrlLength=10;
    public static String generate() {
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<shortUrlLength; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
