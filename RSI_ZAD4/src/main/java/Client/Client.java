package Client;

import ServerFromWSDL.GeoIPService;
import ServerFromWSDL.GeoIPServiceSoap;

public class Client {
    public static void main(String[] args) {
        GeoIPService geoIPService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();

        System.out.println("GetLocation: " + geoIPServiceSoap.getLocation());
        System.out.println("GetLocation: " + geoIPServiceSoap.getIpLocation("103.102.38.0")); // pakistan
        System.out.println("GetLocation: " + geoIPServiceSoap.getIpLocation("103.103.112.0")); // pakistan
    }
}
