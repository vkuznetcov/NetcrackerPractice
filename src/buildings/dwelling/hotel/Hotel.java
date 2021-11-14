package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class Hotel extends Dwelling
{
    private int stars;

    public Hotel(int floorsValue, int... flatsValue)
    {
        super(floorsValue, flatsValue);
        for(int i = 0; i < floorsValue; i++){
            setFloor(i, new HotelFloor(flatsValue[i]));
        }
        stars = HotelFloor.STARS;
    }

    public Hotel(Floor... newFloors)
    {
        super(newFloors);
        updateStars();
//        int max = HotelFloor.STARS;
//        boolean flag = false;
//        for(int i = 0; i < newFloors.length; i++){
//            if(getFloor(i) instanceof HotelFloor){
//                flag = true;
//                if(((HotelFloor) getFloor(i)).getStars() > max)
//                    max = ((HotelFloor) getFloor(i)).getStars();
//            }
//        }
//        if(flag){
//            this.stars = max;
//        }
//        else{
//            this.stars = 0;
//        }
    }

    public int getStars(){
        updateStars();
        return stars;
    }

    private void updateStars(){
        int max = HotelFloor.STARS;
        boolean flag = false;
        for(int i = 0; i < getFloorsAmount(); i++){
            if(getFloor(i) instanceof HotelFloor){
                flag = true;
                if(((HotelFloor) getFloor(i)).getStars() > max)
                    max = ((HotelFloor) getFloor(i)).getStars();
            }
        }
        if(flag){
            this.stars = max;
        }
        else{
            this.stars = 0;
        }
    }

    private double getCoeff(int floorIndex){
        double coeff = 0;
        switch(getFloor(floorIndex) instanceof HotelFloor ? ((HotelFloor)getFloor(floorIndex)).getStars():0){
            case 1:
            {
                coeff = 0.25;
                break;
            }
            case 2:
            {
                coeff = 0.5;
                break;
            }
            case 3:
            {
                coeff = 1;
                break;
            }
            case 4:
            {
                coeff = 1.25;
                break;
            }
            case 5:
            {
                coeff = 1.5;
                break;
            }
            default: break;
        }
        return coeff;
    }

    @Override
    public Space getBestSpace(){
        double coeff = 0;
        double area = 0;
        double areacoeff = 0;
        Space forreturn = getSpace(0);
        for(int floorIndex = 0; floorIndex < getFloorsAmount(); floorIndex++)
        {
            coeff = getCoeff(floorIndex);
            area=getFloor(floorIndex).getBestSpace().getSquare();

            if(area*coeff>areacoeff)
            {
                areacoeff=area*coeff;
                forreturn = getFloor(floorIndex).getBestSpace();
            }
        }
        if(areacoeff != 0)
            return forreturn;
        return null;
    }

    @Override
    public String toString(){
        StringBuilder forReturn = new StringBuilder("Hotel(");
        forReturn.append(getStars()).append(", ").append(getFloorsAmount()).append(", ");
        for (int i = 0; i < getFloorsAmount(); i++)
        {
            if (i == getFloorsAmount() - 1)
            {
                forReturn.append(getFloor(i).toString());
                break;
            }
            forReturn.append(getFloor(i).toString()).append(", ");
        }
        forReturn.append(")");
        return forReturn.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Hotel){
            if(getFloorsAmount()!=((Hotel) object).getFloorsAmount())
                return false;
            for(int i = 0; i < getFloorsAmount(); i++){
                if(!getFloor(i).equals(((Hotel) object).getFloor(i)))
                    return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorsAmount(); ++i){
            result += getFloorsAmount() ^ getFloor(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone()
    {
        Hotel forreturn = new Hotel(getFloors());
        for(int i = 0; i < forreturn.getFloorsAmount(); i++){
            forreturn.setFloor(i, (Floor)getFloor(i).clone());
        }
        return forreturn;
    }
}
