package lab3.officeBuildings.lists.officeList;

import lab3.officeBuildings.interfaces.Space;
import lab3.officeBuildings.Office;

public class ArrListNode
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
        data = new Office(newOffice.getRoomsAmount(), newOffice.getSquare());
    }

    public ArrListNode(ArrListNode newNode)
    {
        data = new Office(newNode.data.getRoomsAmount(), newNode.data.getSquare());
    }

}
