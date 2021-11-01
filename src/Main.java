
import lab2.buildings.Flat;
import lab3.officeBuildings.Office;
import lab3.officeBuildings.OfficeBuilding;
import lab3.officeBuildings.interfaces.Building;
import lab3.officeBuildings.interfaces.Space;
import lab4.Buildings;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Flat test = new Flat(250, 1);
            int res = test.hashCode();
//            OfficeBuilding test = new OfficeBuilding(2, 2, 1);
//            OfficeBuilding test2 = (OfficeBuilding) test.clone();
//            System.out.println(test.equals(test2));


        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }

    }
}
