package lab3.officeBuildings;

public class Office implements Space
{
    //fields
    private int rooms;
    private double square;

    //constants
    final double SQUARE = 250;
    final int ROOMS = 1;

    public Office()
    {
        square = SQUARE;
        rooms = ROOMS;
    }

    public Office(double square)
    {
        this.square = square;
        rooms = ROOMS;
    }

    public Office(int rooms, double square)
    {
        this.rooms = rooms;
        this.square = square;
    }

    public int getRooms()
    {
        return rooms;
    }

    public void setRooms(int rooms)
    {
        this.rooms = rooms;
    }

    public double getSquare()
    {
        return square;
    }

    public void setSquare(double square)
    {
        this.square = square;
    }
}
