package buildings.officeBuildings;


import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;
import java.io.Serializable;

public class Office implements Space, Serializable
{
    //constants
    public static final double SQUARE = 250;
    public static final int ROOMS = 1;
    //fields
    private int rooms;
    private double square;

    public Office()
    {
        square = SQUARE;
        rooms = ROOMS;
    }

    public Office(double square) throws InvalidSpaceAreaException
    {
        if (square <= 0 || square > SQUARE)
        {
            throw new InvalidSpaceAreaException(square);
        }
        this.square = square;
        rooms = ROOMS;
    }

    public Office(int rooms, double square) throws IllegalArgumentException
    {
        if (square <= 0 || square > SQUARE)
        {
            throw new InvalidSpaceAreaException(square);
        }
        if (rooms <= 0 || rooms > ROOMS)
        {
            throw new InvalidRoomsCountException(rooms);
        }
        this.rooms = rooms;
        this.square = square;
    }

    public Office(Space newOffice)
    {
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

    @Override
    public String toString()
    {
        return "Office(" + rooms + ", " + square + ")";
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Office){
            if(getRoomsAmount()!=((Office) object).getRoomsAmount())
                return false;
            if(getSquare()!=((Office) object).getSquare())
                return false;
            return true;
        }
        else return false;
    }

    @Override
    public Object clone()
    {
        Object forreturn = new Office(rooms, square);
        return forreturn;
    }

    @Override
    public int hashCode(){
        int result;
        long doubleAsLong = Double.doubleToLongBits(square);
        long mask1 = 0b0000000000000000000000000000000011111111111111111111111111111111L;
        long mask2 = 0b1111111111111111111111111111111100000000000000000000000000000000L;
        long a = doubleAsLong & mask1;
        long b = doubleAsLong & mask2;
        result = (int)(rooms ^ a ^ b);
        return result;
    }

    @Override
    public int compareTo(Space o)
    {
        if(this.getSquare() < o.getSquare())
            return -1;
        else if(this.getSquare() == o.getSquare())
            return 0;
        else return 1;
    }
}
