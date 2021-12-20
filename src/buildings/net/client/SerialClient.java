package buildings.net.client;

import buildings.dwelling.DwellingFactory;
import buildings.dwelling.HotelFactory;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.officeBuildings.OfficeFactory;
import lab4.Buildings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 1234);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            Building building;
            String type;

            String path = "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\resultSerial.txt";

            String buildingsInfo = "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\BuildingsInfo.txt";
            String buildingsTypes = "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\BuildingsTypes.txt";

            FileWriter writer = new FileWriter(new File(path));
            Scanner scanner = new Scanner(new FileReader(buildingsTypes));
            BufferedReader reader = new BufferedReader(new FileReader(buildingsInfo));

            Object price;

            while (scanner.hasNext()){
                type = scanner.nextLine();
                System.out.println(type);

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

                dos.writeInt(1);
                building = Buildings.readBuilding(reader);
                System.out.println(building);
                Buildings.serializeBuilding(building, dos);

                price = new ObjectInputStream(dis).readObject();

                if(price instanceof Double){
                    System.out.println("Price: " + price + "\n");
                    writer.write(price + "\n");
                }

                if (price instanceof BuildingUnderArrestException){
                    System.out.println("Building is arrested!\n");
                    writer.write("Building is arrested!\n");
                }
            }

            dos.writeInt(0);
            writer.close();
            dos.flush();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
