
package ws.adaptor;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AdaptorService", targetNamespace = "http://webservice.adaptor.lifecoach/", wsdlLocation = "http://localhost:6904/ws/adaptorBmi?wsdl")
public class AdaptorService
    extends Service
{

    private final static URL ADAPTORSERVICE_WSDL_LOCATION;
    private final static WebServiceException ADAPTORSERVICE_EXCEPTION;
    private final static QName ADAPTORSERVICE_QNAME = new QName("http://webservice.adaptor.lifecoach/", "AdaptorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:6904/ws/adaptorBmi?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ADAPTORSERVICE_WSDL_LOCATION = url;
        ADAPTORSERVICE_EXCEPTION = e;
    }

    public AdaptorService() {
        super(__getWsdlLocation(), ADAPTORSERVICE_QNAME);
    }

    public AdaptorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ADAPTORSERVICE_QNAME, features);
    }

    public AdaptorService(URL wsdlLocation) {
        super(wsdlLocation, ADAPTORSERVICE_QNAME);
    }

    public AdaptorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ADAPTORSERVICE_QNAME, features);
    }

    public AdaptorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AdaptorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Adaptor
     */
    @WebEndpoint(name = "AdaptorImplementationPort")
    public Adaptor getAdaptorImplementationPort() {
        return super.getPort(new QName("http://webservice.adaptor.lifecoach/", "AdaptorImplementationPort"), Adaptor.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Adaptor
     */
    @WebEndpoint(name = "AdaptorImplementationPort")
    public Adaptor getAdaptorImplementationPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.adaptor.lifecoach/", "AdaptorImplementationPort"), Adaptor.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ADAPTORSERVICE_EXCEPTION!= null) {
            throw ADAPTORSERVICE_EXCEPTION;
        }
        return ADAPTORSERVICE_WSDL_LOCATION;
    }

}