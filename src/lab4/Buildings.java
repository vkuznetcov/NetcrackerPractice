package lab4;

import buildings.criterions.FloorSpacesAmountComparator;
import buildings.criterions.SpaceRoomsAmountComparator;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.Office;
import buildings.officeBuildings.OfficeBuilding;
import buildings.officeBuildings.OfficeFloor;

import java.io.*;
import java.util.*;

public class Buildings
{
    static BuildingFactory builder = new DwellingFactory();

    public static void outputBuilding(Building building, OutputStream out) throws IOException
    {
        DataOutputStream outputStream = new DataOutputStream(out);
        try
        {
            outputStream.writeInt(building.getFloorsAmount());

            for (int i = 0; i < building.getFloorsAmount(); i++)
            {
                outputStream.writeInt(building.getFloor(i).getFloorSize());
                for (int j = 0; j < building.getFloor(i).getFloorSize(); j++)
                {
                    outputStream.writeInt(building.getFloor(i).getSpace(j).getRoomsAmount());
                    outputStream.writeDouble(building.getFloor(i).getSpace(j).getSquare());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException
    {
        DataInputStream inputStream = new DataInputStream(in);
        try
        {
            Floor[] build = new Floor[inputStream.readInt()];
            for (int i = 0; i < build.length; i++)
            {
                build[i] = createFloor(inputStream.readInt());
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    int rooms = inputStream.readInt();
                    double square = inputStream.readDouble();
                    build[i].setSpace(j, createSpace(square, rooms));
                }
            }
            return createBuilding(build);
        }
        catch (IOException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(out);
        try
        {

            writer.write(building.getFloorsAmount() + " ");

            for (int i = 0; i < building.getFloorsAmount(); i++)
            {
                writer.write(building.getFloor(i).getFloorSize() + " ");
                for (int j = 0; j < building.getFloor(i).getFloorSize(); j++)
                {
                    writer.write(building.getFloor(i).getSpace(j).getRoomsAmount() + " ");
                    writer.write(building.getFloor(i).getSpace(j).getSquare() + " ");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static Building readBuilding(Reader in)
    {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        try
        {
            tokenizer.nextToken();
            Floor[] build = new Floor[(int) tokenizer.nval];
            for (int i = 0; i < build.length; i++)
            {
                tokenizer.nextToken();
                build[i] = createFloor((int) tokenizer.nval);
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    tokenizer.nextToken();
                    int rooms = (int) tokenizer.nval;
                    tokenizer.nextToken();
                    double square = tokenizer.nval;
                    build[i].setSpace(j, createSpace(square, rooms));
                }
            }
            return createBuilding(build);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(out);)
        {
            outputStream.writeObject(building);
        }
    }

    public static Building deserializeBuilding(InputStream in)
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(in);)
        {
            return (Building) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeBuildingFormat(Building building, Writer out)
    {
        Formatter formatter = new Formatter(out);
        try
        {
            formatter.format("Building floors amount: %d\n\n", building.getFloorsAmount());

            for (int i = 0; i < building.getFloorsAmount(); i++)
            {
                formatter.format("Floor number %d:\n", i);
                for (int j = 0; j < building.getFloor(i).getFloorSize(); j++)
                {
                    formatter.format("Space number %d:\n", j);
                    formatter.format("Rooms amount: %d\n", building.getFloor(i).getSpace(j).getRoomsAmount());
                    formatter.format("Square amount: %.2f\n\n", building.getFloor(i).getSpace(j).getSquare());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            formatter.close();
        }
    }

    public static Building readBuilding(Scanner scanner)
    {
        try
        {
            scanner.useLocale(Locale.US);
            Floor[] build;
            if (scanner.hasNext())
            {
                build = new Floor[(int) scanner.nextInt()];
            }
            else
            {
                return null;
            }
            for (int i = 0; i < build.length; i++)
            {
                build[i] = createFloor(scanner.nextInt());
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    int rooms = scanner.nextInt();
                    double square = scanner.nextDouble();
                    build[i].setSpace(j, createSpace(square, rooms));
                }
            }
            return createBuilding(build);
        }
        catch (IllegalArgumentException | InputMismatchException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void sortSpaces(Space[] forSort){
        for(int i = 0; i < forSort.length; i++) {
            for (int j = 0; j < forSort.length - i - 1; j++) {
                Space tmp;
                if(forSort[j].compareTo(forSort[j+1]) > 0){
                    tmp = forSort[j];
                    forSort[j] = forSort[j + 1];
                    forSort[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortFloors(Floor[] forSort){
        for(int i = 0; i < forSort.length; i++) {
            for (int j = 0; j < forSort.length - i - 1; j++) {
                Floor tmp;
                if(forSort[j].compareTo(forSort[j+1]) > 0){
                    tmp = forSort[j];
                    forSort[j] = forSort[j + 1];
                    forSort[j + 1] = tmp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sortArrays(T[] array){
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                T tmp;
                if(array[j].compareTo(array[j+1]) > 0){
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortSpacesByCriterion(Space[] forSort){
        SpaceRoomsAmountComparator criterion = new SpaceRoomsAmountComparator();
        for(int i = 0; i < forSort.length; i++) {
            for (int j = 0; j < forSort.length - i - 1; j++) {
                Space tmp;
                if(criterion.compare(forSort[j], forSort[j + 1]) > 0){
                    tmp = forSort[j];
                    forSort[j] = forSort[j + 1];
                    forSort[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortFloorsByCriterion(Floor[] forSort){
        FloorSpacesAmountComparator criterion = new FloorSpacesAmountComparator();
        for(int i = 0; i < forSort.length; i++) {
            for (int j = 0; j < forSort.length - i - 1; j++) {
                Floor tmp;
                if(criterion.compare(forSort[j], forSort[j + 1]) > 0){
                    tmp = forSort[j];
                    forSort[j] = forSort[j + 1];
                    forSort[j + 1] = tmp;
                }
            }
        }
    }

    public static <T> void sortArraysByCriterion(T[] forSort, Comparator<T> criterion){
        for(int i = 0; i < forSort.length; i++) {
            for (int j = 0; j < forSort.length - i - 1; j++) {
                T tmp;
                if(criterion.compare(forSort[j], forSort[j + 1]) > 0){
                    tmp = forSort[j];
                    forSort[j] = forSort[j + 1];
                    forSort[j + 1] = tmp;
                }
            }
        }
    }

    public static void setBuildingFactory(BuildingFactory newValue){
        builder = newValue;
    }

    public static Space createSpace(double area){
        return builder.createSpace(area);
    }

    public static Space createSpace(double area, int roomsCount){
        return builder.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spaceCount){
        return builder.createFloor(spaceCount);
    }

    public static Floor createFloor(Space[] spaces){
        return builder.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts){
        return builder.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors){
        return builder.createBuilding(floors);
    }
}
