package lab2.buildings;

import lab3.officeBuildings.exceptions.FloorIndexOutOfBoundsException;
import lab3.officeBuildings.exceptions.SpaceIndexOutOfBoundsException;
import lab3.officeBuildings.interfaces.Building;
import lab3.officeBuildings.interfaces.Floor;
import lab3.officeBuildings.interfaces.Space;

import java.io.Serializable;

public class Dwelling implements Building, Serializable
{
    private Floor[] floors;

    public Dwelling()
    {
        floors = new Floor[4];
        for (int i = 0; i < 4; ++i)
        {
            floors[i] = new DwellingFloor(10);
        }
    }

    public Dwelling(int floorsValue, int... flatsValue)
    {
        if (flatsValue.length < floorsValue)
        {
            System.out.println("ERROR: invalid flats size");
            return;
        }
        floors = new Floor[floorsValue];
        for (int i = 0; i < floors.length; ++i)
        {
            floors[i] = new DwellingFloor(flatsValue[i]);
        }
    }

    public Dwelling(Floor... newFloors)
    {
        floors = new Floor[newFloors.length];
        for (int i = 0; i < floors.length; ++i)
        {
            floors[i] = new DwellingFloor(newFloors[i].getFloor());
        }
    }

    public int getFloorsAmount()
    {
        return floors.length;
    }

    public int getSpacesAmount()
    {
        int flatsAmount = 0;
        for (Floor current : floors)
        {
            flatsAmount += current.getFloorSize();
        }
        return flatsAmount;
    }

    public double getSquareAmount()
    {
        double squareAmount = 0;
        for (Floor current : floors)
        {
            squareAmount += current.getSquareAmount();
        }

        return squareAmount;
    }

    public int getRoomsAmount()
    {
        int roomsAmount = 0;
        for (Floor current : floors)
        {
            roomsAmount += current.getRoomsAmount();
        }

        return roomsAmount;
    }

    public Floor[] getFloors()
    {
        Floor[] forReturn = new Floor[floors.length];
        for (int i = 0; i < floors.length; ++i)
        {
            forReturn[i] = new DwellingFloor(floors[i].getFloor());
        }
        return forReturn;
    }

    public Floor getFloor(int floorNumber) throws FloorIndexOutOfBoundsException
    {
        if(floorNumber >= floors.length)
            throw new FloorIndexOutOfBoundsException(floorNumber, floors.length - 1);
        return new DwellingFloor(floors[floorNumber].getFloor());
    }

    public void setFloor(int floorNumber, Floor newFloor) throws FloorIndexOutOfBoundsException
    {
        if(floorNumber >= floors.length)
            throw new FloorIndexOutOfBoundsException(floorNumber, floors.length - 1);
        floors[floorNumber] = new DwellingFloor(newFloor.getFloor());
    }

    public Flat getSpace(int flatNumber) throws SpaceIndexOutOfBoundsException
    {
        if(flatNumber >= this.getSpacesAmount())
            throw new SpaceIndexOutOfBoundsException(flatNumber, this.getSpacesAmount() - 1);


        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length && flatNumber > floors[floorNumber].getFloorSize(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFloorSize();
        }
        return new Flat(floors[floorNumber].getSpace(flatNumber).getSquare(),
                        floors[floorNumber].getSpace(flatNumber).getRoomsAmount());
    }

    public void setSpace(int flatNumber, Space newFlat) throws SpaceIndexOutOfBoundsException
    {
        if(flatNumber >= this.getSpacesAmount())
            throw new SpaceIndexOutOfBoundsException(flatNumber, this.getSpacesAmount() - 1);

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length && flatNumber > floors[floorNumber].getFloorSize(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFloorSize();
        }

        floors[floorNumber].setSpace(flatNumber, newFlat);
    }

    public void addSpace(int flatNumber, Space newFlat) throws SpaceIndexOutOfBoundsException
    {
        if(flatNumber > this.getSpacesAmount())
            throw new SpaceIndexOutOfBoundsException(flatNumber, this.getSpacesAmount());

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length && flatNumber > floors[floorNumber].getFloorSize(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFloorSize();
        }

        floors[floorNumber].addSpace(flatNumber, newFlat);
    }

    public void deleteSpace(int flatNumber) throws SpaceIndexOutOfBoundsException
    {
        if(flatNumber >= this.getSpacesAmount())
            throw new SpaceIndexOutOfBoundsException(flatNumber, this.getSpacesAmount() - 1);
        if (flatNumber > this.getSpacesAmount())
        {
            //throw exception
            System.out.println("ERROR: invalid flat number");
            return;
        }

        int floorNumber = 0;
        for (floorNumber = 0;
             floorNumber < floors.length && flatNumber > floors[floorNumber].getFloorSize(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFloorSize();
        }

        floors[floorNumber].deleteSpace(flatNumber);
    }

    public Space getBestSpace()
    {
        Space maxSquare = floors[0].getBestSpace();
        for (int i = 1; i < floors.length; ++i)
        {
            if (floors[i].getBestSpace().getSquare() > maxSquare.getSquare())
            {
                maxSquare = floors[i].getBestSpace();
            }
        }
        return maxSquare;
    }

    public Space[] getSorted()
    {
        Space[] forReturn = new Space[this.getSpacesAmount()];
        {
            int flatIndex = 0;
            for (int i = 0; i < floors.length; ++i)
            {
                for (int j = 0; j < floors[i].getFloorSize(); ++j)
                {
                    forReturn[flatIndex] = floors[i].getSpace(j);
                    flatIndex++;
                }
            }
        }
        sort(forReturn);
        return forReturn;
    }

    public static void sort(Space... flatArray)
    {
        for (int i = 0; i < flatArray.length; ++i)
        {
            for (int j = 0; j < flatArray.length - 1; ++j)
            {
                if (flatArray[j].getSquare() < flatArray[j + 1].getSquare())
                {
                    Space tmp = flatArray[j];
                    flatArray[j] = flatArray[j + 1];
                    flatArray[j + 1] = tmp;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuffer forreturn = new StringBuffer("Dwelling(");
        for (int i = 0; i < getFloorsAmount(); i++)
        {
            if (i == getFloorsAmount() - 1)
            {
                forreturn.append(getFloor(i).toString());
                break;
            }
            forreturn.append(getFloor(i).toString() + ", ");
        }
        forreturn.append(")");
        return forreturn.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Dwelling){
            if(getFloorsAmount()!=((Dwelling) object).getFloorsAmount())
                return false;
            for(int i = 0; i < getFloorsAmount(); i++){
                if(!getFloor(i).equals(((Dwelling) object).getFloor(i)))
                    return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public Object clone()
    {
        Dwelling forreturn = new Dwelling(getFloors());
        for(int i = 0; i < forreturn.getFloorsAmount(); i++){
            forreturn.setFloor(i, (Floor)getFloor(i).clone());
        }
        return forreturn;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorsAmount(); ++i){
            result += getFloorsAmount() ^ getFloor(i).hashCode();
        }
        return result;
    }
}
