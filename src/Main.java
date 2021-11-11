
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.Office;
import buildings.officeBuildings.OfficeFloor;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            HotelFloor[] hlf = new HotelFloor[5];
            for(int i = 0; i < hlf.length; i++){
                hlf[i] = new HotelFloor(5);
            }
            Hotel hl = new Hotel(hlf);
            Hotel hl2 = new Hotel(new HotelFloor(5));

            System.out.println(hl.equals(hl2));
            System.out.println(hl.toString());
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }

    }
}
