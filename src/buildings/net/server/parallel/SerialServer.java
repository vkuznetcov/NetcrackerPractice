package buildings.net.server.parallel;

import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.officeBuildings.OfficeBuilding;
import lab4.Buildings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static double priceCheck(Building building) throws BuildingUnderArrestException {
        if (arrestedBuilding(building))
            throw new BuildingUnderArrestException();

        double squarePrice;
        double square = building.getSquareAmount();
        if (building instanceof Hotel) {
            squarePrice = 2000;
        } else if (building instanceof OfficeBuilding) {
            squarePrice = 1500;
        } else {
            squarePrice = 1000;
        }

        double result = square * squarePrice;
        return result;
    }

    public static boolean arrestedBuilding(Building building) {

        int chance = (int) (Math.random() * 10);
        if (chance > 8) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(1234)) {

            System.out.println("Server started");

            while (true) {
                Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (
                                DataInputStream dis = new DataInputStream(socket.getInputStream());
                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        ) {
                            Building building;
                            while ((dis.readInt()) == 1){
                                building = Buildings.deserializeBuilding(dis);
                                Object price;
                                try {
                                    System.out.println("Building: " + building);
                                    price = priceCheck(building);
                                    System.out.println("Price:" + price);
                                    new ObjectOutputStream(dos).writeObject(price);
                                }
                                catch (BuildingUnderArrestException e){
                                    new ObjectOutputStream(dos).writeObject(new BuildingUnderArrestException());
                                }
                                dos.flush();
                                System.out.println("\n---------------------------------------------------------------------------");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
