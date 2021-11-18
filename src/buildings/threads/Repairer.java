package buildings.threads;

import buildings.interfaces.Floor;

public class Repairer extends Thread
{
    private Floor floor;

    public Repairer(Floor floor){
        this.floor = floor;
    }
    @Override
    public void run()
    {
        for (int i = 0; i < floor.getFloorSize(); i++)
        {
            System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
        }
        System.out.println("Repairing ended");
    }
}
