package buildings.officeBuildings.lists.officeList;



import buildings.interfaces.Space;
import buildings.officeBuildings.Office;

import java.io.Serializable;

public class ArrListNode implements Serializable
{
    public Space data;
    public ArrListNode next;

    public ArrListNode()
    {
        data = new Office();
        next = null;
    }

    public ArrListNode(Space newOffice)
    {
        data = newOffice;
        //data = (Space)newOffice.clone();
    }

    public ArrListNode(ArrListNode newNode)
    {
        data = new Office(newNode.data.getRoomsAmount(), newNode.data.getSquare());
        //data = (Space)newNode.data.clone();
    }

}
