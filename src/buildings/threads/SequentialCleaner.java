package buildings.threads;

import buildings.interfaces.Floor;

public class SequentialCleaner implements Runnable
{
    Floor floor;
    Semaphore semaphore;

    public SequentialCleaner(Floor floor, Semaphore semaphore){
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public synchronized void run()
    {
        for (int i = 0; i < floor.getFloorSize(); i++)
        {

                while (!semaphore.getClean()) {
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                }
                System.out.println("Cleaning space number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
//            semaphoreRepairer.release();
//            semaphoreCleaner.acquire();
                semaphore.changeClean();
                semaphore.changeRepair();

        }
        System.out.println("Cleaning ended");
    }
}
