package Server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
        public Product(String nazwa, String opis, int cena) {
                this.nazwa = nazwa;
                this.opis = opis;
                this.cena = cena;
        }
        private String nazwa;
        private String opis;
        private int cena;
}
