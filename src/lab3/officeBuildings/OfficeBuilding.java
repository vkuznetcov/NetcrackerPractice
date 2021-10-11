package lab3.officeBuildings;

import lab2.buildings.Dwelling;
import lab3.officeBuildings.exceptions.FloorIndexOutOfBoundsException;
import lab3.officeBuildings.exceptions.InvalidSpacesNumberException;
import lab3.officeBuildings.exceptions.SpaceIndexOutOfBoundsException;
import lab3.officeBuildings.interfaces.Building;
import lab3.officeBuildings.interfaces.Floor;
import lab3.officeBuildings.interfaces.Space;
import lab3.officeBuildings.lists.officeFloorList.LinkList;
import lab3.officeBuildings.lists.officeFloorList.LinkListNode;

public class OfficeBuilding implements Building
{
    LinkList floors;

    public OfficeBuilding(int floorsSize, int... officesSize) throws InvalidSpacesNumberException
    {
        if (officesSize.length != floorsSize)
        {
            throw new InvalidSpacesNumberException(officesSize.length, floorsSize);
        }
        floors = new LinkList(floorsSize);
        for (int i = 0; i < officesSize.length; ++i)
        {
            floors.changeByNum(i, new OfficeFloor(officesSize[i]));
        }

    }

    private LinkListNode getNode(int num)
    {
        return floors.getByNum(num);
    }

    private void addNode(int num, LinkListNode newNode)
    {
        floors.addByNum(num, newNode.data);
    }

    private void deleteNode(int num)
    {
        floors.deleteByNum(num);
    }

    public int getFloorsAmount()
    {
        return floors.length();
    }

    public int getSpacesAmount()
    {
        int officesAmount = 0;

        for (int i = 0; i < floors.length(); ++i)
        {
            officesAmount += floors.getByNum(i).data.getFloorSize();
        }

        return officesAmount;
    }

    public double getSquareAmount()
    {
        double squareAmount = 0;

        for (int i = 0; i < floors.length(); ++i)
        {
            squareAmount += floors.getByNum(i).data.getSquareAmount();
        }

        return squareAmount;
    }

    public int getRoomsAmount()
    {
        int roomsAmount = 0;

        for (int i = 0; i < floors.length(); ++i)
        {
            roomsAmount += floors.getByNum(i).data.getRoomsAmount();
        }

        return roomsAmount;
    }

    public Floor[] getFloors()
    {
        Floor[] forReturn = new Floor[floors.length()];

        for (int i = 0; i < forReturn.length; i++)
        {
            forReturn[i] = new OfficeFloor(floors.getByNum(i).data.getFloor());
        }

        return forReturn;
    }

    public Floor getFloor(int num) throws FloorIndexOutOfBoundsException
    {
        if (num >= floors.length())
        {
            throw new FloorIndexOutOfBoundsException(num, floors.length() - 1);
        }

        return new OfficeFloor(floors.getByNum(num).data.getFloor());
    }

    public void changeFloor(int num, Floor newFloor) throws FloorIndexOutOfBoundsException
    {
        if (num >= floors.length())
        {
            throw new FloorIndexOutOfBoundsException(num, floors.length() - 1);
        }

        floors.changeByNum(num, newFloor);
    }

    public Space getSpace(int officeNumber) throws SpaceIndexOutOfBoundsException
    {
        if (officeNumber >= this.getSpacesAmount())
        {
            throw new SpaceIndexOutOfBoundsException(officeNumber, this.getSpacesAmount() - 1);
        }

        int floorNumber = 0;
        for (floorNumber = 0; floorNumber < floors.length() &&
                officeNumber > floors.getByNum(floorNumber).data.getFloorSize(); ++floorNumber)
        {
            officeNumber -= floors.getByNum(floorNumber).data.getFloorSize();
        }

        return new Office(floors.getByNum(floorNumber).data.getSpace(floorNumber));
    }

    public void changeSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if (num >= this.getSpacesAmount())
        {
            throw new SpaceIndexOutOfBoundsException(num, this.getSpacesAmount() - 1);
        }

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length() && num > floors.getByNum(floorNumber).data.getFloorSize(); ++floorNumber)
        {
            num -= floors.getByNum(floorNumber).data.getFloorSize();
        }

        floors.getByNum(floorNumber).data.changeSpace(num, newSpace);
    }

    public void addSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if (num > this.getSpacesAmount())
        {
            throw new SpaceIndexOutOfBoundsException(num, this.getSpacesAmount());
        }

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length() && num > floors.getByNum(floorNumber).data.getFloorSize(); ++floorNumber)
        {
            num -= floors.getByNum(floorNumber).data.getFloorSize();
        }

        floors.getByNum(floorNumber).data.addSpace(num, newSpace);
    }

    public void deleteSpace(int num) throws SpaceIndexOutOfBoundsException
    {
        if (num >= this.getSpacesAmount())
        {
            throw new SpaceIndexOutOfBoundsException(num, this.getSpacesAmount() - 1);
        }

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length() && num > floors.getByNum(floorNumber).data.getFloorSize(); ++floorNumber)
        {
            num -= floors.getByNum(floorNumber).data.getFloorSize();
        }

        floors.getByNum(floorNumber).data.deleteSpace(num);
    }

    public Space getBestSpace()
    {
        Space forReturn = new Office(floors.getByNum(0).data.getBestSpace());

        for (int i = 1; i < floors.length(); i++)
        {
            if (forReturn.getSquare() < floors.getByNum(i).data.getBestSpace().getSquare())
            {
                forReturn = floors.getByNum(i).data.getBestSpace();
            }
        }

        return forReturn;
    }

    public Space[] getSorted()
    {
        Space[] forReturn = new Space[this.getSpacesAmount()];
        {
            int flatIndex = 0;
            for (int i = 0; i < floors.length(); ++i)
            {
                for (int j = 0; j < floors.getByNum(i).data.getFloorSize(); ++j)
                {
                    forReturn[flatIndex] = floors.getByNum(i).data.getSpace(j);
                    flatIndex++;
                }
            }
        }
        Dwelling.sort(forReturn);
        return forReturn;
    }
}
