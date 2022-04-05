package view;

import helpers.OutputHelper;
import helpers.TextColours;
import model.PickUps;
import datastructures.ADTSingleLinkedList;
import datastructures.listNode;

public class collectableListView {
    public void displayCollectable(PickUps aPickup){
        System.out.println("You have pickup number " + TextColours.TEXT_PURPLE + + aPickup.getPickupID() + " " + aPickup.getPickup() + TextColours.TEXT_RESET);
    }

    public void displayAllPickups(ADTSingleLinkedList<PickUps> allPickups){
        System.out.println("All Pickups Collected");
        System.out.println("---------------------");
        System.out.println(OutputHelper.repeat("-",62));
        System.out.format("| %-5s | %-50s |\n", "ID", "Description");
        System.out.println(OutputHelper.repeat("-",62));

        listNode<PickUps> tmp = allPickups.front();
        while (tmp != null) {
            System.out.format("| %5d | %-50s |\n", tmp.getNodeData().getPickupID(), tmp.getNodeData().getPickup());
            tmp = tmp.getNextNode();
        }
        System.out.println(OutputHelper.repeat("-",62));
    }
}
