import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class City {

    ArrayList<Coordenates> cities = new ArrayList<>();

    City() {
        readCitiesTxt();
        printCities();
    }

    public ArrayList<Coordenates> getCities() {
        return cities;
    }

    public Coordenates getCity(int index){
        return this.cities.get(index);
    }

    private void readCitiesTxt() {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;

        /* Leemos fichero con las coordenadas de cada ciudad */
        try {
            file = new File("src/coordenates.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                /* Para cada linea creamos una ciudad con sus coordenadas */
                String []coords = line.split(" ");
                this.cities.add(new Coordenates(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /* Cerramos fichero */
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void printCities() {
        System.out.print("Coordenadas: ");
        for (int i = 0; i < cities.size(); i++) {
            System.out.print("(" + cities.get(i).getX() + "," + cities.get(i).getY() + ") ");
        }
        System.out.println();
    }

}

class Coordenates {
    private int x;
    private int y;

    Coordenates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
