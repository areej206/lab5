package controllers;

import daos.mazeDAOImpl;
import datastructures.ADTSingleLinkedList;
import helpers.InputHelper;
import helpers.TextColours;
import model.Direction;
import model.PickUps;
import view.collectableListView;
import view.roomView;

public class controller {
    private final mazeDAOImpl theMazeDAO;
    private final roomView aRoomView;
    private final PickUps aPickUp;
    private ADTSingleLinkedList<PickUps> listOfPickUps;
    private final collectableListView aCollectableView;
    private Direction theDirection;
    private final InputHelper inputHelper;
    private int row = 0, col = 0;

    public controller() {
        this.theMazeDAO = new mazeDAOImpl(8,8);
        this.aRoomView = new roomView();
        this.aPickUp = new PickUps();
        this.listOfPickUps = new ADTSingleLinkedList<PickUps>();
        this.aCollectableView = new collectableListView();
        this.theDirection = Direction.SOUTH;
        this.inputHelper = new InputHelper();
    }

    public controller(mazeDAOImpl theMaze, ADTSingleLinkedList<PickUps> listOfPickUps, Direction theDirection) {
        this.theMazeDAO = theMaze;
        this.aRoomView = new roomView();
        this.aPickUp = new PickUps();
        this.listOfPickUps = listOfPickUps;
        this.aCollectableView = new collectableListView();
        this.theDirection = theDirection;
        this.inputHelper = new InputHelper();
    }

    /**
     * Displays the menu and uses an InputHelper object
     * to accept valid user choice.
     * An appropriate private method is called to implement the choice.
     */
    public void run() {
        boolean quit = false;
        boolean finished = false;

        char cChoice = 'N';
        int iChoice = 0;
        setup();

        System.out.println();

        do {
            theMenu();
            iChoice = inputHelper.readInt("Please select a menu option 1,2,3 or 4",4,1);
            switch (iChoice) {
                case 1:
                    theInstructions();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Thus, the adventure begins ...");
                    System.out.println();
                    this.aRoomView.showRoom(this.theMazeDAO.getRoom(row,col));
                    do{
                        cChoice = inputHelper.readCharacter("Enter direction - Valid characters are N,S,E,W or Q for Quit.", "N,S,E,W,Q");
                        switch (cChoice) {
                            case 'N':
                                System.out.println("Heading North ...");
                                System.out.println();
                                headingNorth();
                                break;
                            case 'S':
                                System.out.println("Heading South ...");
                                System.out.println();
                                headingSouth();
                                break;
                            case 'E':
                                System.out.println("Heading East ...");
                                System.out.println();
                                headingEast();
                                break;
                            case 'W':
                                System.out.println("Heading West ...");
                                System.out.println();
                                headingWest();
                                break;
                            case 'Q':
                                System.out.println("Thanks for playing ...");
                                System.out.println();
                                finished = true;
                                break;
                            default: // invalid option
                                System.out.println("Oops! Something has went wrong!");
                                break;
                        }
                        if(this.listOfPickUps.length() == 10){
                            finished = true;
                        }


                    } while (!finished);
                    break;
                case 3:
                    System.out.println("Heading East ...");
                    System.out.println();
                    showCollectables();
                    break;
                case 4:
                    System.out.println("Thanks for playing ...");
                    System.out.println();
                    quit = true;
                    break;
                default: // invalid option
                    System.out.println("Oops! Something has went wrong!");
                    break;
            }

        } while (!quit);
        theMazeDAO.writeCollectablesToFile(this.listOfPickUps);
        theMazeDAO.writeCurrentMazeStateToFile();
    }

    private void setup(){
        System.out.println("Setting up Game ...");
        this.theMazeDAO.loadFromFile();
    }

    private void theMenu() {
        // Print menu to console
        System.out.println(TextColours.TEXT_GREEN + "Welcome to the Coders Guild");
        System.out.println("---------------------------" + TextColours.TEXT_RESET);
        System.out.println("1: Instructions");
        System.out.println("2: Play");
        System.out.println("3: Show Collectables");
        System.out.println("4: Quit");
        System.out.println();
    }

    private void theInstructions(){
        System.out.println("The Coders Guild has had their " + TextColours.TEXT_RED + "ten" + TextColours.TEXT_RESET + " most prized coding assets stolen!");
        System.out.println("Your challenge is to enter the lair of the evil Codezilla and recover the assets.");
        System.out.println("You can only travel in the four directions: - North (N), South (S), East (E) or West (W).");
        System.out.println("Be prepared for the unexpected and good luck!!!");
    }

    private void headingNorth(){
        theDirection = Direction.NORTH;
        row --;
        this.aRoomView.showRoom(this.theMazeDAO.getRoom(row,col));
        if(this.theMazeDAO.getRoom(row,col).getaPickup().getPickupID() > 0){
            this.listOfPickUps.insert(this.theMazeDAO.getRoom(row,col).getaPickup());
            // Remove pickup from current location
            this.aPickUp.setPickupID(0);
            this.aPickUp.setPickup("Nothing here!");
            this.theMazeDAO.getRoom(row,col).setaPickup(this.aPickUp);
        }
    }

    private void headingSouth(){
        theDirection = Direction.SOUTH;
        row ++;
        this.aRoomView.showRoom(this.theMazeDAO.getRoom(row,col));
        if(this.theMazeDAO.getRoom(row,col).getaPickup().getPickupID() > 0){
            this.listOfPickUps.insert(this.theMazeDAO.getRoom(row,col).getaPickup());
            // Remove pickup from current location
            this.aPickUp.setPickupID(0);
            this.aPickUp.setPickup("Nothing here!");
            this.theMazeDAO.getRoom(row,col).setaPickup(this.aPickUp);
        }
    }

    private void headingEast(){
        theDirection = Direction.EAST;
        col ++;
        this.aRoomView.showRoom(this.theMazeDAO.getRoom(row,col));
        if(this.theMazeDAO.getRoom(row,col).getaPickup().getPickupID() > 0){
            this.listOfPickUps.insert(this.theMazeDAO.getRoom(row,col).getaPickup());
            // Remove pickup from current location
            this.aPickUp.setPickupID(0);
            this.aPickUp.setPickup("Nothing here!");
            this.theMazeDAO.getRoom(row,col).setaPickup(this.aPickUp);
        }
    }

    private void headingWest(){
        theDirection = Direction.WEST;
        col --;
        this.aRoomView.showRoom(this.theMazeDAO.getRoom(row,col));
        if(this.theMazeDAO.getRoom(row,col).getaPickup().getPickupID() > 0){
            this.listOfPickUps.insert(this.theMazeDAO.getRoom(row,col).getaPickup());
            // Remove pickup from current location
            this.aPickUp.setPickupID(0);
            this.aPickUp.setPickup("Nothing here!");
            this.theMazeDAO.getRoom(row,col).setaPickup(this.aPickUp);
        }
    }

    private void showCollectables(){
        System.out.println();
        System.out.println(TextColours.TEXT_GREEN + "Your Collectables");
        System.out.println("-----------------" + TextColours.TEXT_RESET);
        this.aCollectableView.displayAllPickups(this.listOfPickUps);
    }
}
