package lab3.officeBuildings.exceptions;

import lab3.officeBuildings.Office;

public class InvalidSpaceAreaException extends IllegalArgumentException
{
    public InvalidSpaceAreaException(double square){
        String message = "InvdalidSpaceAreaException\nInvalid value: " + square + "\nEnter value between 1 and " + Office.SQUARE;
        throw new IllegalArgumentException(message);
    }
}
