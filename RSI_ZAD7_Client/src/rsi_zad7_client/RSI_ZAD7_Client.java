/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsi_zad7_client;

/**
 *
 * @author chojak
 */
public class RSI_ZAD7_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(hello("test"));
    }

    private static String hello(java.lang.String name) {
        rsi_zad7_server.SecureWebService_Service service = new rsi_zad7_server.SecureWebService_Service();
        rsi_zad7_server.SecureWebService port = service.getSecureWebServicePort();
        return port.hello(name);
    }
    
}
