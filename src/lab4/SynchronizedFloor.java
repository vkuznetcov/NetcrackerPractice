package lab4;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {

    Floor syncFloor;

    public SynchronizedFloor(Floor floor){
        syncFloor = floor;
    }

    @Override
    public synchronized int getFloorSize() {
        return syncFloor.getFloorSize();
    }

    @Override
    public synchronized double getSquareAmount() {
        return syncFloor.getSquareAmount();
    }

    @Override
    public synchronized int getRoomsAmount() {
        return syncFloor.getRoomsAmount();
    }

    @Override
    public synchronized Space[] getFloor() {
        return syncFloor.getFloor();
    }

    @Override
    public synchronized Space getSpace(int num) {
        return syncFloor.getSpace(num);
    }

    @Override
    public synchronized void setSpace(int num, Space newSpace) {
        syncFloor.setSpace(num, newSpace);
    }

    @Override
    public synchronized void addSpace(int num, Space newSpace) {
        syncFloor.addSpace(num, newSpace);
    }

    @Override
    public synchronized void deleteSpace(int num) {
        syncFloor.deleteSpace(num);
    }

    @Override
    public synchronized Space getBestSpace() {
        return syncFloor.getBestSpace();
    }

    @Override
    public synchronized Object clone() {
        return syncFloor.clone();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return syncFloor.compareTo(o);
    }

    @Override
    public synchronized Iterator<Space> iterator() {
        return null;
    }
}
