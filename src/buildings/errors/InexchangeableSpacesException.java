package buildings.errors;

public class InexchangeableSpacesException extends RuntimeException
{
    public InexchangeableSpacesException(){
        String message = "Inexchangeable Spaces!\nTry another one";
        throw new Error(message);
    }
}