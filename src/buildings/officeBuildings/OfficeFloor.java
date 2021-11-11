package buildings.officeBuildings;


import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.lists.officeList.ArrList;
import buildings.officeBuildings.lists.officeList.ArrListNode;

public class OfficeFloor implements Floor, java.io.Serializable
{
    private ArrList floor;

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
        if (num >= floor.length())
        {
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        }
        return new Office(floor.getByNum(num).data.getRoomsAmount(), floor.getByNum(num).data.getSquare());
    }

    public void setSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if (num >= floor.length())
        {
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        }
        floor.changeByNum(num, newSpace);
    }

    public void addSpace(int num, Space newSpace) throws SpaceIndexOutOfBoundsException
    {
        if (num > floor.length())
        {
            throw new SpaceIndexOutOfBoundsException(num, floor.length());
        }
        this.addNode(num, new ArrListNode(newSpace));
        //floor.addByNum(num, newSpace);
    }

    public void deleteSpace(int num) throws SpaceIndexOutOfBoundsException
    {
        if (num >= floor.length())
        {
            throw new SpaceIndexOutOfBoundsException(num, floor.length() - 1);
        }
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

    @Override
    public String toString()
    {
        StringBuffer forreturn = new StringBuffer("Office Floor(");
        for (int i = 0; i < getFloorSize(); i++)
        {
            if (i == getFloorSize() - 1)
            {
                forreturn.append(getSpace(i).toString());
                break;
            }
            forreturn.append(getSpace(i).toString() + ", ");
        }
        forreturn.append(")");
        return forreturn.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof OfficeFloor){
            if(getFloorSize()!=((OfficeFloor) object).getFloorSize())
                return false;
            for(int i = 0; i < getFloorSize(); i++){
                if(!getSpace(i).equals(((OfficeFloor) object).getSpace(i)))
                    return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public Object clone()
    {
        OfficeFloor forreturn = new OfficeFloor(getFloorSize());
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
}
