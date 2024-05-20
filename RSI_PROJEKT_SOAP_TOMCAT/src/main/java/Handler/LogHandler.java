package Handler;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        String direction = isOutbound ? "OUTBOUND" : "INBOUND";

        SOAPMessage message = context.getMessage();

        try {
            // Create a log entry with a timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String timestamp = sdf.format(new Date());

            // Write log to a file
            String logFileName = "soap_messages_log.txt";
            try (FileWriter fw = new FileWriter(logFileName, true);
                 PrintWriter pw = new PrintWriter(fw)) {

                pw.println("Timestamp: " + timestamp);
                pw.println("Direction: " + direction);

                String endpoint = context.get(MessageContext.WSDL_OPERATION).toString().split("}")[1];
                pw.println("Endpoint: " + endpoint);
                pw.println("---------------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}