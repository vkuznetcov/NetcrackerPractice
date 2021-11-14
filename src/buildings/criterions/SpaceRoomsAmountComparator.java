package buildings.criterions;

import buildings.interfaces.Space;

import java.util.Comparator;

public class SpaceRoomsAmountComparator implements Comparator<Space>
{
    @Override
    public int compare(Space o1, Space o2)
    {
        if(o1.getRoomsAmount() > o2.getRoomsAmount())
            return -1;
        else if(o1.getRoomsAmount() == o2.getRoomsAmount())
            return 0;
        else return 1;
    }
}
