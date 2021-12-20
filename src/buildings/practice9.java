package buildings;

import buildings.dwelling.DwellingFactory;
import buildings.dwelling.HotelFactory;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.officeBuildings.OfficeFactory;
import lab4.Buildings;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class practice9 {

    static JFrame jFrame = getJFrame();

    //Panels
    static  JPanel rootPanel = new JPanel();
    static JPanel jPanel1 = new JPanel();
    static JPanel jPanel2 = new JPanel();
    static JPanel jPanel3 = new JPanel();
    static JPanel jPanel4 = new JPanel();

    //Labels
    static Label buildingType = new Label("Building Type: ");
    static Label buildingFloors = new Label("Number of Floors: ");
    static Label buildingSquare = new Label("General Square: ");


    static Building building;

    public static void main(String[] args) {
        jFrame.add(rootPanel);
        panelSettings();

        jFrame.setJMenuBar(getMenuBar());
        jFrame.revalidate();
    }
    /*static File filename;
    static JFileChooser fileChooser = new JFileChooser();
    public static void main(String[] args) {
        JFrame mainWindow = mainFrame();
        JPanel jPanel = new JPanel();
        setMenu(mainWindow, jPanel);
    }*/


    static JFrame getJFrame() {
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = 800;
        int height = 600;
        jFrame.setBounds(dimension.width/2 - width/2, dimension.height/2 - height/2, width, height);

        jFrame.setTitle("Practice 9");

        return jFrame;
    }

    static void updatePlanPanel(){
        //int spaceCount = 0;
        rootPanel.removeAll();
        jPanel4.removeAll();
        updatePanel1();
        System.out.println("Update Plan");
        for(int i = 0; i < building.getFloorsAmount(); ++i){
            JPanel floorPanel = new JPanel();
            floorPanel.setBorder(BorderFactory.createEtchedBorder());
            floorPanel.add(new Label("Floor #" + i));
            int finalI1 = i;
//            floorPanel.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    updatePanel2(building.getFloor(finalI1), finalI1);
//                }
//            });
            for(int j = 0; j < building.getFloor(i).getFloorSize(); ++j){
                JButton jButton = new JButton("Space #" + j);
                int finalI = i;
                int finalJ = j;
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updatePanel2(building.getFloor(finalI1), finalI1);
                        updatePanel3(building.getFloor(finalI).getSpace(finalJ), finalJ);
                    }
                });
                floorPanel.add(jButton);
            }
            //spaceCount += building.getFloor(i).getSpacesNum();
            jPanel4.add(floorPanel);
        }
        //updatePanel1();
        JScrollPane scrollPane = new JScrollPane(jPanel4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootPanel.add(scrollPane);
        rootPanel.add(jPanel1);
        rootPanel.add(jPanel2);
        rootPanel.add(jPanel3);
        jFrame.revalidate();
    }


    static void updatePanel1(){
        jPanel1.removeAll();

        jPanel1.add(new Label("Building", 1));

        buildingType.setText("Building Type: " + building.getClass().getSimpleName());
        buildingFloors.setText("Number of Floors: " + building.getFloorsAmount());
        buildingSquare.setText("General Square: " + building.getSquareAmount());

        jPanel1.add(buildingType);
        jPanel1.add(buildingFloors);
        jPanel1.add(buildingSquare);

        jPanel1.repaint();
        jPanel1.revalidate();
    }

    static void updatePanel2(Floor floor, int num){
        jPanel2.removeAll();

        jPanel2.add(new Label("Floor", 1));
        jPanel2.add(new Label("Floor Number: " + num));
        jPanel2.add(new Label("Spaces Number: " + floor.getFloorSize()));
        jPanel2.add(new Label("General Square: " + floor.getSquareAmount()));

        jPanel2.repaint();
        jPanel2.revalidate();
    }

    static void updatePanel3(Space space, int num){
        jPanel3.removeAll();

        jPanel3.add(new Label("Space",1 ));

        jPanel3.add(new Label("Space Number: " + num));
        jPanel3.add(new Label("Rooms Number: " + space.getRoomsAmount()));
        jPanel3.add(new Label("Square: " + space.getSquare()));

        jPanel3.repaint();
        jPanel3.revalidate();
    }

    static void panelSettings(){

        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.Y_AXIS));
        jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.Y_AXIS));
        jPanel3.setLayout(new BoxLayout(jPanel3,BoxLayout.Y_AXIS));
        jPanel4.setLayout(new BoxLayout(jPanel4,BoxLayout.Y_AXIS));

        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel3.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setBorder(BorderFactory.createEtchedBorder());

        //1 Panel
        jPanel1.add(new Label("Building", 1));
        jPanel1.add(buildingType);
        jPanel1.add(buildingFloors);
        jPanel1.add(buildingSquare);

        //2 Panel
        jPanel2.add(new Label("Floor", 1));
        jPanel2.add(new Label("Floor Number: "));
        jPanel2.add(new Label("Spaces Number: "));
        jPanel2.add(new Label("General Square: "));

        //3 Panel
        jPanel3.add(new Label("Space", 1));
        jPanel3.add(new Label("Space Number: "));
        jPanel3.add(new Label("Rooms Number: "));
        jPanel3.add(new Label("Square: "));

        jPanel4.add(new Label("Building Plane: "));
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
        rootPanel.add(jPanel1);
        rootPanel.add(jPanel2);
        rootPanel.add(jPanel3);
        rootPanel.add(jPanel4);
    }

    static JMenuBar getMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu lookAndFeel = new JMenu("Look&Feel");
        jMenuBar.add(file);
        jMenuBar.add(lookAndFeel);

        //FILE MENU
        JMenuItem dwelling = new JMenuItem("Open Dwelling");
        JMenuItem office = new JMenuItem("Open Office Building");
        JMenuItem hotel = new JMenuItem("Open Hotel");

        ButtonGroup group = new ButtonGroup();

        for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lookAndFeelInfo.getName());

            item.addActionListener(click -> {
                try {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    rootPanel.repaint();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            });
            group.add(item);
            lookAndFeel.add(item);
        }

        file.add(dwelling).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.addSeparator();
        file.add(office).addActionListener(new OpenBuilding(new OfficeFactory()));
        file.addSeparator();
        file.add(hotel).addActionListener(new OpenBuilding(new HotelFactory()));
        file.addSeparator();
        file.add(new JMenuItem("Exit")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return jMenuBar;
    }

    static class OpenBuilding extends AbstractAction{

        private final BuildingFactory factory;

        OpenBuilding(BuildingFactory factory) {
            this.factory = factory;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File(("D:\\code project (.java)\\NetcrackerPractice\\src\\buildings")));
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if(f.getName().endsWith("txt")){
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return "TXT Files only";
                }
            });
            jFileChooser.showOpenDialog(jFrame);
            File file = jFileChooser.getSelectedFile();
            Buildings.setBuildingFactory(factory);
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            }catch (IOException | NumberFormatException | FloorIndexOutOfBoundsException fileNotFoundException){
                JOptionPane.showMessageDialog(rootPanel,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                fileNotFoundException.printStackTrace();
            }
            building = Buildings.readBuilding(fr);
            if(building == null){
                JOptionPane.showMessageDialog(rootPanel,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            //building = Buildings.readBuilding(fr);
            System.out.println("Read complete");

            updatePlanPanel();
            updatePanel1();
        }
    }
}