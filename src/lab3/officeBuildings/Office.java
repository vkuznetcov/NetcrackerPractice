package lab3.officeBuildings;

import lab3.officeBuildings.exceptions.InvalidRoomsCountException;
import lab3.officeBuildings.exceptions.InvalidSpaceAreaException;
import lab3.officeBuildings.interfaces.Space;

public class Office implements Space
{
    //fields
    private int rooms;
    private double square;

    //constants
    public static final double SQUARE = 250;
    public static final int ROOMS = 1;

    public Office()
    {
        square = SQUARE;
        rooms = ROOMS;
    }

    public Office(double square) throws InvalidSpaceAreaException
    {
        if(square <= 0 || square > 250)
            throw new InvalidSpaceAreaException(square);
        this.square = square;
        rooms = ROOMS;
    }

    public Office(int rooms, double square) throws InvalidSpaceAreaException, InvalidRoomsCountException
    {
        if(square <= 0 || square > SQUARE)
            throw new InvalidSpaceAreaException(square);
        if(rooms <= 0 || rooms > ROOMS)
            throw new InvalidRoomsCountException(rooms);
        this.rooms = rooms;
        this.square = square;
    }

    public Office(Space newOffice){
        rooms = newOffice.getRoomsAmount();
        square = newOffice.getSquare();
    }

    public int getRoomsAmount()
    {
        return rooms;
    }

    public void setRoomsAmount(int rooms)
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
