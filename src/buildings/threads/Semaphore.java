package buildings.threads;

public class Semaphore
{
    private int counter;

    private int maxValue;

    private boolean isRepair;
    private boolean isClean;

    String type;

    public Semaphore(int counter, String str){
        this.counter = counter;
        maxValue = counter;
        isRepair = true;
        isClean = false;
        type = str;
    }

    public void acquire(){
        --counter;
        System.out.println(type + " aq " + counter);
        while(counter == 0){}
//        --counter;
    }

    public void release(){
        counter = counter < maxValue ? counter + 1 : maxValue;
        System.out.println(type + " " + counter);
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
