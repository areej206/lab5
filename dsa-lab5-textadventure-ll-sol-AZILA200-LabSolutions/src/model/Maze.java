package model;

public class Maze {
    /*
    An array to hold the combined value of rolling two dice a set number of times
     */
    private Room [][] theMaze;

    /**
     * This method is the default constructor for the data set array
     * @param rows declares the number of rows in the array.
     * @param cols declares the number of columns in the array.
     */
    public Maze(int rows, int cols) {
        this.theMaze = new Room[rows][cols];
    }

    public int getDataSetSize(){
        return this.theMaze.length;
    }

    public void addToDataSet (int row, int col, Room aValue){
        this.theMaze[row][col] = aValue;
    }

    public Room getValueFromDataSet(int row, int col){
        return this.theMaze[row][col];
    }
}
