import domain.Chauffeur;
import domain.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BotFunctions{
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);


    public void addKmPerPerson(List<Chauffeur> chauffeurList, double amountOfKm, Tank tank) {
        tank.addAmountOfKm(amountOfKm);
        double amountPerPerson = amountOfKm / chauffeurList.size();
        for (Chauffeur chauffeur : chauffeurList) {
            chauffeur.addAmountOfKm(amountPerPerson);
            LOG.info(chauffeur.getName() + " " + amountPerPerson + " km added");
        }
    }

    public void createNewTank(List<Chauffeur> chauffeurList) {
        Tank tank = new Tank();
        for (Chauffeur chauffeur : chauffeurList) {
            chauffeur.setTank(tank);
        }
        LOG.info("New tank set");
    }
}
