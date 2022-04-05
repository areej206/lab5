package model;

import java.util.Arrays;

public class Room {
    private String description;
    private Direction[] possibleMoves;
    private PickUps aPickup;

    public Room() {
        this.description = "";
        this.possibleMoves = new Direction[4];
        this.aPickup = new PickUps();
    }

    public Room(String description, Direction[] possibleMoves, PickUps aPickup) {
        this.description = description;
        this.possibleMoves = possibleMoves;
        this.aPickup = aPickup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Direction[] getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(Direction[] possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public PickUps getaPickup() {
        return aPickup;
    }

    public void setaPickup(PickUps aPickup) {
        this.aPickup = aPickup;
    }

    public Direction[] convertToDirection(String[] directions){
        Direction[] possibleMoves = new Direction[4];
        int count = 0;
        for(String direction : directions){
            possibleMoves[count] = Direction.valueOf(direction);
            count++;
        }
        return possibleMoves;
    }

    public String convertDirectionToString(){
        String directions = "";
        for(int pos = 0; pos < 4; pos++){
            if(this.possibleMoves[pos] != null){
                switch(this.possibleMoves[pos]) {
                    case NORTH:
                        directions += Direction.NORTH.name();
                        break;
                    case SOUTH:
                        directions += Direction.SOUTH.name();
                        break;
                    case WEST:
                        directions += Direction.WEST.name();
                        break;
                    case EAST:
                        directions += Direction.EAST.name();
                        break;
                }
                directions += ":";
            }
            else {
                if(pos < 3){
                    directions += ":";
                }
            }
        }
        return directions;
    }

    public String CSVFormat(){
        String outputStr = this.description + "," + this.convertDirectionToString() + "," + this.aPickup.CSVFormat();
        return outputStr;
    }

    @Override
    public String toString() {
        return "Room{" +
                "description='" + description + '\'' +
                ", possibleMoves=" + Arrays.toString(possibleMoves) +
                ", aPickup=" + aPickup.toString() +
                '}';
    }
}
