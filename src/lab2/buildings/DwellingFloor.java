package lab2.buildings;

public class DwellingFloor
{
    private Flat[] floor;

    public void setFloor(Flat[] floorValue)
    {
        floor = new Flat[floorValue.length];
        for (int i = 0; i < floorValue.length; ++i)
        {
            floor[i] = new Flat(floorValue[i].getSquare(), floorValue[i].getRooms());
        }

    }

    public DwellingFloor(int flatsQuantity)
    {
        floor = new Flat[flatsQuantity];
        for (int i = 0; i < flatsQuantity; ++i)
        {
            floor[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flatsArray)
    {
        floor = new Flat[flatsArray.length];
        for (int i = 0; i < flatsArray.length; ++i)
        {
            floor[i] = new Flat(flatsArray[i].getSquare(), flatsArray[i].getRooms());
        }
    }

    public int getFlats()
    {
        return floor.length;
    }

    public double getSquare()
    {
        double sumSquare = 0;
        for (Flat current : floor)
        {
            sumSquare += current.getSquare();
        }
        return sumSquare;
    }

    public int getRooms()
    {
        int sumRooms = 0;
        for (Flat current : floor)
        {
            sumRooms += current.getRooms();
        }
        return sumRooms;
    }

    public Flat[] getFloor()
    {
        Flat[] forReturn = new Flat[floor.length];
        for (int i = 0; i < floor.length; ++i)
        {
            forReturn[i] = new Flat(floor[i].getSquare(), floor[i].getRooms());
        }
        return forReturn;
    }

    public Flat getFlat(int flatIndex)
    {
        //Flat forReturn = new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRoom_quantity());
        //return forReturn;
        return new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRooms());
    }

    public void changeFlat(int flatNum, Flat newFlat)
    {
        if (flatNum >= floor.length)
        {
            System.out.println("ERROR: invalid number");
            return;
        }
        floor[flatNum] = new Flat(newFlat.getSquare(), newFlat.getRooms());
    }

    public void addFlat(int flatNum, Flat newFlat)
    {
        if (flatNum > floor.length + 1)
        {
            System.out.println("ERROR: there are less flats that needed");
            return;
        }
        Flat[] newFloor = new Flat[floor.length + 1];
        for (int i = 0; i < flatNum + 1; ++i)
        {

            if (i == flatNum)
            {
                newFloor[i] = newFlat;
                break;
            }
            newFloor[i] = floor[i];
        }
        for (int i = flatNum + 1; i < newFloor.length; ++i)
        {
            newFloor[i] = floor[i - 1];
        }
        floor = newFloor;
    }

    public void deleteFlat(int flatNum)
    {
        if (flatNum > floor.length - 1)
        {
            System.out.println("ERROR: invalid number");
            return;
        }
        Flat[] newFloor = new Flat[floor.length - 1];
        for (int i = 0; i < flatNum; ++i)
        {
            newFloor[i] = floor[i];
        }
        for (int i = flatNum; i < newFloor.length; ++i)
        {
            newFloor[i] = floor[i + 1];
        }
        floor = newFloor;
    }

    public Flat getBestSpace()
    {
        int indexOfMax = 0;
        double maxSquare = floor[0].getSquare();
        for (int i = 1; i < floor.length; ++i)
        {
            if (floor[i].getSquare() > maxSquare)
            {
                maxSquare = floor[i].getSquare();
                indexOfMax = i;
            }
        }
        return new Flat(floor[indexOfMax].getSquare(), floor[indexOfMax].getRooms());
    }

    public void print()
    {
        System.out.println("dwellingfloor: ");
        for (Flat cur : floor)
        {
            cur.Print();
        }
    }
}
