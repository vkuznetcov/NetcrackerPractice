package buildings.interfaces;

public interface Building extends Iterable<Floor>
{
    int getFloorsAmount();

    int getSpacesAmount();

    double getSquareAmount();

    int getRoomsAmount();

    Floor[] getFloors();

    Floor getFloor(int num);

    void setFloor(int num, Floor newFloor);

    Space getSpace(int num);

    void setSpace(int num, Space newSpace);

    void addSpace(int num, Space newSpace);

    void deleteSpace(int num);

    Space getBestSpace();

    Space[] getSorted();

    Object clone();
}
