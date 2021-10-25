
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
            OfficeBuilding test = new OfficeBuilding(3, 2, 3, 4);;
            int a = test.getSpacesAmount();
            Space res = test.getBestSpace();
            Buildings.writeBuilding(test, new FileWriter("src\\lab4\\info.txt"));
            //Buildings.outputBuilding(test, new FileOutputStream("src\\lab4\\info.txt"));
            //Buildings.serializeBuilding(test, new FileOutputStream("src\\lab4\\info.txt"));
            //Building test2 = Buildings.deserializeBuilding(new FileInputStream("src\\lab4\\info.txt"));
            Building test2 = Buildings.readBuilding(new Scanner(new FileInputStream("src\\lab4\\info.txt")));
            //Building test2 = Buildings.readBuilding(new Scanner(System.in));

            //Building test2 = Buildings.inputBuilding(new FileInputStream("src\\lab4\\info.txt"));
            System.out.println();
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exc){
            System.out.println(exc.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
