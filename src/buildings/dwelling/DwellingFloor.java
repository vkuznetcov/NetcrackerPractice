package buildings.dwelling;

import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable
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
        floor = new Space[flatsQuantity];
        for (int i = 0; i < flatsQuantity; ++i)
        {
            floor[i] = new Flat();
        }
    }

    public DwellingFloor(Space... flatsArray)
    {
        floor = new Space[flatsArray.length];
        for (int i = 0; i < flatsArray.length; ++i)
        {
            //floor[i] = new Flat(flatsArray[i].getSquare(), flatsArray[i].getRoomsAmount());
            floor[i] = (Space)flatsArray[i].clone();
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

    public Space[] getFloor()
    {
        Space[] forReturn = new Space[floor.length];
        for (int i = 0; i < floor.length; ++i)
        {
            //forReturn[i] = new Flat(floor[i].getSquare(), floor[i].getRoomsAmount());
            forReturn[i] = (Space)floor[i].clone();
        }
        return forReturn;
    }

    public Space getSpace(int flatIndex) throws SpaceIndexOutOfBoundsException
    {
        if(flatIndex >= floor.length)
            throw new SpaceIndexOutOfBoundsException(flatIndex, floor.length - 1);
        //Flat forReturn = new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRoom_quantity());
        //return forReturn;
        //return new Flat(floor[flatIndex].getSquare(), floor[flatIndex].getRoomsAmount());
        return (Space)floor[flatIndex].clone();
    }


    public void setSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if(num >= floor.length)
            throw new SpaceIndexOutOfBoundsException(num, floor.length - 1);

        //floor[num] = new Flat(newSpace.getSquare(), newSpace.getRoomsAmount());
        floor[num] = (Space)newSpace.clone();
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
                //newFloor[i] = new Flat(newSpace.getSquare(), newSpace.getRoomsAmount());
                newFloor[i] = (Space)newSpace.clone();
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

    @Override
    public String toString(){
        StringBuilder forReturn = new StringBuilder("Dwelling Floor(");
        for(int i = 0; i < getFloorSize(); i++){
            if(i == getFloorSize() - 1)
            {
                forReturn.append(floor[i].toString());
                break;
            }
            forReturn.append(floor[i].toString()).append(", ");
        }
        forReturn.append(")");
        return forReturn.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof DwellingFloor){
            if(getFloorSize()!=((DwellingFloor) object).getFloorSize())
                return false;
            for(int i = 0; i < getFloorSize(); i++){
                if(!getSpace(i).equals(((DwellingFloor) object).getSpace(i)))
                    return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public Object clone()
    {
        DwellingFloor forreturn = new DwellingFloor(getFloorSize());
        for(int i = 0; i < forreturn.getFloorSize(); i++){
            forreturn.setSpace(i, (Space)getSpace(i).clone());
        }
        return forreturn;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorSize(); ++i){
            result += getFloorSize() ^ getSpace(i).hashCode();
        }
        return result;
    }

    @Override
    public Iterator<Space> iterator()
    {
        return new Iterator<Space>()
        {
            private int index = 0;

            @Override
            public boolean hasNext()
            {
                return index < floor.length;
            }

            @Override
            public Space next()
            {
                return getSpace(index++);
            }
        };
    }


    @Override
    public int compareTo(Floor o)
    {
        if(this.getFloorSize() < o.getFloorSize())
            return -1;
        else if(this.getFloorSize() == o.getFloorSize())
            return 0;
        else return 1;
    }
}
