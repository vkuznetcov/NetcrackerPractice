
import lab3.officeBuildings.OfficeBuilding;
import lab3.officeBuildings.interfaces.Space;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            OfficeBuilding test = new OfficeBuilding(3, 2, 3, 4);;
            int a = test.getSpacesAmount();
            Space res = test.getBestSpace();
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }
    }
}
