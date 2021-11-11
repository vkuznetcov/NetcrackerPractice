package buildings.officeBuildings.lists.officeFloorList;

import buildings.interfaces.Floor;
import buildings.officeBuildings.OfficeFloor;

import java.io.Serializable;

public class LinkListNode implements Serializable
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
        //data = (Floor)newFloor.clone();
    }

    public LinkListNode(LinkListNode newNode)
    {
        data = new OfficeFloor(newNode.data.getFloor());
    }
}
