package daos;

import datastructures.ADTSingleLinkedList;
import model.PickUps;
import model.Room;

/**
 * An abstract Data Access Object class
 * which specifies the functionality required
 * to interact with a data store and implemented
 * in concrete subclasses
 */
public abstract class DAO {

    public abstract void loadFromFile();
    public abstract void writeCollectablesToFile(ADTSingleLinkedList<PickUps> collectedItems);
    public abstract void writeCurrentMazeStateToFile();
    public abstract Room getRoom(int row, int col);
    public abstract PickUps getPickUps(int row, int col);

}