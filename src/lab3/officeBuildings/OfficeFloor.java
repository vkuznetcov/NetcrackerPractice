package lab3.officeBuildings;

import lab3.officeBuildings.exceptions.SpaceIndexOutOfBoundsException;
import lab3.officeBuildings.interfaces.Floor;
import lab3.officeBuildings.interfaces.Space;
import lab3.officeBuildings.lists.officeList.ArrList;
import lab3.officeBuildings.lists.officeList.ArrListNode;

public class OfficeFloor implements Floor
{
    ArrList floor;

    private ArrListNode getNode(int num)
    {
        return floor.getByNum(num);
    }

    private void addNode(int num, ArrListNode newNode)
    {
        floor.addByNum(num, newNode.data);
    }

    private void deleteNode(int num)
    {
        floor.deleteByNum(num);
    }

    public OfficeFloor()
    {
        floor = new ArrList();
    }

    public OfficeFloor(int officeQuantity)
    {
        floor = new ArrList(officeQuantity);
    }

    public OfficeFloor(Space... officeArray)
    {
        floor = new ArrList(new ArrListNode(officeArray[0]));
        for (int i = 1; i < officeArray.length; ++i)
        {
            floor.pushBack(officeArray[i]);
        }
    }

    public int getFloorSize()
    {
        return floor.length();
    }

    public double getSquareAmount()
    {
        double square = 0;
        for (int i = 0; i < floor.length(); ++i)
        {
            square += floor.getByNum(i).data.getSquare();
        }
        return square;
    }

    public int getRoomsAmount()
    {
        int rooms = 0;
        for (int i = 0; i < floor.length(); ++i)
        {
            rooms += floor.getByNum(i).data.getRoomsAmount();
        }
        return rooms;
    }

    public Space[] getFloor()
    {
        Space forReturn[] = new Space[floor.length()];

        for (int i = 0; i < forReturn.length; ++i)
        {
            forReturn[i] = new Office(floor.getByNum(i).data.getRoomsAmount(), floor.getByNum(i).data.getSquare());
        }

        return forReturn;
    }

    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException
    {
        if(num >= floor.length())
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        return new Office(floor.getByNum(num).data.getRoomsAmount(), floor.getByNum(num).data.getSquare());
    }

    public void changeSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if(num >= floor.length())
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        floor.changeByNum(num, newSpace);
    }

    public void addSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if(num > floor.length())
            throw new SpaceIndexOutOfBoundsException(num, floor.length());
        this.addNode(num, new ArrListNode(newSpace));
        //floor.addByNum(num, newSpace);
    }

    public void deleteSpace(int num) throws SpaceIndexOutOfBoundsException
    {
        if(num >= floor.length())
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        floor.deleteByNum(num);
    }

    public Space getBestSpace()
    {
        Space forReturn = new Office(floor.getByNum(0).data.getRoomsAmount(), floor.getByNum(0).data.getSquare());
        for (int i = 1; i < floor.length(); ++i)
        {
            if (forReturn.getSquare() < floor.getByNum(i).data.getSquare())
            {
                forReturn.setRoomsAmount(floor.getByNum(i).data.getRoomsAmount());
                forReturn.setSquare(floor.getByNum(i).data.getSquare());
            }
        }
        return forReturn;
    }
}
