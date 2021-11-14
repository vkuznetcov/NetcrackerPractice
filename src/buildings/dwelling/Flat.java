package buildings.dwelling;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;
import buildings.officeBuildings.Office;

import java.io.Serializable;

public class Flat implements Space, Serializable
{
    private double square;
    private int rooms;


    public Flat()
    {
        square = 50;
        rooms = 2;
    }

    public Flat(double squareValue) throws InvalidSpaceAreaException
    {
        if (squareValue <= 0 || squareValue > Office.SQUARE)
        {
            throw new InvalidSpaceAreaException(squareValue);
        }
        square = squareValue;
        rooms = 2;
    }

    public Flat(double squareValue, int roomValue) throws IllegalArgumentException
    {
//        if (squareValue <= 0 || squareValue > Office.SQUARE)
//        {
//            throw new InvalidSpaceAreaException(square);
//        }
//        if (roomValue <= 0 || roomValue > Office.ROOMS)
//        {
//            throw new InvalidRoomsCountException(rooms);
//        }
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

    @Override
    public String toString()
    {
        return "Flat(" + rooms + ", " + square + ")";
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Flat){
            if(getRoomsAmount()!=((Flat) object).getRoomsAmount())
                return false;
            if(getSquare()!=((Flat) object).getSquare())
                return false;
            return true;
        }
        else return false;
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
    public Object clone()
    {
        Object forreturn = new Flat(square,rooms);
        return forreturn;
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
