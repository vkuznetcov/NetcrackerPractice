package lab3.officeBuildings.exceptions;

import lab3.officeBuildings.interfaces.Space;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException
{
    public SpaceIndexOutOfBoundsException(int num, int size){
        String message = "SpaceIndexOfBoundsException\nInvalid argument: " + num + "\nEnter number between 0 and " + size;
        throw new IndexOutOfBoundsException(message);
    }
}
