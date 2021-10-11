package lab3.officeBuildings.exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException
{
    public FloorIndexOutOfBoundsException(int num, int size){
        String message = "FloorIndexOfBoundsException\nInvalid argument: " + num + "\nEnter value between 0 and " + size;
        throw new IndexOutOfBoundsException(message);
    }
}
