import java.text.DecimalFormat;
import java.util.ArrayList;

public class Node {

    private ArrayList<Integer> vector = new ArrayList<>();
    private double distance;

    private ArrayList<Integer> base = new ArrayList<>();
    private City cities;

    Node(City cities) {
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
    private void createBase() {
        for (int i = 2; i < this.cities.getCities().size() + 1; i++) {
            base.add(i);
        }
    }

    private void createRoute() {
        // Primera ciudad siempre '1'
        vector.add(1);
        /* Creación de el recorrido de ciudades de manera aleatoria */
        while (base.size() > 0) {
            int index = (int) (Math.random() * base.size());
            Integer pulled = base.get(index);
            vector.add(pulled);
            base.remove(index);
        }
//        vector.addAll(base);
        // Última ciudad siempre '1'
        vector.add(1);
    }

    public void estimateLength() {
        this.distance = 0;
        for (int i = 0; i < vector.size() - 1; i++) {
            Coordenates city1 = cities.getCity(vector.get(i) - 1);
            Coordenates city2 = cities.getCity(vector.get(i + 1) - 1);

            double aux = distanceBetweenTwoCities(city1.getX(), city2.getX(), city1.getY(), city2.getY());
            this.distance += aux;
        }
    }

    private double distanceBetweenTwoCities(int x1, int x2, int y1, int y2) {
        return Math.sqrt((Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2)));
    }

    public void printVector() {
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

    public int getCity(int index) {
        return this.vector.get(index);
    }

    public void setCity (int index, int value){
        this.vector.set(index, value);
    }


    /* ------- METHODS FOR SECOND PART ------------------ */
    /*
     * Creating a son by fathers
     */
    Node(Node f1, int num1, Node f2, int num2, City cities) {
        this.cities = cities;
//        System.out.print("\n\nPadre " + num1 + ": ");
//        f1.printVector();

//        System.out.print("Padre " + num2 + ": ");
//        f2.printVector();

        initializeVectorInZeros();
        /* Seteamos dos primeros y dos últimos */
        this.vector.set(0, 1);
        this.vector.set(1, f1.getCity(1));
        this.vector.set(24, f1.getCity(24));
        this.vector.set(25, 1);

        int lastIndex = 1;
        int lastValue = f2.getCity(lastIndex);
        fillWithFather1(lastIndex, lastValue, f1, f2);
        fillWithFather2(f1, f2);
        estimateLength();

//        System.out.print("\tHijo: ");
//        printVector();
    }

    private void initializeVectorInZeros() {
        for (int i = 0; i < 26; i++) {
            this.vector.add(0);
        }
    }

    private void fillWithFather1(int lastIndex, int lastValue, Node f1, Node f2) {
        ArrayList<Integer> existing = new ArrayList<>(); // Ciudades existentes
        existing.add(f1.getCity(1));    // Agregamos segundo y penúltimo elemento. El '1' igual no saldrá
        existing.add(f1.getCity(24));   // dentro de las busquedas.
        boolean band = false;   // Condición de paro
        int newValue;
        do {
            lastIndex = searchIndex(lastValue, f1);
            newValue = f1.getCity(lastIndex);
            if (existing.contains(newValue)) {
                band = true;
//                System.out.println("Arreglo de existentes: " + existing.toString());
//                System.out.print("Pertenencientes a 1° Padre: ");
//                printVector();
            } else {
                this.vector.set(lastIndex, newValue);
                lastValue = f2.getCity(lastIndex);
                existing.add(newValue);
            }
        } while (!band);
    }

    private void fillWithFather2(Node f1, Node f2) {
        for (int i = 2; i < this.vector.size() - 2; i++) {
            if (this.vector.get(i) == 0){
                if ((f2.getCity(i) == f1.getCity(24)) || (f2.getCity(i) == f1.getCity(1))){
                    this.vector.set(i, f2.getCity(24));
                } else {
                    this.vector.set(i, f2.getCity(i));
                }
            }
        }
    }

    /* Búsqueda de indice por un valor */
    private int searchIndex(int value, Node f1) {
//        System.out.print("Buscando: " + value);
        int i = 0;
        while (f1.getCity(i) != value) {
            i++;
        }
//        System.out.println(", Encontrado en i=" + i);
        return i;
    }
}
