package lab4;

import buildings.errors.InexchangeableFloorsException;
import buildings.errors.InexchangeableSpacesException;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class PlacementExchanger
{
    static boolean spaceExchangeChecker(Space space1, Space space2)
    {
        return (space1.getSquare() == space2.getSquare() && space1.getRoomsAmount() == space2.getRoomsAmount());
    }

    static boolean floorExchangeChecker(Floor floor1, Floor floor2)
    {
        return (floor1.getSquareAmount() == floor2.getSquareAmount() && floor1.getFloorSize() == floor2.getFloorSize());
    }

    public static void exchangeFloorSpaces(Floor floor1, int index1, Floor floor2, int index2)
            throws InexchangeableSpacesException, SpaceIndexOutOfBoundsException
    {
        if (!spaceExchangeChecker(floor1.getSpace(index1), floor2.getSpace(index2)))
        {
            throw new InexchangeableSpacesException();
        }

        if (index1 > floor1.getFloorSize() - 1)
        {
            throw new SpaceIndexOutOfBoundsException(index1, floor1.getFloorSize() - 1);
        }

        if (index2 > floor2.getFloorSize() - 1)
        {
            throw new SpaceIndexOutOfBoundsException(index2, floor2.getFloorSize() - 1);
        }

        Space tmp = floor1.getFloor()[index1];
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, tmp);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2)
            throws InexchangeableFloorsException, FloorIndexOutOfBoundsException
    {
        if (!floorExchangeChecker(building1.getFloor(index1), building2.getFloor(index2)))
        {
            throw new InexchangeableFloorsException();
        }
        if (index1 > building1.getFloorsAmount() - 1)
        {
            throw new FloorIndexOutOfBoundsException(index1, building1.getFloorsAmount() - 1);
        }
        if (index2 > building2.getFloorsAmount() - 1)
        {
            throw new FloorIndexOutOfBoundsException(index2, building2.getFloorsAmount() - 1);
        }

        Floor tmp = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, tmp);
    }


}
