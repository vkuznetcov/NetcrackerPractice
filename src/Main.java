import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Floor;
import buildings.threads.Semaphore;
import buildings.threads.SequentialCleaner;
import buildings.threads.SequentialRepairer;

public class Main
{
    public static void main(String[] args)
    {
        Floor floor = new DwellingFloor(5);
        Semaphore semaphore = new Semaphore();
        SequentialCleaner cleaner = new SequentialCleaner(floor, semaphore);
        SequentialRepairer repairer = new SequentialRepairer(floor, semaphore);
        Thread thread = new Thread(cleaner);
        Thread thread1 = new Thread(repairer);
        thread1.start();
        thread.start();
    }
}
