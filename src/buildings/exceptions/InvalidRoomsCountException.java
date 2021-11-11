package buildings.exceptions;


import buildings.officeBuildings.Office;

public class InvalidRoomsCountException extends IllegalArgumentException
{
    public InvalidRoomsCountException(int rooms)
    {
        String message =
                "InvdalidSpaceAreaException\nInvalid value: " + rooms + "\nEnter value between 1 and " + Office.ROOMS;
        throw new IllegalArgumentException(message);
    }
}
