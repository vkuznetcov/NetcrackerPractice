
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.hotel.Hotel;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.OfficeBuilding;
import buildings.officeBuildings.OfficeFloor;

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
            Hotel fl = new Hotel(3, 2, 3, 4);
//            boolean check = it.hasNext();
            for(Floor cur : fl){
                System.out.println(cur.toString());
            }
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }
    }
}
