package buildings.interfaces;

public interface Floor extends Iterable<Space>, Comparable<Floor>
{
    int getFloorSize();

    double getSquareAmount();

    int getRoomsAmount();

    Space[] getFloor();

    Space getSpace(int num);

    void setSpace(int num, Space newSpace);

    void addSpace(int num, Space newSpace);

    void deleteSpace(int num);

    Space getBestSpace();

    Object clone();
}
