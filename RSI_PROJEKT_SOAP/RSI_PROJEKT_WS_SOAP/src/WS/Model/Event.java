package WS.Model;

import java.util.Date;

public class Event {
    public int id;
    public String name;
    public String type;
    public Date date;
    public String description;

    public Event() { }
    public Event(int id, String name, String type, Date date, String description) {
        this.id = id; // Assigning negative value until added to the map
        this.name = name;
        this.type = type;
        this.date = date;
        this.description = description;
    }
}