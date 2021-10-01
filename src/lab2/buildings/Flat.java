package lab2.buildings;

import lab3.officeBuildings.Space;

public class Flat implements Space
{
    private double square;
    private int rooms;


    public Flat()
    {
        square = 50;
        rooms = 2;
    }

    public void Print()
    {
        System.out.println("flat: " + square + " " + rooms);
    }

    public Flat(double squareValue)
    {
        square = squareValue;
        rooms = 2;
    }

    public Flat(double squareValue, int roomValue)
    {
        square = squareValue;
        rooms = roomValue;
    }

    public int getRooms()
    {
        return rooms;
    }

    public double getSquare()
    {
        return square;
    }

    public void setSquare(double squareValue)
    {
        square = squareValue;
    }

    public void setRooms(int roomValue)
    {
        rooms = roomValue;
    }

}
