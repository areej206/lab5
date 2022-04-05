package daos;

import app.TextAdventureApp;
import datastructures.ADTSingleLinkedList;
import datastructures.listNode;
import model.Maze;
import model.PickUps;
import model.Room;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mazeDAOImpl extends DAO{
    private Maze theMaze;
    public static final char DELIMITER = ',';
    public static final char EOLN='\n';
    public static final String QUOTE="\"";
    public static final String USERDIRECTORY = System.getProperty("user.dir");

    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }

    public mazeDAOImpl(int row, int col){
        this.theMaze = new Maze(row,col);
    }

    public mazeDAOImpl(Maze theMaze) {
        this.theMaze = theMaze;
    }

    public Maze getTheMaze() {
        return theMaze;
    }

    public void setTheMaze(Maze theMaze) {
        this.theMaze = theMaze;
    }

    @Override
    public void loadFromFile() {
        String transactionFile = USERDIRECTORY +"\\TheCodersGuild.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            String roomDescription;
            String roomDirection;
            int pickUpID;
            String pickUp;

            int count = 0, row = 0, col = 0;

            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));
                roomDescription = temp[0];
                roomDirection = temp[1];
                pickUpID = Integer.parseInt(temp[2]);
                pickUp = temp[3];
                Room theRoom = new Room();
                theRoom.setDescription(roomDescription);
                String delims = ":"; // Use this delimiter to split the string.
                String[] tokens = roomDirection.split(delims); // Create a string array for the three time values
                theRoom.setPossibleMoves(theRoom.convertToDirection(tokens));
                PickUps thePickup = new PickUps(pickUpID,pickUp);
                theRoom.setaPickup(thePickup);
                theMaze.addToDataSet(row, col, theRoom);

                line = br.readLine();
                count++;

                if (count%8 == 0) {
                    row ++;
                    col = 0;
                }
                else {
                    col++;
                }

            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(TextAdventureApp.class.getName()).log(Level.INFO, null, ex);
        }
    }

    @Override
    public void writeCollectablesToFile(ADTSingleLinkedList<PickUps> collectedItems) {
        try {
            File myFile = new File(USERDIRECTORY +"\\collectedItems.txt");

            if(myFile.exists()){
                myFile.delete();
                System.out.println("File deleted: " + myFile.getName());
            }
            else if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myFile = new File(USERDIRECTORY +"\\collectedItems.txt");
            FileWriter myWriter = new FileWriter(myFile.getName());
            listNode<PickUps> tmp = collectedItems.front();
            while (tmp != null) {
                if(tmp.getNextNode() == null){
                    myWriter.write(tmp.getNodeData().CSVFormat());
                }
                else {
                    myWriter.write(tmp.getNodeData().CSVFormat() + "\n");
                }
                tmp = tmp.getNextNode();
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeCurrentMazeStateToFile() {
        try {
            File myFile = new File(USERDIRECTORY +"\\currentMazeState.txt");

            if(myFile.exists()){
                myFile.delete();
                System.out.println("File deleted: " + myFile.getName());
            }
            else if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myFile = new File(USERDIRECTORY +"\\currentMazeState.txt");
            FileWriter myWriter = new FileWriter(myFile.getName());
            for(int row = 0; row < 8; row++){
                for(int col = 0; col < 8; col++){
                    if(row == 7 && col ==7){
                        myWriter.write(this.theMaze.getValueFromDataSet(row,col).CSVFormat());
                    }
                    else {
                        myWriter.write(this.theMaze.getValueFromDataSet(row,col).CSVFormat() + "\n");
                    }
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Room getRoom(int row, int col) {
        return theMaze.getValueFromDataSet(row,col);
    }

    @Override
    public PickUps getPickUps(int row, int col) {
        return theMaze.getValueFromDataSet(row,col).getaPickup();
    }
}
