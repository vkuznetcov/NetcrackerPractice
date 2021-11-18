package buildings.threads;

import buildings.interfaces.Floor;

public class SequentialCleaner implements Runnable
{
    Floor floor;
    Semaphore semaphoreCleaner;

    public SequentialCleaner(Floor floor, Semaphore semaphore1){
        this.floor = floor;
        this.semaphoreCleaner = semaphore1;
    }

    @Override
    public synchronized void run()
    {
        for (int i = 0; i < floor.getFloorSize(); i++)
        {

                while (!semaphoreCleaner.getClean()) {
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                }
                System.out.println("Cleaning space number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
//            semaphoreRepairer.release();
//            semaphoreCleaner.acquire();
                semaphoreCleaner.changeClean();
                semaphoreCleaner.changeRepair();

        }
        System.out.println("Cleaning ended");
    }
}
