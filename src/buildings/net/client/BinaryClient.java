package buildings.net.client;

import buildings.interfaces.Building;
import lab4.Buildings;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                DataOutputStream dos =
                        new DataOutputStream(
                                socket.getOutputStream());

                DataInputStream dis =
                        new DataInputStream(
                                socket.getInputStream());
        ){

            String type;
            Building building;
            String result;
            String path = "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\result.txt";
            FileWriter writer = new FileWriter(new File(path));
            Scanner scanner = new Scanner(new FileReader(
                    "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\BuildingsTypes.txt"));
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            "D:\\code project (.java)\\NetcrackerPractice\\src\\buildings\\BuildingsInfo.txt"));
            while(scanner.hasNext()){

                type = scanner.nextLine();
                System.out.println(type);
                dos.writeUTF(type);
                building = Buildings.readBuilding(reader);
                Buildings.outputBuilding(building, dos);
                result = dis.readUTF();
                writer.write(result + "\n");
                System.out.println(result);
            }

            writer.close();
            scanner.close();
            reader.close();
            dos.writeUTF("Exit");
            System.out.println("Exit");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
