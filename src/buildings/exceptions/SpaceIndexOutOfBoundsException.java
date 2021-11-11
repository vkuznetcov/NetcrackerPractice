package buildings.exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException
{
    public SpaceIndexOutOfBoundsException(int num, int size)
    {
        String message =
                "SpaceIndexOfBoundsException\nInvalid argument: " + num + "\nEnter number between 0 and " + size;
        throw new IndexOutOfBoundsException(message);
    }
}
