package lab2.buildings;

import lab3.officeBuildings.exceptions.SpaceIndexOutOfBoundsException;
import lab3.officeBuildings.interfaces.Floor;
import lab3.officeBuildings.interfaces.Space;

public class DwellingFloor implements Floor
{
    private Space[] floor;

    public void setFloor(Space... floorValue)
    {
        floor = new Space[floorValue.length];
        for (int i = 0; i < floorValue.length; ++i)
        {
            floor[i] = new Flat(floorValue[i].getSquare(), floorValue[i].getRoomsAmount());
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

    public DwellingFloor(Space... flatsArray)
    {
        floor = new Flat[flatsArray.length];
        for (int i = 0; i < flatsArray.length; ++i)
        {
            floor[i] = new Flat(flatsArray[i].getSquare(), flatsArray[i].getRoomsAmount());
        }
    }

    public int getFloorSize()
    {
        return floor.length;
    }

    public double getSquareAmount()
    {
        double sumSquare = 0;
        for (Space current : floor)
        {
            sumSquare += current.getSquare();
        }
        return sumSquare;
    }

    public int getRoomsAmount()
    {
        int sumRooms = 0;
        for (Space current : floor)
        {
            sumRooms += current.getRoomsAmount();
        }
        return sumRooms;
    }

    public Flat[] getFloor()
    {
        Flat[] forReturn = new Flat[floor.length];
        for (int i = 0; i < floor.length; ++i)
        {
            forReturn[i] = new Flat(floor[i].getSquare(), floor[i].getRoomsAmount());
        }
        return forReturn;
    }

    public Flat getSpace(int flatIndex) throws SpaceIndexOutOfBoundsException
    {
        if(flatIndex >= floor.length)
            throw new SpaceIndexOutOfBoundsException(flatIndex, floor.length - 1);
        //Flat forReturn = new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRoom_quantity());
        //return forReturn;
        return new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRoomsAmount());
    }


    public void changeSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if(num >= floor.length)
            throw new SpaceIndexOutOfBoundsException(num, floor.length - 1);

        floor[num] = new Flat(newSpace.getSquare(), newSpace.getRoomsAmount());
    }

    public void addSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if(num > floor.length)
            throw new SpaceIndexOutOfBoundsException(num, floor.length);

        Space[] newFloor = new Space[floor.length + 1];
        for (int i = 0; i < num + 1; ++i)
        {

            if (i == num)
            {
                newFloor[i] = new Flat(newSpace.getSquare(), newSpace.getRoomsAmount());
                break;
            }
            newFloor[i] = floor[i];
        }
        for (int i = num + 1; i < newFloor.length; ++i)
        {
            newFloor[i] = floor[i - 1];
        }
        floor = newFloor;
    }

    public void deleteSpace(int flatNum) throws SpaceIndexOutOfBoundsException
    {
        if(flatNum >= floor.length)
            throw new SpaceIndexOutOfBoundsException(flatNum, floor.length - 1);


        Space[] newFloor = new Flat[floor.length - 1];
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

    public Space getBestSpace()
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
        return new Flat(floor[indexOfMax].getSquare(), floor[indexOfMax].getRoomsAmount());
    }
}
