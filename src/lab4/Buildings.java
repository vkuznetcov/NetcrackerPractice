package lab4;

import buildings.criterions.FloorSpacesAmountComparator;
import buildings.criterions.SpaceRoomsAmountComparator;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.Office;
import buildings.officeBuildings.OfficeFloor;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@SuppressWarnings({"unused"})
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

    public static Building inputBuilding(InputStream in)
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

    public static <B extends Building, F extends Floor, S extends Space>
    Building inputBuilding(InputStream in, Class<B> buildingClass,
                                         Class<F> floorCLass, Class<S> spaceClass)
    {
        DataInputStream inputStream = new DataInputStream(in);
        try
        {
            int size = inputStream.readInt();
            Floor[] build = new Floor[size];
            for (int i = 0; i < build.length; i++)
            {
                build[i] = createFloor(inputStream.readInt(), floorCLass);
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    int rooms = inputStream.readInt();
                    double square = inputStream.readDouble();
                    build[i].setSpace(j, createSpace(square, rooms, spaceClass));
                }
            }
            return createBuilding(build, buildingClass);
        }
        catch (IOException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws IOException
    {
        try(Reader input = new FileReader(
                "D:\\code project (.java)\\NetcrackerPractice\\info.txt"))
        {
            System.out.println(readBuilding(input, Dwelling.class, DwellingFloor.class, Office.class));
        }
    }

    public static void writeBuilding(Building building, Writer out)
    {
        try
        {
            out.write(building.getFloorsAmount() + " ");

            for (int i = 0; i < building.getFloorsAmount(); i++)
            {
                out.write(building.getFloor(i).getFloorSize() + " ");
                for (int j = 0; j < building.getFloor(i).getFloorSize(); j++)
                {
                    out.write(building.getFloor(i).getSpace(j).getRoomsAmount() + " ");
                    out.write(building.getFloor(i).getSpace(j).getSquare() + " ");
                }
            }
            out.flush();
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

    public static <B extends Building, F extends Floor, S extends Space>Building readBuilding(Reader in,
                                                                                              Class<B> buildingClass,
                                                                                              Class<F> floorClass,
                                                                                              Class<S> spaceClass)
    {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        try
        {
            tokenizer.nextToken();
            Floor[] build = new Floor[(int) tokenizer.nval];
            for (int i = 0; i < build.length; i++)
            {
                tokenizer.nextToken();
                build[i] = createFloor((int) tokenizer.nval, floorClass);
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    tokenizer.nextToken();
                    int rooms = (int) tokenizer.nval;
                    tokenizer.nextToken();
                    double square = tokenizer.nval;
                    build[i].setSpace(j, createSpace(square, rooms, spaceClass));
                }
            }
            return createBuilding(build, buildingClass);
        }
        catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        try
        {
            outputStream.writeObject(building);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building deserializeBuilding(InputStream in) throws IOException {
        ObjectInputStream inputStream = new ObjectInputStream(in);
        try{
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
        try (Formatter formatter = new Formatter(out))
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

    public static <B extends Building, F extends Floor, S extends Space>
    Building readBuilding(Scanner scanner, Class<B> buildingClass, Class<F> floorClass, Class<S> spaceClass)
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
                build[i] = createFloor(scanner.nextInt(), floorClass);
                for (int j = 0; j < build[i].getFloorSize(); j++)
                {
                    int rooms = scanner.nextInt();
                    double square = scanner.nextDouble();
                    build[i].setSpace(j, createSpace(square, rooms, spaceClass));
                }
            }
            return createBuilding(build, buildingClass);
        }
        catch (IllegalArgumentException | InputMismatchException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
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

    public <T> void updateService(T value){

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

    public static <T extends Space> Space createSpace(double area, Class<T> spaceClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return spaceClass.getConstructor(double.class).newInstance(area);
    }

    public static <T extends Space> Space createSpace(double area, int roomsCount, Class<T> spaceClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return spaceClass.getConstructor(double.class, int.class).newInstance(area, roomsCount);
    }

    public static <T extends Floor> Floor createFloor(int spaceCount, Class<T> floorClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return floorClass.getConstructor(int.class).newInstance(spaceCount);
    }

    public static <T extends Floor> Floor createFloor(Space[] spaces, Class<T> floorClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return floorClass.getConstructor(spaces.getClass()).newInstance((Object) spaces);
    }

    public static <T extends Building> Building createBuilding(int floorsCount, int[] spacesCounts, Class<T> buildingClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return buildingClass.getConstructor(int.class, int[].class).newInstance(floorsCount, spacesCounts);
    }

    public static <T extends Building> Building createBuilding(Floor[] floors, Class<T> buildingCLass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return buildingCLass.getConstructor(floors.getClass()).newInstance((Object) floors);
    }

    public static Floor synchronizedFloor(Floor floor){
        return new SynchronizedFloor(floor);
    }
}
