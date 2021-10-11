package lab3.officeBuildings.lists.officeFloorList;

import lab3.officeBuildings.OfficeFloor;
import lab3.officeBuildings.interfaces.Floor;

public class LinkListNode
{
    public Floor data;
    public LinkListNode prev;
    public LinkListNode next;

    public LinkListNode()
    {
        data = new OfficeFloor();
        prev = null;
        next = null;
    }

    public LinkListNode(Floor newFloor)
    {
        data = new OfficeFloor(newFloor.getFloor());
    }

    public LinkListNode(LinkListNode newNode)
    {
        data = new OfficeFloor(newNode.data.getFloor());
    }
}
