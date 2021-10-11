package lab3.officeBuildings.lists.officeFloorList;

import lab3.officeBuildings.Office;
import lab3.officeBuildings.OfficeFloor;
import lab3.officeBuildings.interfaces.Floor;
import lab3.officeBuildings.interfaces.Space;

public class LinkList
{
    LinkListNode head;
    int size = 0;

    public LinkList()
    {
        head = new LinkListNode();
        head.next = head;
        head.prev = head;
        size++;
    }

    public LinkList(int size)
    {
        this.size++;
        head = new LinkListNode();
        head.next = head;
        head.prev = head;

        for (int i = 0; i < size - 1; ++i)
        {
            this.pushBack(new OfficeFloor());
        }
    }

    public LinkList(LinkListNode newNode)
    {
        head = newNode;
        head.prev = head;
        head.next = head;
        ++size;
    }

    public void pushBack(Floor newFloor)
    {
        LinkListNode tmp = head;
        while (head.next != tmp)
        {
            head = head.next;
        }
        head.next = new LinkListNode(newFloor);
        head.next.prev = head;
        head.next.next = tmp;
        tmp.prev = head.next;
        head = tmp;
        ++size;
    }

    public void pushFront(Floor newFloor)
    {
        LinkListNode headTmp = head;
        LinkListNode tmp = new LinkListNode(newFloor);
        tmp.next = head;

        tmp.prev = head.prev;
        head.prev.next = tmp;

        head.prev = tmp;

        head = tmp;
        ++size;
    }

    public void popBack()
    {
        head.prev = head.prev.prev;
        head.prev.next = head;
        --size;
    }

    public void popFront()
    {
        head.prev.next = head.next;
        head = head.prev;
        head.next.prev = head;
        head = head.next;
        --size;
    }

    public int length()
    {
        return size;
    }

    public void addByNum(int num, Floor newFloor)
    {
        LinkListNode tmp = head;

        for (int i = 0; i < num; ++i)
        {
            head = head.next;
        }
        LinkListNode tmpNode = new LinkListNode(newFloor);

        tmpNode.next = head.next;
        head.next.prev = tmpNode;

        head.next = tmpNode;
        tmpNode.prev = head;

        head = tmp;
        ++size;
    }

    public LinkListNode getByNum(int num)
    {
        LinkListNode tmp = head;
        for (int i = 0; i < num; ++i)
        {
            tmp = tmp.next;
        }
        return tmp;
    }

    public void deleteByNum(int num)
    {
        LinkListNode tmp = head;

        for (int i = 0; i < num; ++i)
        {
            tmp = tmp.next;
        }


        tmp.next = tmp.next.next;
        tmp.next.prev = tmp;
        --size;
    }

    public void changeByNum(int num, Floor newFloor)
    {
        if(num == 0){
            this.popFront();
            this.pushFront(newFloor);
            return;
        }

        LinkListNode tmp = head;
        for (int i = 0; i < num - 1; ++i)
        {
            tmp = tmp.next;
        }

        LinkListNode newNode = new LinkListNode(newFloor);

        newNode.next = tmp.next.next;
        newNode.next.prev = newNode;

        tmp.next = newNode;
        newNode.prev = tmp;
    }
}
