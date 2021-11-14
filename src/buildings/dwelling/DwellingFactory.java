package buildings.dwelling;

import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.WeakHashMap;

public class DwellingFactory implements BuildingFactory
{
    @Override
    public Space createSpace(double area)
    {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area)
    {
        return new Flat(area, roomsCount);
    }

    @Override
    public Floor createFloor(int spacesCount)
    {
        return new DwellingFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces)
    {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount)
    {
        return new Dwelling(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor[] floors)
    {
        return new Dwelling(floors);
    }
}
