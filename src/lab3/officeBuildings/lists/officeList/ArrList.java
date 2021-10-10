package lab3.officeBuildings.lists.officeList;

import lab3.officeBuildings.Office;
import lab3.officeBuildings.interfaces.Space;

public class ArrList
{
    private ArrListNode head;
    private int size = 0;

    public ArrList()
    {
        head = new ArrListNode();
        head.next = head;
    }

    public ArrList(int size)
    {
        this.size = size;
        head = new ArrListNode();

        for (int i = 0; i < size; ++i)
        {
            this.pushBack(new Office());
        }
    }

    public ArrList(ArrListNode newNode)
    {
        head = newNode;
        head.next = head;
        ++size;
    }

    public void pushBack(Space newOffice)
    {
        ArrListNode tmp = head;
        while (head.next != tmp)
        {
            head = head.next;
        }
        head.next = new ArrListNode(newOffice);
        head.next.next = tmp;
        head = tmp;
        ++size;
    }

    public void pushFront(Space newOffice)
    {
        ArrListNode headTmp = head;
        ArrListNode tmp = new ArrListNode(newOffice);
        tmp.next = head;
        while (head.next != headTmp)
        {
            head = head.next;
        }
        head.next = tmp;
        head = tmp;
        ++size;
    }

    public void popBack()
    {
        ArrListNode tmp = head;
        while (head.next.next != tmp)
        {
            head = head.next;
        }
        head.next = tmp;
        head = tmp;
        --size;
    }

    public void popFront()
    {
        ArrListNode tmp = head;
        while (head.next != tmp)
        {
            head = head.next;
        }
        head.next = head.next.next;
        head = head.next;
        --size;
    }

    public int length()
    {
        return size;
    }

    public void addByNum(int num, Space newOffice)
    {
        ArrListNode tmp = head;

        for (int i = 0; i < num; ++i)
        {
            head = head.next;
        }
        ArrListNode tmpNode = new ArrListNode(newOffice);

        tmpNode.next = head.next;
        head.next = tmpNode;
        head = tmp;
        ++size;
    }

    public ArrListNode getByNum(int num)
    {
        ArrListNode tmp = head;
        for (int i = 0; i < num; ++i)
        {
            tmp = tmp.next;
        }
        return tmp;
    }

    public void deleteByNum(int num)
    {
        ArrListNode tmp = head;

        for (int i = 0; i < num; ++i)
        {
            tmp = tmp.next;
        }

        tmp.next = tmp.next.next;
        --size;
    }

    public void changeByNum(int num, Space newOffice)
    {
        ArrListNode tmp = head;
        for (int i = 0; i < num; ++i)
        {
            tmp = tmp.next;
        }

        ArrListNode newNode = new ArrListNode(newOffice);
        newNode.next = tmp.next;
        tmp.next = newNode;
    }
}
