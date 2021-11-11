package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Space;

public class HotelFloor extends DwellingFloor
{
    private int stars;
    static final int STARS = 1;

    public HotelFloor(int spacesQuantity){
        super(spacesQuantity);
        stars = this.STARS;
    }

    public HotelFloor(Space[] spacesArr){
        super(spacesArr);
        stars = this.STARS;
    }

    public int getStars(){
        return stars;
    }

    public void setStars(int value){
        stars = value;
    }

    @Override
    public String toString(){
        StringBuffer forreturn = new StringBuffer("Hotel Floor(");
        forreturn.append(getStars() + ", ");
        forreturn.append(getFloorSize() + ", ");
        for(int i = 0; i < getFloorSize(); i++){
            if(i == getFloorSize() - 1)
            {
                forreturn.append(getSpace(i).toString());
                break;
            }
            forreturn.append(getSpace(i).toString() + ", ");
        }
        forreturn.append(")");
        return forreturn.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof HotelFloor){
            if(getFloorSize()!=((HotelFloor) object).getFloorSize())
                return false;
            for(int i = 0; i < getFloorSize(); i++){
                if(!getSpace(i).equals(((HotelFloor) object).getSpace(i)))
                    return false;
            }
            if(getStars() != ((HotelFloor) object).getStars())
                return false;
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorSize(); ++i){
            result += getFloorSize() ^ getSpace(i).hashCode() ^ getStars();
        }
        return result;
    }

    //КЛОНИРОВАНИЕ
}
