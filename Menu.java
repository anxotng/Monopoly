package monopoly;

import java.io.File;
import java.util.ArrayList;
import partida.*;

import java.util.Scanner;

public class Menu {

    //Atributos
    private ArrayList<Jugador> jugadores; //Jugadores de la partida.
    private ArrayList<Avatar> avatares; //Avatares en la partida.
    private int turno; //Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; //Variable para contar el número de lanzamientos de un jugador en un turno.
    private Tablero tablero; //Tablero en el que se juega.
    private Dado dado1; //Dos dados para lanzar y avanzar casillas.
    private Dado dado2;
    private Jugador banca; //El jugador banca.
    private boolean tirado; //Booleano para comprobar si el jugador que tiene el turno ha tirado o no.
    private boolean solvente; //Booleano para comprobar si el jugador que tiene el turno es solvente, es decir, si ha pagado sus deudas.

    private boolean dadosDobles;
    private int vecesDoblesSacados;

    //Constructor
    public Menu() throws Exception { // el throws es para iniciarPartida
        this.jugadores = new ArrayList<Jugador>();
        this.avatares = new ArrayList<Avatar>();
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
        banca.setTablero(tablero);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        iniciarPartida();
    }

    // Getters y setters
    public ArrayList<Jugador> getJugadores() { 
        return jugadores;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public int getTurno() {
        return turno;
    }

    public int getLanzamientos() {
        return lanzamientos;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Dado getDado1() {
        return dado1;
    }

    public Dado getDado2() {
        return dado2;
    }

    public Jugador getBanca() {
        return banca;
    }

    public boolean isTirado() {
        return tirado;
    }

    public boolean isSolvente() {
        return solvente;
    }

    public boolean isDadosDobles(){
        return dadosDobles;
    }

    public int getVecesDobles(){
        return vecesDoblesSacados;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setLanzamientos(int lanzamientos) {
        this.lanzamientos = lanzamientos;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setDado1(Dado dado1) {
        this.dado1 = dado1;
    }

    public void setDado2(Dado dado2) {
        this.dado2 = dado2;
    }

    public void setTirado(boolean tirado) {
        this.tirado = tirado;
    }

    public void setSolvente(boolean solvente) {
        this.solvente = solvente;
    }

    public void setDadosDobles(boolean dadosDobles){
        this.dadosDobles = dadosDobles;
    }

    public void setVecesDobles(int vecesDoblesSacados){
        this.vecesDoblesSacados = vecesDoblesSacados;
    }



    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() throws Exception { // el throws es por el Scanner de fichero, ya que puede no encontrar el fichero
        System.out.println("Bienvenido a Monopoly!");

        File archivo = new File("comandos.txt");
        Scanner lector = new Scanner(archivo);

        while(lector.hasNextLine()) {
            String comando = lector.nextLine();
            analizarComando(comando);
        }
        lector.close();
        System.out.println("Fin de la lectura de comandos.");
    }
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    * Lo que analiza los comandos es un switch que llama a los métodos correspondientes a cada comando.
    * Los comandos se leen del fichero comandos.txt.
    */
    private void analizarComando(String comando) {
        //Imprimir el comando leído en negrita
        System.out.println("\n" + comando);

        String[] partes = comando.split(" "); // split para separar el comando en partes

        switch (partes[0]) {
            case "crear":
            case "Crear":
                switch (partes[1]) {
                    case "jugador":
                    case "Jugador":
                        if(partes.length < 4) {
                            System.out.println("Error: comando incompleto para crear jugador.");
                            return;
                        }
                        //Crear jugador con nombre y avatar añadiéndolo a la casilla de salida y a la lista de avatares disponibles
                        Jugador jugadorNuevo = new Jugador(partes[2], partes[3], tablero.getPosiciones().get(0).get(0), avatares);
                        jugadores.add(jugadorNuevo); 
                        jugadorNuevo.setTablero(tablero);


                        // Mostrar la información del jugador creado
                        System.out.println("{\n\tnombre: " + partes[2] + ",\n\tavatar: " + partes[3] + "\n}");

                        // Repintado del tablero
                        System.out.println(tablero.toString());
                        break;
                }
                break;

            
            case "describir": 
            case "Describir":
                if(partes.length < 2) {
                    System.out.println("Error: comando incompleto para describir.");
                    return;
                }

                if(partes.length == 3 && partes[1].equalsIgnoreCase("jugador")){
                    for(Jugador j : jugadores) {
                        if(partes[2].equalsIgnoreCase(j.getNombre())) {
                            descJugador(partes);
                            return;
                        }
                    }
                } else if(partes.length == 2){
                    for(Casilla c : tablero.getCasillas()) {
                        if(partes[1].equalsIgnoreCase(c.getNombre())) {
                            descCasilla(partes[1]);
                            return;
                        }
                    }
                }
                
                break;

            case "listar":
            case "Listar":

                if(partes.length < 2) {
                    System.out.println("Error: comando incompleto para listar.");
                    return;
                }

                switch (partes[1]) {
                    case "jugadores":
                    case "Jugadores":
                        this.listarJugadores();
                        break;

                    case "enventa":
                    case "Enventa":
                        listarVenta();
                        // Repintado del tablero
                        System.out.println(tablero.toString());
                        break;
                    default:
                        System.out.println("Comando no reconocido.");
                        break;
                }
                break;
                
            case "lanzar":
            case "Lanzar":
                if(!partes[1].equalsIgnoreCase("dados")) {
                    System.out.println("Comando no reconocido.");
                    return;
                }
                if(partes.length == 2) {
                    lanzarDados();
                } else if(partes.length == 3) {
                    // lanzar dados forzados
                    lanzarDadosForzados(partes);
                }
                // Repintado del tablero
                System.out.println(tablero.toString());
                break;

            case "jugador":
                System.out.println("{\n\tnombre: " + jugadores.get(turno).getNombre() + ",\n\tavatar: " + jugadores.get(turno).getAvatar().getId() + "\n}");
                break;

            case "comprar":
            case "Comprar":
                comprar(jugadores.get(turno), partes[1]);
                break;
            
            case "salir":
                salirCarcel();
                break;

            case "acabar":
                acabarTurno();
                break;
            
            case "ver":
                System.out.println(tablero.toString());
                break;

            default:
                System.out.println("Comando no reconocido.");
                break;
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] partes) {
        for(Jugador j : jugadores) { // 
            if(partes[2].equalsIgnoreCase(j.getNombre())) { //Comprueba con qué nombre coincide el jugador del archivo.
                System.out.println(j.toString());
                return;
            }
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String ID) {
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
        for(Casilla c : tablero.getCasillas()) {
            if(nombre.equalsIgnoreCase(c.getNombre())) {
                System.out.println(c.infoCasilla());
                return;
            }
        }
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {

        if (tirado == true) {
            System.out.println(jugadores.get(turno).getNombre() +", ya has lanzado los dados. Finaliza tu turno.");
            acabarTurno();
            return;
        }

        if(jugadores.get(turno).estaEnCarcel()){
            Dado dadoCarcel1 = new Dado();
            Dado dadoCarcel2 = new Dado();

            dadoCarcel1.setValorDado(dadoCarcel1.hacerTirada());
            dadoCarcel2.setValorDado(dadoCarcel2.hacerTirada());

            int valorDadoCarcel1 = dadoCarcel1.getValorDado();
            int valorDadoCarcel2 = dadoCarcel2.getValorDado();

            if(valorDadoCarcel1 == valorDadoCarcel2){
                // El jugador sale de la cárcel pero no se mueve
                jugadores.get(turno).setEnCarcel(false);
                jugadores.get(turno).setTiradasCarcel(0);
            }

            if(jugadores.get(turno).getTiradasCarcel() >= 3 && jugadores.get(turno).estaEnCarcel() == true){
                // Paga obligatoriamente los 500000 y sale de la cárcel
                jugadores.get(turno).sumarFortuna(-500000);
                System.out.println(jugadores.get(turno).getNombre() + " paga 500.000€ y sale de la cárcel. Puede lanzar los dados.");
                jugadores.get(turno).setEnCarcel(false);
                jugadores.get(turno).setTiradasCarcel(0);
            }

            jugadores.get(turno).setTiradasCarcel(jugadores.get(turno).getTiradasCarcel()+1);

            return;
        }

        vecesDoblesSacados = 0;
        dadosDobles = false;

        do {
            Dado dado1 = new Dado();
            Dado dado2 = new Dado();

            dado1.setValorDado(dado1.hacerTirada());
            dado2.setValorDado(dado2.hacerTirada());

            int valorDado1 = dado1.getValorDado();
            int valorDado2 = dado2.getValorDado();

            int valorTotal = valorDado1 + valorDado2;

            if(valorDado1 == valorDado2){
                dadosDobles = true;
                vecesDoblesSacados++;
            }

            if(dadosDobles == true && jugadores.get(turno).estaEnCarcel() == true){
                // Sale de la cárcel y se acaba el turno
                jugadores.get(turno).setEnCarcel(false);
            }

            if(vecesDoblesSacados >= 3){
                System.out.println("El jugador se va a la cárcel por sacar tres dobles seguidos.");
                jugadores.get(turno).encarcelar(tablero.getPosiciones());
            }

            Casilla posicionAntes = jugadores.get(turno).getAvatar().getLugar();

            // Avanzar el avatar del jugador en turno
            jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), valorTotal);

            Casilla posicionDespues = jugadores.get(turno).getAvatar().getLugar();

            switch(posicionDespues.getTipo().toLowerCase()){
                case "solar": 
                    // Si la posicionDespues tiene duenho, pagamos el alquiler
                    if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                        if(jugadores.get(turno).getFortuna() >= posicionDespues.getImpuesto()){ // si la fortuna del jugador es mayor o igual que el alquiler de la casilla en la que se cae
                            posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                            System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler.");
                        } else{
                            System.out.println("El jugador " + jugadores.get(turno).getNombre() + " no tiene suficiente dinero para pagar el alquiler de la casilla " + posicionDespues.getNombre() + ", así que puede(1) hipotecar alguna propiedad o (2) declararse en bancarrota");
                        }
                    } else{
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                    }
                    break;
                
                case "especial":
                    switch(posicionDespues.getNombre().toLowerCase()){
                        case "carcel":
                            break;
                        
                        case "parking":
                            jugadores.get(turno).sumarFortuna(tablero.getBoteParking());
                            System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". El jugador " + jugadores.get(turno).getNombre() + " recibe " + tablero.getBoteParking() +"€.");
                            tablero.setBoteParking(0); // La cantidad se reinicia cada vez que un jugador cae en parking
                            break;

                        case "salida":
                            if(jugadores.get(turno).estaEnCarcel() == true){
                                break;
                            } else {
                                jugadores.get(turno).sumarFortuna(2000000);
                                System.out.println("El jugador " + jugadores.get(turno).getNombre() + " recibe 2000000€ por pasar por la salida.");
                                break;
                            }
                        case "ircarcel":
                            jugadores.get(turno).encarcelar(tablero.getPosiciones());
                            break;
                    }
                case "servicio":
                    // Si la posicionDespues tiene duenho, pagamos el alquiler
                    if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                        posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler de servicio.");
                    } else{
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                    }
                    break;

                case "transporte":
                    // Si la posicionDespues tiene duenho, pagamos el alquiler
                    if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                        posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler de transporte.");
                    } else{
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                    }
                    break;
                
                case "impuesto":
                    tablero.sumarboteParking(posicionDespues.getImpuesto());
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta impuesto " + posicionDespues.getNombre() + ". El jugador paga "+ posicionDespues.getImpuesto() + "€ que se depositan en el Parking.");
                    break;

                case "comunidad":
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta Caja de comunidad.");
                    break;
    
                case "suerte":
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta Suerte.");
                    break;
            }

            lanzamientos++;

        }while(dadosDobles == true);        
    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(Jugador comprador, String casilla) {
        for(Casilla c : tablero.getCasillas()) {
            if(c.getNombre().equalsIgnoreCase(casilla)) {
                c.comprarCasilla(comprador, tablero.getBanca());
                return;
            }
        }
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel(){
        if(jugadores.get(turno).getAvatar().getLugar().getNombre().equalsIgnoreCase("carcel")){
            if(jugadores.get(turno).estaEnCarcel() == false){
                System.out.println("El jugador " + jugadores.get(turno).getNombre() + " simplemente está de visita en la cárcel, pero no encarcelado. Puede seguir jugando normal.");
            } else{ 
                // opciones para salir de la cárcel: (1) pagar 500000€ // (2) usar una carta de suerte (esta entrega no) // (3) sacar dobles valores en los dados
                Scanner sc = new Scanner(System.in);
                System.out.println("Elige una opción (1) pagar 500000€ (2) usar una carta de suerte (esta entrega no) (3) probar a sacar dobles valores en los dados en los siguientes 3 turnos: ");
                int opcion = sc.nextInt();

                if(opcion == 1){
                    jugadores.get(turno).sumarFortuna(-500000);
                    System.out.println(jugadores.get(turno).getNombre() + " paga 500.000€ y sale de la cárcel. Puede lanzar los dados.");
                } else if(opcion == 2){
                    System.out.println("Opción no disopnible actualmente");
                } else if(opcion == 3){
                    tirado = false; 
                    lanzarDados();
                }

                jugadores.get(turno).setTiradasCarcel(0);
                jugadores.get(turno).setEnCarcel(false);
            }
        } else{
            System.out.println("El jugador " + jugadores.get(turno).getNombre() + " no está en la casilla cárcel, por lo que no tiene que salir de ella.");
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
        for(Casilla c : tablero.getCasillas()) {
            if(c.getDuenho() == null || c.getDuenho() == banca) {
                if(c.getTipo().equalsIgnoreCase("solar")){ //Sólo estas casillas pueden ser compradas
                    System.out.println("{\n\ttipo: " + c.getTipo() + ", \n\tgrupo: " + c.getGrupo().getColorGrupo() + ", \n\tvalor: " + c.getValor() + "\n}");
                } else if(c.getTipo().equalsIgnoreCase("servicio") || c.getTipo().equalsIgnoreCase("transporte")){
                    System.out.println("{\n\ttipo: " + c.getTipo() + ", \n\tvalor: " + c.getValor() + "\n}");
                }
            }
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
        for(Jugador j : jugadores) {
            System.out.println(j.toString());
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {
    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
        setTurno((getTurno() + 1) % jugadores.size());
        System.out.println("El jugador actual es " + jugadores.get(turno).getNombre() + ".");
    }



    private void lanzarDadosForzados(String partes[]) {

        if(tirado == true){
            System.out.println(jugadores.get(turno).getNombre() +", ya has lanzado los dados. Finaliza tu turno.");
            acabarTurno();
            return;
        }

        if(jugadores.get(turno).estaEnCarcel()){
            Dado dadoCarcel1 = new Dado();
            Dado dadoCarcel2 = new Dado();

            dadoCarcel1.setValorDado(dadoCarcel1.hacerTirada());
            dadoCarcel2.setValorDado(dadoCarcel2.hacerTirada());

            int valorDadoCarcel1 = dadoCarcel1.getValorDado();
            int valorDadoCarcel2 = dadoCarcel2.getValorDado();

            if(valorDadoCarcel1 == valorDadoCarcel2){
                // El jugador sale de la cárcel pero no se mueve
                jugadores.get(turno).setEnCarcel(false);
                jugadores.get(turno).setTiradasCarcel(0);
            }

            if(jugadores.get(turno).getTiradasCarcel() >= 3 && jugadores.get(turno).estaEnCarcel() == true){
                // Paga obligatoriamente los 500000 y sale de la cárcel
                jugadores.get(turno).sumarFortuna(-500000);
                System.out.println(jugadores.get(turno).getNombre() + " paga 500.000€ y sale de la cárcel. Puede lanzar los dados.");
                jugadores.get(turno).setEnCarcel(false);
                jugadores.get(turno).setTiradasCarcel(0);
            }

            jugadores.get(turno).setTiradasCarcel(jugadores.get(turno).getTiradasCarcel()+1);

            return;
        }

        // en el partes[2] esta el valorforzado en el formato "x+y"
        String[] valores = partes[2].split("\\+"); // el split se usa para separar expresiones regulares, y en este caso, '+' no es tratado como un caracter literal, por eso se utiliza la \\ 
        int valorDado1 = Integer.parseInt(valores[0]); //Convertir a entero.
        int valorDado2 = Integer.parseInt(valores[1]);

        int valorTotal = valorDado1 + valorDado2;

        if(valorDado1 == valorDado2){
            dadosDobles = true;
            vecesDoblesSacados++;
        }

        Casilla posicionAntes = jugadores.get(turno).getAvatar().getLugar();

        // Avanzar el avatar del jugador en turno
        jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), valorTotal);

        Casilla posicionDespues = jugadores.get(turno).getAvatar().getLugar();

        switch(posicionDespues.getTipo().toLowerCase()){
            case "solar": 
                // Si la posicionDespues tiene duenho, pagamos el alquiler
                if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                    posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler.");
                } else{
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                }
                break;
            
            case "especial":
                switch(posicionDespues.getNombre().toLowerCase()){
                    case "carcel":
                        if(jugadores.get(turno).estaEnCarcel() == false){
                            System.out.println("Simplemente estás de visita en la cárcel, pero no encarcelado. Puedes seguir jugando normal.");
                            break;
                        }
                    
                    case "parking":
                        jugadores.get(turno).sumarFortuna(tablero.getBoteParking());
                        System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". El jugador " + jugadores.get(turno) + " recibe " + tablero.getBoteParking() +"€.");
                        tablero.setBoteParking(0); // La cantidad se reinicia cada vez que un jugador cae en parking
                        break;

                    case "salida":
                        if(jugadores.get(turno).estaEnCarcel() == false){
                            jugadores.get(turno).sumarFortuna(2000000);
                            System.out.println("El jugador " + jugadores.get(turno).getNombre() + " recibe 2000000€ por pasar por la salida.");
                        }
                        break;

                    case "ircarcel":
                        jugadores.get(turno).encarcelar(tablero.getPosiciones());
                        break;
                        
                }
            case "servicio":
                // Si la posicionDespues tiene duenho, pagamos el alquiler
                if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                    posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler de servicio.");
                } else{
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                }
                break;

            case "transporte":
                // Si la posicionDespues tiene duenho, pagamos el alquiler
                if((posicionDespues.getDuenho() != null) && (posicionDespues.getDuenho() != jugadores.get(turno)) && (posicionDespues.getDuenho() != banca)) {
                    posicionDespues.alquiler(posicionDespues.getDuenho(), jugadores.get(turno), valorTotal);
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre() + ". Se han pagado "+ posicionDespues.getImpuesto() + "€ de alquiler de transporte.");
                } else{
                    System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta " + posicionDespues.getNombre());
                }
                break;
            
            case "impuesto":
                tablero.sumarboteParking(posicionDespues.getImpuesto());
                System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta impuesto " + posicionDespues.getNombre() + ". El jugador paga "+ posicionDespues.getImpuesto() + "€ que se depositan en el Parking.");
                break;

            case "comunidad":
                System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta Caja de comunidad.");
                break;

            case "suerte":
                System.out.println("El avatar " + jugadores.get(turno).getAvatar().getId() + " avanza " + valorTotal + " posiciones, desde " + posicionAntes.getNombre() + " hasta Suerte.");
                break;
        }

        lanzamientos++;
        
    }

}

