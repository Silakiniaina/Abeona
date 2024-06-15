
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Benefice.Benefice;
import model.Categorie.Categorie_transport;
import model.Client.Client;
import model.Evenement.Evenement;
import model.Lieu.Ville;
import model.Partenaire.Guide;
import model.Partenaire.Hotel;
import model.Partenaire.Partenaire;
import model.Partenaire.Transport;

public class Test {
    public static void main(String[] args) {
        // Timestamp time = new Timestamp(1,2,3,4,5,6,7);
        // Evenement categ = new Evenement("EVT11","uhuygctyqvc<bu","Mada",time,"","HOT1","VIL1","CEV2");
        Hotel evenement = new Hotel();
        try {
            String timestampString = "2023-06-11 10:15:30.0";
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            float b = 1;
        // Convertir la chaîne en Timestamp
            Timestamp parsedTimestamp = Timestamp.valueOf(timestampString);
           ArrayList<Hotel> ben = Hotel.getListHotelByCriteria("moyen","VIL2",b,parsedTimestamp,currentTimestamp);
           for (Hotel hotel : ben) {
                System.out.println(hotel.getId_hotel());
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
