package buildings.net.server.sequental;

import buildings.dwelling.DwellingFactory;
import buildings.dwelling.HotelFactory;
import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.officeBuildings.OfficeBuilding;
import buildings.officeBuildings.OfficeFactory;
import lab4.Buildings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {

    public static double calculatePrice(Building building) throws BuildingUnderArrestException {
        if(arrestedBuilding(building)) throw new BuildingUnderArrestException();
        double squarePrice;
        double square = building.getSquareAmount();
        double result;
        if (building instanceof Hotel){
            squarePrice = 2000;
        }
        else if (building instanceof OfficeBuilding){
            squarePrice = 1500;
        }else{
            squarePrice = 1000;
        }
        result = squarePrice*square;
        return result;
    }

    public static boolean arrestedBuilding(Building building){
        int chance = (int)(Math.random()*10);
        if(chance > 8){
            return true;
        }else return false;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000)){

            System.out.println("Server started!");

            while(true){
                try(
                        Socket socket = server.accept();
                        DataOutputStream dos =
                                new DataOutputStream(
                                        socket.getOutputStream());

                        DataInputStream dis =
                                new DataInputStream(
                                        socket.getInputStream());

                ) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    String type;
                    Building building;
                    Double price;
                    String result;

                    while (!(type = dis.readUTF()).equals("Exit")) {
                        System.out.println("\n===============================\n" +
                                "\tRequest type: " + type +
                                "\n===============================\n");
                        switch (type) {
                            case "Dwelling":
                                Buildings.setBuildingFactory(new DwellingFactory());
                                break;
                            case "OfficeBuilding":
                                Buildings.setBuildingFactory(new OfficeFactory());
                                break;
                            case "Hotel":
                                Buildings.setBuildingFactory(new HotelFactory());
                                break;
                        }


                        building = Buildings.inputBuilding(dis);
                        try {


                            System.out.println("\n===============================\n" +
                                    "\tRequest Building:" +
                                    "\n===============================\n"
                                    + building);
                            price = calculatePrice(building);
                            System.out.println("Price of Building: " + price);
                            result = (price.toString());

                        } catch (BuildingUnderArrestException e){
                            result = "Building is Arrested!";
                        }
                        dos.writeUTF(result);
                        dos.flush();
                        System.out.println("\n------------------------------------------------------------------------------------");
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
