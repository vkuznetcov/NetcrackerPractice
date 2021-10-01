package lab2.buildings;

public class Dwelling
{
    private DwellingFloor[] floors;

    public Dwelling()
    {
        floors = new DwellingFloor[4];
        for (int i = 0; i < 4; ++i)
        {
            floors[i] = new DwellingFloor(10);
        }
    }

    public Dwelling(int floorsValue, int[] flatsValue)
    {
        if (flatsValue.length < floorsValue)
        {
            System.out.println("ERROR: invalid flats size");
            return;
        }
        floors = new DwellingFloor[floorsValue];
        for (int i = 0; i < floors.length; ++i)
        {
            floors[i] = new DwellingFloor(flatsValue[i]);
        }
    }

    public Dwelling(DwellingFloor[] newFloors)
    {
        floors = new DwellingFloor[newFloors.length];
        for (int i = 0; i < floors.length; ++i)
        {
            floors[i] = new DwellingFloor(newFloors[i].getFloor());
        }
    }

    public int getFloorsAmount()
    {
        return floors.length;
    }

    public int getFlatsAmount()
    {
        int flatsAmount = 0;
        for (DwellingFloor current : floors)
        {
            flatsAmount += current.getFlats();
        }
        return flatsAmount;
    }

    public double getSquareAmount()
    {
        double squareAmount = 0;
        for (DwellingFloor current : floors)
        {
            squareAmount += current.getSquare();
        }

        return squareAmount;
    }

    public int getRoomsAmount()
    {
        int roomsAmount = 0;
        for (DwellingFloor current : floors)
        {
            roomsAmount += current.getRooms();
        }

        return roomsAmount;
    }

    public DwellingFloor[] getFloors()
    {
        DwellingFloor[] forReturn = new DwellingFloor[floors.length];
        for (int i = 0; i < floors.length; ++i)
        {
            forReturn[i] = new DwellingFloor(floors[i].getFloor());
        }
        return forReturn;
    }

    public DwellingFloor getFloor(int floorNumber)
    {
        return new DwellingFloor(floors[floorNumber].getFloor());
    }

    public void changeFloor(int floorNumber, DwellingFloor newFloor)
    {
        floors[floorNumber] = new DwellingFloor(newFloor.getFloor());
    }

    public Flat getFlat(int flatNumber)
    {
        if (flatNumber > this.getFlatsAmount())
        {
            //throw exception
            System.out.println("ERROR: invalid flat number");
            return new Flat();
        }

        int floorNumber = 0;
        for (floorNumber = 0; floorNumber < floors.length && flatNumber > floors[floorNumber].getFlats(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFlats();
        }
        return new Flat(floors[floorNumber].getFlat(flatNumber).getSquare(),
                        floors[floorNumber].getFlat(flatNumber).getRoomQuantity());
    }

    public void changeFlat(int flatNumber, Flat newFlat)
    {
        if (flatNumber > this.getFlatsAmount())
        {
            //throw exception
            System.out.println("ERROR: invalid flat number");
            return;
        }

        int floorNumber = 0;
        for (floorNumber = 0; floorNumber < floors.length && flatNumber > floors[floorNumber].getFlats(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFlats();
        }

        floors[floorNumber].changeFlat(flatNumber, newFlat);
    }

    public void addFlat(int flatNumber, Flat newFlat)
    {
        if (flatNumber > this.getFlatsAmount() + 1)
        {
            //throw exception
            System.out.println("ERROR: invalid flat number");
            return;
        }

        int floorNumber = 0;
        for (floorNumber = 0; floorNumber < floors.length && flatNumber > floors[floorNumber].getFlats(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFlats();
        }

        floors[floorNumber].addFlat(flatNumber, newFlat);
    }

    public void deleteFlat(int flatNumber)
    {
        if (flatNumber > this.getFlatsAmount())
        {
            //throw exception
            System.out.println("ERROR: invalid flat number");
            return;
        }

        int floorNumber = 0;
        for (floorNumber = 0; floorNumber < floors.length && flatNumber > floors[floorNumber].getFlats(); ++floorNumber)
        {
            flatNumber -= floors[floorNumber].getFlats();
        }

        floors[floorNumber].deleteFlat(flatNumber);
    }

    public Flat getBestSpace()
    {
        Flat maxSquare = floors[0].getBestSpace();
        for (int i = 1; i < floors.length; ++i)
        {
            if (floors[i].getBestSpace().getSquare() > maxSquare.getSquare())
            {
                maxSquare = floors[i].getBestSpace();
            }
        }
        return maxSquare;
    }

    public Flat[] getSorted()
    {
        Flat[] forReturn = new Flat[this.getFlatsAmount()];
        {
            int flatIndex = 0;
            for (int i = 0; i < floors.length; ++i)
            {
                for (int j = 0; j < floors[i].getFlats(); ++j)
                {
                    forReturn[flatIndex] = floors[i].getFlat(j);
                    flatIndex++;
                }
            }
        }
        this.sort(forReturn);
        return forReturn;
    }

    private void sort(Flat[] flatArray)
    {
        for (int i = 0; i < flatArray.length; ++i)
        {
            for (int j = 0; j < flatArray.length - 1; ++j)
            {
                if (flatArray[j].getSquare() < flatArray[j + 1].getSquare())
                {
                    Flat tmp = flatArray[j];
                    flatArray[j] = flatArray[j + 1];
                    flatArray[j + 1] = tmp;
                }
            }
        }
    }
}
