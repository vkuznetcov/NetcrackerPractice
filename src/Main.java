
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.OfficeBuilding;
import buildings.officeBuildings.OfficeFloor;
import buildings.threads.*;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Space sp1 = new Flat(150,1);
            Space sp2 = new Flat(300, 4);
            Space sp3 = new Flat(120, 2);

            Floor fl = new DwellingFloor(sp1, sp2, sp3);

            Semaphore semRepairer = new Semaphore(2, "rep");
            Semaphore semCleaner = new Semaphore(2, "cl");
            Thread cl = new Thread(new SequentialCleaner(fl, semCleaner));
            Thread rep = new Thread(new SequentialRepairer(fl, semCleaner));

            rep.start();
//            semCleaner.acquire();
            cl.start();


        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }
    }
}
