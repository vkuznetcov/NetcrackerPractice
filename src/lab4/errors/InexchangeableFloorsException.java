package lab4.errors;

public class InexchangeableFloorsException extends RuntimeException
{
    public InexchangeableFloorsException(){
        String message = "Inexchangeable Floors!\nTry another one";
        throw new Error(message);
    }
}
