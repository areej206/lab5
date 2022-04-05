package datastructures;

/**
 *
 * @author rla
 */
public class ADTSingleLinkedList<E> implements IADTLinkedList<E> {
    private listNode<E> head; // This will be null if empty or point to the top of the list
    private int listSize;      // This will maintain the size of the list

    // Constructor
    public ADTSingleLinkedList(){
        this.head = null;
        this.listSize = 0;
    }
    @Override
    public int length (){
        return this.listSize;
    }
    @Override
    public void createList ( ){
        this.head = null;
        this.listSize = 0;
    }
    @Override
    public void printList ( ){
        int count = 0;
        listNode<E> tmp = this.head;
        while (tmp != null) {
            count++;
            System.out.println( String.format("(%d) ==> %s ",count,tmp.getNodeData().toString()));
            tmp = tmp.getNextNode();
        }
    }

    @Override
    public listNode<E> front() {
        return this.head;
    }

    @Override
    public void insert ( E theNode ){
        listNode<E> tmp = new listNode<E>();
        tmp.setNodeData(theNode);
        tmp.setNextNode(this.head);
        this.head = tmp;
        this.listSize++;
    }
    @Override
    public void remove ( E theNode ){
        listNode previousNode = null;
        listNode currentNode = this.head;
        while ( currentNode != null ) {
            if (theNode.equals(currentNode.getNodeData())) {
                // found the node that needs to be removed
                if (previousNode == null) {
                    // must be removing the first node in the list
                    this.head = currentNode.getNextNode();
                }
                else {
                    // removing a node that’s not first in list
                    previousNode.setNextNode(currentNode.getNextNode());
                }
                System.out.println("Removing item ...");
                this.listSize--;
            }
            else {

                previousNode = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
    }
    @Override
    public boolean find ( E theNode ){
        listNode<E> tmp = this.head;
        while ( tmp != null ) {
            if (theNode.equals(tmp.getNodeData())) {
                return true;
            }
            tmp = tmp.getNextNode();
        }
        return false;
    }

    @Override
    public listNode<E> find(String theLastName) {
        listNode<E> tmp = this.head;
        String theData = "";
        String[] tokens;
        while ( tmp != null ) {
            theData = tmp.getNodeData().toString();
            tokens = theData.split(",");
            if (theLastName.equals(tokens[2])) {
                return tmp;
            }
            tmp = tmp.getNextNode();
        }
        return null;
    }

}
