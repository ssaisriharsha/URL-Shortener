package com.ssaisriharsha.UrlShortener.Utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class DomainProtocolExtractor {
    public static Optional<String[]> extract(String url) {
        try {
            URI uri = new URI(url);
            String protocol=uri.getScheme()+"://";
            String domain=uri.getSchemeSpecificPart().replace("//", "");
            return Optional.of(new String[]{domain, protocol});
        }
        catch(URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
//    public static void main(String[] args) {
//        String[] arr=extract("https://www.instagram.com/");
//        assert arr != null;
//        for(var x: arr) {
//            System.out.println(x);
//        }
//    }
}
