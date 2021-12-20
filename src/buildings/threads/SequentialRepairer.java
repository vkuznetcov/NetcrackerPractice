package buildings.threads;

import buildings.interfaces.Floor;

public class SequentialRepairer implements Runnable
{
    Floor floor;
    Semaphore semaphore;

    public SequentialRepairer(Floor floor, Semaphore semaphore){
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public synchronized void run()
    {
        for (int i = 0; i < floor.getFloorSize(); i++)
        {
                while(!semaphore.getRepair()) {
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
                semaphore.changeRepair();
                semaphore.changeClean();

        }
        System.out.println("Repairing ended");
    }
}
