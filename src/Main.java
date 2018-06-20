/*
* INTEGRANTES:
*   GARCÍA NAVARRO RICARDO DANIEL
*   GONZALEZ OLIVARES JOSUÉ
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static final int NUM_NODES = 100;

    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        City cities = new City();
        for (int i = 0; i < NUM_NODES; i++) {
            nodes.add(new Node(cities));
        }
        /* Array Sorted: */
//        nodes.sort(Comparator.comparingDouble(Node::getDistance));
//        printArray("Lista 1", nodes);

        /* 'nodes' best 50 results */
//        nodes.subList(50, 100).clear();
//        printArray("Mejores 50 [Lista 1]", nodes);

        System.out.println("GENERACIÓN \t CROMOSOMA \t\t CONTENIDO \t\t RECORRIDO");
        /* First Generation (Second List) */
        for(int i = 0; i < 100; i++){
            ArrayList<Node> nextGen = nextGeneration(nodes, cities);
            nextGen.sort(Comparator.comparingDouble(Node::getDistance));
//            printArray(("Generación #" + (i+1)), nextGen);

            /* 'nextGen' best 50 results */
            nextGen.subList(50,100).clear();
//            printArray("Mejores 50 [Lista " + (i+1) + "]", nextGen);

            /* Swap of 5° and 10° element */
            nextGen = swap(nextGen, 4, 9);
//            printArray("1° y 5° swapped", nextGen);
//            System.out.println("------------------------ Elementos 1 y 5 swapped en Primer Nodo [Gen " + i + "] -------------------");
            System.out.print("\t" + i + " \t\t " + (i+1) + "\t\t\t\t");
            nextGen.get(0).printVector();
            nodes = nextGen;
        }

    }


    public static void printArray(String message, ArrayList<Node> nodes) {
        System.out.println("------------------------------------- " + message + " ------------------------------------------");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print("[" + (i + 1) + "]: ");
            nodes.get(i).printVector();
        }
    }

    public static ArrayList<Node> nextGeneration(ArrayList<Node> nodes, City cities){
        ArrayList<Node> nodes2 = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < nodes.size() - 1; i = i + 2) {
            nodes2.add(new Node(nodes.get(i), (i + 1), nodes.get(i + j), (i + j + 1), cities));
        }

        while (j < 4) {
            j++;
            for (int i = 0; i < 25; i++) {
                nodes2.add(new Node(nodes.get(i), (i + 1), nodes.get(i + j), (i + j + 1), cities));
            }
        }
        return nodes2;
    }

    public static ArrayList<Node> swap(ArrayList<Node> nodes, int index_a, int index_b){
        Node firstNode = nodes.get(0);
        int fifthlement = firstNode.getCity(index_a);
        firstNode.setCity(index_a, firstNode.getCity(index_b));
        firstNode.setCity(index_b, fifthlement);
        firstNode.estimateLength();
        nodes.set(0, firstNode);
        return nodes;
    }
}
