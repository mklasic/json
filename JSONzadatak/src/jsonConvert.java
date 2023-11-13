import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

public class jsonConvert {

    public static void main(String[] args) {
    	//dio jsona sa wikipedije
        String jsonResponse = "{\"value\":\"Raiffeisen\", \"inputId\":\"ooui-php-1\", \"title\":\"Search Wikipedia\"}";
        
        try {
            // Convert JSON to XML
            String xmlResponse = convertJsonToXml(jsonResponse);

            // Print the XML response
            System.out.println("Converted XML:\n" + xmlResponse);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    private static String convertJsonToXml(String jsonResponse) throws IOException, JAXBException {
        // Parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Convert JSON to XML using JAXB
        JAXBContext jaxbContext = JAXBContext.newInstance(JsonNode.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Convert JSON node to XML
        StringWriter writer = new StringWriter();
        marshaller.marshal(jsonNode, writer);

        return writer.toString();
    }
}

