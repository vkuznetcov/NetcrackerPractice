package lab3.officeBuildings.interfaces;

public interface Building
{
    int getFloorsAmount();

    int getSpacesAmount();

    double getSquareAmount();

    int getRoomsAmount();

    Floor[] getFloors();

    Floor getFloor(int num);

    void changeFloor(int num, Floor newFloor);

    Space getSpace(int num);

    void changeSpace(int num, Space newSpace);

    void addSpace(int num, Space newSpace);

    void deleteSpace(int num);

    Space getBestSpace();

    Space[] getSorted();
}
