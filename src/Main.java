import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static final int NUM_NODES = 100;

    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        City cities = new City();
        for (int i = 0; i < NUM_NODES; i++){
            nodes.add(new Node(cities));
        }
        /* Array Sorted: */
        Collections.sort(nodes, Comparator.comparingDouble(Node::getDistance));

        for (int i = 0; i < nodes.size(); i++) {
            System.out.print("[" + (i+1) + "]: ");
            nodes.get(i).printVector();
        }
    }
}
