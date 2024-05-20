package WS;

import WS.Model.Event;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(name="ImageService")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface IWebService {
    @WebMethod
    List<Event> getEventsForDay(Date date);

    @WebMethod
    List<Event> getEventsForWeek(Date inputDate);

    @WebMethod
    Event getEventInformation(int eventId);

    @WebMethod
    int addEvent(@WebParam(name = "name") String name,
                 @WebParam(name = "type") String type,
                 @WebParam(name = "date") Date date,
                 @WebParam(name = "description") String description);

    @WebMethod
    void modifyEventInformation(@WebParam(name = "eventId") int eventId,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "type") String type,
                                @WebParam(name = "date") Date date,
                                @WebParam(name = "description") String description);

    @WebMethod
    List<Event> getAllEvents();

    @WebMethod
    byte[] generatePDFReport(ArrayList<Event> events);
}
