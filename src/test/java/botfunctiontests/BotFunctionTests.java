package java.botfunctiontests;

import com.nelisriebezos.broozercruiserbot.BotFunctions;
import com.nelisriebezos.broozercruiserbot.Exceptions.NegativeNumberException;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoChauffeurException;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test BotFunctionTests")
public class BotFunctionTests {
    Car car;
    Chauffeur niels;
    Chauffeur bruus;
    Chauffeur piep;
    Chauffeur floriaan;
    TankSession tankSession1;

    @BeforeEach
    public void initialize() {
        car = new Car();
        niels = new Chauffeur();
        bruus = new Chauffeur();
        piep = new Chauffeur();
        floriaan = new Chauffeur();
        tankSession1 = new TankSession();

        car.setTankSession(tankSession1);
        niels.setCar(car);
        niels.setName("niels");
        bruus.setCar(car);
        bruus.setName("bruus");
        piep.setCar(car);
        piep.setName("piep");
        floriaan.setCar(car);
        floriaan.setName("floriaan");
    }

        @Test
        public void addNegativeAmountOfKm() {
        NegativeNumberException thrown = Assertions.assertThrows(NegativeNumberException.class, () -> {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            chauffeurList.add(niels);
            BotFunctions.addKmPerPerson(chauffeurList, -1);
        });
        assertEquals(-1 + ", must be positive", thrown.getMessage());
        }

    @Test
    public void emptyChauffeurList() {
        NoChauffeurException thrown = Assertions.assertThrows(NoChauffeurException.class, () -> {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            BotFunctions.addKmPerPerson(chauffeurList, 1);
        });
        assertEquals("ChauffeurList is empty", thrown.getMessage());
    }

        @Test
        public void amountOfKmOnePerson() {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            chauffeurList.add(niels);
            BotFunctions.addKmPerPerson(chauffeurList, 10);
            assertTrue(niels.getAmountOfKm() == 10
                    && car.getMileage() == 10
                    && car.getSessionKmAmount() == 10);
        }

        @Test
        public void amountOfKmMultiplePeople() {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            chauffeurList.add(niels);
            chauffeurList.add(piep);
            BotFunctions.addKmPerPerson(chauffeurList, 10);
            assertTrue(niels.getAmountOfKm() == 5
                    && piep.getAmountOfKm() == 5
                    && car.getMileage() == 10
                    && car.getSessionKmAmount() == 10);
        }

        @Test
        public void calculateCosts() {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            chauffeurList.add(niels);
            chauffeurList.add(piep);
            chauffeurList.add(bruus);
            chauffeurList.add(floriaan);
            car.setMileage(0);
            BotFunctions.addKmPerPerson(chauffeurList, 50);
            assertEquals(BotFunctions.calculateCosts(chauffeurList, 80).toString(), "[niels: 20.0, piep: 20.0, bruus: 20.0, floriaan: 20.0]");
        }

    @Test
    public void calculateCostNegativeAmount() {
        NegativeNumberException thrown = Assertions.assertThrows(NegativeNumberException.class, () -> {
            List<Chauffeur> chauffeurList = new ArrayList<>();
            chauffeurList.add(niels);
            BotFunctions.calculateCosts(chauffeurList, -1);
        });
        assertEquals(-1 + ", must be positive", thrown.getMessage());
    }
}
