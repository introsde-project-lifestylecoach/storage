package lifecoach.storage.endpoint;

import lifecoach.storage.webservice.StorageImplementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

import javax.xml.ws.Endpoint;

public class StoragePublisher 
{
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        String PORT = "6902";
        String BASE_URL = "/ws/storage";
        
        if (HOSTNAME.equals("127.0.0.1"))
        {
            HOSTNAME = "localhost";
        }       

        if (String.valueOf(System.getenv("PORT")) != "null")
        {
            PORT = String.valueOf(System.getenv("PORT"));
        }
        
        String endpointUrl = PROTOCOL + HOSTNAME + ":" + PORT + BASE_URL;
        
        System.out.println("Starting People Service...");
        System.out.println("--> Published. Check out " + endpointUrl + "?wsdl");
        Endpoint.publish(endpointUrl, new StorageImplementation());
    }
}
