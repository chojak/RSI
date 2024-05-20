package WS;

import WS.Methods.Utilities;
import WS.Model.Event;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@MTOM
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@javax.jws.WebService(endpointInterface = "WS.IWebService")
public class WebService implements IWebService {
    private ArrayList<Event> events = new ArrayList();
    private int eventIdCounter = 11;
    public WebService() {
        events.add(new Event(1, "Dni Białegostoku", "Festiwal", new Date(2024 - 1900, 5 - 1, 5), "Coroczne święto, podczas którego prezentowane są kultura, jedzenie i tradycje Białegostoku."));
        events.add(new Event(2, "Półmaraton Białegostoku", "Sport", new Date(2024 - 1900, 5 - 1,5), "Popularne wydarzenie biegowe, przyciągające zawodników z całego kraju."));
        events.add(new Event(3, "Festiwal Jazzowy w Białymstoku", "Muzyka", new Date(2024 - 1900, 5 - 1,6), "Trzy dni jazzowych występów lokalnych i międzynarodowych artystów."));
        events.add(new Event(4, "Festiwal Filmowy w Białymstoku", "Sztuka", new Date(2024 - 1900, 5 - 1, 6), "Święto niezależnego kina z pokazami, warsztatami i sesjami Q&A."));
        events.add(new Event(5, "Jarmark Bożonarodzeniowy w Białymstoku", "Święta", new Date(2024 - 1900, 5 - 1, 10), "Świąteczny jarmark oferujący rękodzieło, tradycyjne smakołyki i rozrywkę."));
        events.add(new Event(6, "Spacer Artystyczny po Białymstoku", "Sztuka", new Date(2024 - 1900, 5 - 1, 10), "Prowadzone przez lokalnych artystów spacery po vibrantnej scenie street artu w Białymstoku."));
        events.add(new Event(7,"Festiwal Piwa w Białymstoku", "Jedzenie & Napoje", new Date(2024 - 1900, 5 - 1, 14), "Weekendowe święto piwa rzemieślniczego z degustacjami, muzyką na żywo i aktywnościami związanymi z piwem."));
        events.add(new Event(8, "Tydzień Teatralny w Białymstoku", "Sztuka", new Date(2024 - 1900, 5 - 1,14), "Prezentacja przedstawień teatralnych lokalnych grup dramatycznych oraz gości."));
        events.add(new Event(9, "Targi Książki w Białymstoku", "Literatura", new Date(2024 - 1900, 5 - 1, 14), "Wydarzenie dla miłośników książek z czytaniem przez autorów, podpisywaniem książek i dyskusjami literackimi."));
        events.add( new Event(10, "Festiwal Wiosenny w Białymstoku", "Festiwal", new Date(2024 - 1900, 5 - 1, 14), "Święto wiosny z koncertami plenerowymi, wystawami sztuki i atrakcjami dla całej rodziny."));
    }

    @Override
    public List<Event> getEventsForDay(Date date) {
        List<Event> eventsForDay = new ArrayList<>();
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(date);
        for (Event event : events) {
            Calendar eventDate = Calendar.getInstance();
            eventDate.setTime(event.date);
            if (inputDate.get(Calendar.DAY_OF_YEAR) == eventDate.get(Calendar.DAY_OF_YEAR)
                    && inputDate.get(Calendar.YEAR) == eventDate.get(Calendar.YEAR)) {
                eventsForDay.add(event);
            }
        }
        return eventsForDay;
    }

    @Override
    public List<Event> getEventsForWeek(Date inputDate) {
        List<Event> eventsForWeek = new ArrayList<>();
        Date monday = Utilities.firstDayOfWeek(inputDate);
        Date sunday = Utilities.lastDayOfWeek(inputDate);

        for (Event event : events) {
            if (event.date.after(monday) && event.date.before(sunday)) {
                eventsForWeek.add(event);
            }
        }
        return eventsForWeek;
    }

    @Override
    public Event getEventInformation(int eventId) {
        return events.stream().filter(x -> x.id == eventId).findFirst().get();
    }

    @Override
    public int addEvent(String name,
                        String type,
                        Date date,
                        String description) {
        Event event = new Event(eventIdCounter, name, type, date, description);
        events.add(event);
        return eventIdCounter++;
    }

    @Override
    public void modifyEventInformation(int eventId,
                                       String name,
                                       String type,
                                       Date date,
                                       String description) {
        Event event = events.get(eventId);
        if (event != null) {
            event.name = name;
            event.type = type;
            event.date = date;
            event.description = description;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return events;
    }

    @Override
    public byte[] generatePDFReport(ArrayList<Event> events) {
        // Kod generujący raport PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        try (Document document = new Document(pdfDocument)) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            document.add(new Paragraph("Eventy Białostockie: "));
            for (int i = 0; i < events.size(); i++) {
                Event currentEvent = events.get(i);
                document.add(new Paragraph("Event nr: " + (i + 1)));
                document.add(new Paragraph("Nazwa: " + currentEvent.name));
                document.add(new Paragraph("Typ: " + currentEvent.type));
                document.add(new Paragraph("Data wydarzenia : " + formatter.format(currentEvent.date)));
                document.add(new Paragraph("Opis : " + currentEvent.description));
                document.add(new Paragraph("---------------------------------------------------------------------------------------"));
            }
        }
        return outputStream.toByteArray();
    }
}
