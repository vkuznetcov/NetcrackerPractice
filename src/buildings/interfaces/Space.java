package buildings.interfaces;

public interface Space
{
    int getRoomsAmount();

    void setRoomsAmount(int newRooms);

    double getSquare();

    void setSquare(double newSquare);

    Object clone();
}
