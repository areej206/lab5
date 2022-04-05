package view;

import helpers.TextColours;
import model.Direction;
import model.Room;

import java.util.Arrays;

public class roomView {
    public void showRoom(Room aRoom){
        System.out.println("You are in the " + TextColours.TEXT_PURPLE + aRoom.getDescription() + TextColours.TEXT_RESET);
        if(aRoom.getaPickup().getPickupID() > 0){
            System.out.print("You have found ... ");
            System.out.println(TextColours.TEXT_CYAN + aRoom.getaPickup().getPickup()+ TextColours.TEXT_RESET);
        }
        else{
            System.out.println(aRoom.getaPickup().getPickup());
        }
        // This code has been updated to produce the direction list using colour and excluding the null's
        Direction[] possibleDirections = new Direction[4]; // create a temporary array of Direction
        // Create a string array holding the colours chosen for the directions
        String [] dirColours = {TextColours.TEXT_YELLOW, TextColours.TEXT_BLUE, TextColours.TEXT_PURPLE,TextColours.TEXT_GREEN};
        int count = 0; // Use this variable as a way to step through the dirColours array
        String dirOut = ""; // Use this to create the directions output string
        possibleDirections = aRoom.getPossibleMoves(); // get the array of possible moves
        System.out.print("You can go in the following directions: [");
        // Use this loop to step through the list of directions
        for(Direction direction : possibleDirections){
            if(direction != null){
                //System.out.print(dirColours[count] + direction.toString() + ", ");
                // add each direction to the output string with the colour code and a comma
                dirOut += dirColours[count] + direction.toString() + ", ";
                count++; // increase count to access the next colour
            }
        }
        dirOut += "]"; // Add the closing bracket to the output string
        // Replace the ", ]" at the end of the output string with "]" before printing.
        System.out.print(dirOut.replace(", ]","") + TextColours.TEXT_RESET + "]\n");
    }
}
