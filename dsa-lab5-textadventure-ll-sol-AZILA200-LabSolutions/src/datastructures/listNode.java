package datastructures;

/**
 *
 * @author rla
 */
public class listNode<E> {
    /**
     * This is the data we want to store,
     * It can be of any type
     */
    private E nodeData;   // Data we want to store (could be int, string, Object etc)
    /**
     * This will hold the pointer to the next node in the list
     */
    private listNode nextNode;  // A "pointer" to next node in the list

    public listNode (){
        this.nodeData = null;
        this.nextNode = null;
    }

    public listNode(E nodeData, listNode nextNode) {
        this.nodeData = nodeData;
        this.nextNode = nextNode;
    }

    public E getNodeData() {
        return nodeData;
    }

    public void setNodeData(E nodeData) {
        this.nodeData = nodeData;
    }

    public listNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(listNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString(){
        return this.nodeData.toString();
    }
}
