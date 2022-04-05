package datastructures;
/**
 *
 * @author rla
 */
public interface IADTLinkedList <E>{
    int length ();
    void createList ( );
    void printList ( );
    listNode<E> front();
    void insert ( E theNode );
    void remove ( E theNode );
    boolean find ( E theNode );
    listNode<E> find( String theLastName);
}
