package buildings.threads;

import buildings.interfaces.Floor;

public class SequentialRepairer implements Runnable
{
    Floor floor;
    Semaphore semaphoreCleaner;

    public SequentialRepairer(Floor floor, Semaphore semaphore1){
        this.floor = floor;
        this.semaphoreCleaner = semaphore1;
    }

    @Override
    public synchronized void run()
    {
        for (int i = 0; i < floor.getFloorSize(); i++)
        {
                while(!semaphoreCleaner.getRepair()) {
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
//                semaphoreCleaner.release();
//                semaphoreRepairer.acquire();
//
                semaphoreCleaner.changeRepair();
                semaphoreCleaner.changeClean();

        }
        System.out.println("Repairing ended");
    }
}
