package lab2.buildings;

import lab3.officeBuildings.Office;
import lab3.officeBuildings.exceptions.InvalidRoomsCountException;
import lab3.officeBuildings.exceptions.InvalidSpaceAreaException;
import lab3.officeBuildings.interfaces.Space;

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

    public Flat(double squareValue) throws InvalidSpaceAreaException
    {
        if(squareValue <= 0 || squareValue > Office.SQUARE)
            throw new InvalidSpaceAreaException(squareValue);
        square = squareValue;
        rooms = 2;
    }

    public Flat(double squareValue, int roomValue) throws InvalidSpaceAreaException, InvalidRoomsCountException
    {
        if(squareValue <= 0 || squareValue > Office.SQUARE)
            throw new InvalidSpaceAreaException(square);
        if(roomValue <= 0 || roomValue > Office.ROOMS)
            throw new InvalidRoomsCountException(rooms);
        square = squareValue;
        rooms = roomValue;
    }

    public int getRoomsAmount()
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

    public void setRoomsAmount(int roomValue)
    {
        rooms = roomValue;
    }

}
