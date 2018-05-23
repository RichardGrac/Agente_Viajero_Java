import java.text.DecimalFormat;
import java.util.ArrayList;

public class Node{

    private ArrayList<Integer> vector = new ArrayList<>();
    private double distance;

    private ArrayList<Integer> base = new ArrayList<>();
    private City cities;

    Node(City cities){
        this.cities = cities;
        createBase();
        createRoute();
        estimateLength();
    }

    /*
    *  Creamos la estructura base de elementos ordenados
    *  i = 2 porque '1' lo agregarémos manualmente a 'vector'
    *
    * */
    private void createBase(){
        for (int i = 2; i < this.cities.getCities().size() + 1; i++){
            base.add(i);
        }
    }

    private void createRoute() {
        // Primera ciudad siempre '1'
        vector.add(1);
        /* Creación de el recorrido de ciudades de manera aleatoria */
        while(base.size() > 0){
            int index = (int)(Math.random() * base.size());
            Integer pulled = base.get(index);
            vector.add(pulled);
            base.remove(index);
        }
//        vector.addAll(base);
        // Última ciudad siempre '1'
        vector.add(1);
    }

    private void estimateLength() {
        for (int i = 0; i < vector.size() - 1; i++) {
            Coordenates city1 = cities.getCity(vector.get(i) - 1);
            Coordenates city2 = cities.getCity(vector.get(i+1) - 1);

            double aux = distanceBetweenTwoCities(city1.getX(), city2.getX(), city1.getY(), city2.getY());
            this.distance += aux;
        }
    }

    private double distanceBetweenTwoCities(int x1, int x2, int y1, int y2){
        return Math.sqrt((Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2)));
    }

    public void printVector(){
        System.out.print("[ ");
        for (int i = 0; i < this.vector.size(); i++) {
            System.out.print(vector.get(i) + " ");
        }

        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("] - Distance: " + f.format(this.distance) + " Unidades");
    }

    public double getDistance() {
        return distance;
    }
}
