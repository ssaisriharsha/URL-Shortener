package com.ssaisriharsha.UrlShortener.Utilities;

import java.net.URI;
import java.net.URISyntaxException;

public class ProtocolDomainExtractor {
    public static String[] extract(String url) {
        try {
            URI uri = new URI(url);
            String protocol=uri.getScheme()+"://";
            String domain=uri.getSchemeSpecificPart().replace("//", "");
            return new String[]{domain, protocol};
        }
        catch(URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String[] arr=extract("https://www.instagram.com/");
        assert arr != null;
        for(var x: arr) {
            System.out.println(x);
        }
    }
}
