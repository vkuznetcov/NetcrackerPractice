package lab3.officeBuildings.exceptions;

public class InvalidSpacesNumberException extends IllegalArgumentException
{
    public InvalidSpacesNumberException(int num, int size){
        String message = "InvalidSpacesNumberException\nInvalid argument: number of values equals " + num + "\nEnter " + size + " values";
        throw new IllegalArgumentException(message);
    }
}
