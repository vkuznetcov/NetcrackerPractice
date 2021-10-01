package lab2.buildings;

public class Flat
{
    private double square;
    private int roomQuantity;


    public Flat()
    {
        square = 50;
        roomQuantity = 2;
    }

    public void Print()
    {
        System.out.println("flat: " + square + " " + roomQuantity);
    }

    public Flat(double squareValue)
    {
        square = squareValue;
        roomQuantity = 2;
    }

    public Flat(double squareValue, int roomValue)
    {
        square = squareValue;
        roomQuantity = roomValue;
    }

    public int getRoomQuantity()
    {
        return roomQuantity;
    }

    public double getSquare()
    {
        return square;
    }

    public void setSquare(double squareValue)
    {
        square = squareValue;
    }

    public void setRoomQuantity(int roomValue)
    {
        roomQuantity = roomValue;
    }

}
