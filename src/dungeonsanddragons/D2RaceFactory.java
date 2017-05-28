/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Scanner;

/**
 *
 * @author JaneW
 */
public class D2RaceFactory {

    static private EnumMap<D2Race.D2Races, D2Race> mapRaces;

    public D2RaceFactory() {
        mapRaces = new EnumMap<D2Race.D2Races, D2Race>(D2Race.D2Races.class);
    }

    static public D2Race getRandom() {
        int iSelected = D2Dice.roll(1, mapRaces.size(), 0) - 1;
        return mapRaces.get(iSelected);
    }

    static public D2Race getD2Race(D2Race.D2Races enumNewRace) {
        return mapRaces.get(enumNewRace);
    }

    static public D2Race getD2Race(String sNewRaceName) {
        return mapRaces.get(D2Race.D2Races.fromString(sNewRaceName));

    }

    public void load() {
        System.out.println("Loading Races...");
        Scanner s = null;
        Path p1 = Paths.get(".", "D2Races.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;




            while ((line = reader.readLine()) != null) {

                System.out.println("Race data: " + line);

                s = new Scanner(line).useDelimiter(",");

                String sRaceName = s.next();
                D2Race.D2Races enumKey = D2Race.D2Races.fromString(sRaceName);


                D2Race newElement = new D2Race(sRaceName, // RACE NAME
                        s.next(), // SIZE
                        s.nextInt()); // SPEED

                newElement.print();
                mapRaces.put(enumKey, newElement);
            }
            reader.close();

        } catch (IOException x) {
            System.err.println("STOP:" + x);
        }
        s.close();

    }
}
