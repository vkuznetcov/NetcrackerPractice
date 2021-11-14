package buildings.dwelling;

import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory
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
        return new HotelFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces)
    {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount)
    {
        return new Hotel(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor[] floors)
    {
        return new Hotel(floors);
    }
}
