package monopoly;

public class MonopolyETSE {

    public static void main(String[] args) {
        System.out.println("Bienvenido a MonopolyETSE.");

        Menu menu = new Menu(); // Aquí podrías inicializar el juego
        //menu.iniciarPartida(); // Método que maneja el bucle principal
        menu.tablero.toString();
    }

    /*private void lecturaFichero(String fichero) {
        try{
            File f= new File(fichero);
            Scanner sc = new Scanner(f);
            sc.hasNextLine();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        }
    }*/
    
}
