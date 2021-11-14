package buildings.criterions;

import buildings.interfaces.Floor;

import java.util.Comparator;

public class FloorSpacesAmountComparator implements Comparator<Floor>
{
    @Override
    public int compare(Floor o1, Floor o2)
    {
        if(o1.getSquareAmount() > o2.getSquareAmount())
            return -1;
        else if(o1.getSquareAmount() == o2.getSquareAmount())
            return 0;
        else return 1;
    }
}
