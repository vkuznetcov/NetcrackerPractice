package buildings.threads;

public class Semaphore
{
    private int counter;

    private int maxValue;

    private boolean isRepair;
    private boolean isClean;

    public Semaphore(int counter){
        this.counter = counter;
        maxValue = counter;
        isRepair = true;
        isClean = false;
    }

    public void acquire(){
        --counter;
        while(counter < 1){}
//        --counter;
    }

    public void release(){
        counter = counter < maxValue ? counter + 1 : maxValue;
    }

    public int getCounter(){
        return counter;
    }

    public synchronized void changeRepair(){
        isRepair = !isRepair;
    }

    public synchronized void changeClean(){
        isClean = !isClean;
    }

    public boolean getRepair(){
        return isRepair;
    }
    public boolean getClean(){
        return isClean;
    }

}
