package com.epam.javaee.soap;

import static org.junit.Assert.assertFalse;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.junit.Test;

public class NewsServiceIntegrationTest {

    @Test
    public void getAll() throws Exception {

        // Data to access the web service
        URL wsdlDocumentLocation = new URL("http://localhost:8080/view/News?wsdl");
        String namespaceURI = "http://soap.javaee.epam.com/";
        String servicePart = "News";
        String portName = "NewsServiceEndpointPort";
        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        // Creates a service instance
        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        NewsInterface newsSoap = service.getPort(portQN, NewsInterface.class);
        System.out.println(newsSoap.getAll());

        assertFalse("News list shouldn't be null", newsSoap.getAll().isEmpty());
    }
}