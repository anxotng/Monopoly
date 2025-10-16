package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {
    // Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; // 4 lados do tabuleiro
    private HashMap<String, Grupo> grupos; // cor → Grupo
    private Jugador banca; // banca
    private long boteParking; // acumulador do Parking

    public Tablero(Jugador banca) {
        this.banca = banca;
        this.posiciones = new ArrayList<>();
        this.grupos = new HashMap<>();
        this.boteParking = 0L;
        this.generarCasillas();
    }

    public ArrayList<ArrayList<Casilla>> getPosiciones() {
        return posiciones;
    }

    public Jugador getBanca() {
        return banca;
    }

    public long getBoteParking() {
        return boteParking;
    }

    public void sumarAlBote(long cantidad) {
        if (cantidad > 0)
            this.boteParking += cantidad;
    }

    public long vaciarBote() {
        long tmp = this.boteParking;
        this.boteParking = 0L;
        return tmp;
    }

    // Cria as casillas (4 lados)
    private void generarCasillas() {
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }

    // Lado Sur
    private void insertarLadoSur() {
        ArrayList<Casilla> ladoSur = new ArrayList<>();
        this.posiciones.add(ladoSur);

        Casilla salida = new Casilla("Salida", "Especial", 1, banca);
        ladoSur.add(salida);

        Casilla solar1 = new Casilla("Solar1", "Solar", 2, 600000, banca);
        ladoSur.add(solar1);

        Casilla caja = new Casilla("Caja", "Comunidad", 3, banca);
        ladoSur.add(caja);

        Casilla solar2 = new Casilla("Solar2", "Solar", 4, 600000, banca);
        ladoSur.add(solar2);

        Casilla imp1 = new Casilla("Imp1", 5, 2000000, banca);
        ladoSur.add(imp1);

        Casilla trans1 = new Casilla("Trans1", "Transporte", 6, 500000, banca);
        ladoSur.add(trans1);

        Casilla solar3 = new Casilla("Solar3", "Solar", 7, 1000000, banca);
        ladoSur.add(solar3);

        Casilla suerte = new Casilla("Suerte", "Suerte", 8, banca);
        ladoSur.add(suerte);

        Casilla solar4 = new Casilla("Solar4", "Solar", 9, 1000000, banca);
        ladoSur.add(solar4);

        Casilla solar5 = new Casilla("Solar5", "Solar", 10, 1200000, banca);
        ladoSur.add(solar5);

        Casilla carcel = new Casilla("Carcel", "Especial", 11, banca);
        ladoSur.add(carcel);

        grupos.put("negro", new Grupo(solar1, solar2, "Negro"));
        solar1.setGrupo(grupos.get("negro"));
        solar2.setGrupo(grupos.get("negro"));

        grupos.put("azul", new Grupo(solar3, solar4, solar5, "Azul"));
        solar3.setGrupo(grupos.get("azul"));
        solar4.setGrupo(grupos.get("azul"));
        solar5.setGrupo(grupos.get("azul"));
    }

    // Lado Oeste
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoOeste = new ArrayList<>();
        this.posiciones.add(ladoOeste);

        Casilla solar6 = new Casilla("Solar6", "Solar", 12, 1400000, banca);
        ladoOeste.add(solar6);

        Casilla serv1 = new Casilla("Serv1", "Servicio", 13, 500000, banca);
        ladoOeste.add(serv1);

        Casilla solar7 = new Casilla("Solar7", "Solar", 14, 1400000, banca);
        ladoOeste.add(solar7);

        Casilla solar8 = new Casilla("Solar8", "Solar", 15, 1600000, banca);
        ladoOeste.add(solar8);

        Casilla trans2 = new Casilla("Trans2", "Transporte", 16, 500000, banca);
        ladoOeste.add(trans2);

        Casilla solar9 = new Casilla("Solar9", "Solar", 17, 1800000, banca);
        ladoOeste.add(solar9);

        Casilla caja = new Casilla("Caja", "Comunidad", 18, banca);
        ladoOeste.add(caja);

        Casilla solar10 = new Casilla("Solar10", "Solar", 19, 1800000, banca);
        ladoOeste.add(solar10);

        Casilla solar11 = new Casilla("Solar11", "Solar", 20, 2200000, banca);
        ladoOeste.add(solar11);

        grupos.put("rosa", new Grupo(solar6, solar7, solar8, "Rosa"));
        solar6.setGrupo(grupos.get("rosa"));
        solar7.setGrupo(grupos.get("rosa"));
        solar8.setGrupo(grupos.get("rosa"));

        grupos.put("salmon", new Grupo(solar9, solar10, solar11, "Cyan")); // "salmon" mapeado para Cyan
        solar9.setGrupo(grupos.get("salmon"));
        solar10.setGrupo(grupos.get("salmon"));
        solar11.setGrupo(grupos.get("salmon"));
    }

    // Lado Norte
    private void insertarLadoNorte() {
        ArrayList<Casilla> ladoNorte = new ArrayList<>();
        this.posiciones.add(ladoNorte);

        Casilla parking = new Casilla("Parking", "Especial", 21, 0, banca);
        ladoNorte.add(parking);

        Casilla solar12 = new Casilla("Solar12", "Solar", 22, 2200000, banca);
        ladoNorte.add(solar12);

        Casilla suerte = new Casilla("Suerte", "Suerte", 23, banca);
        ladoNorte.add(suerte);

        Casilla solar13 = new Casilla("Solar13", "Solar", 24, 2_200_000, banca);
        ladoNorte.add(solar13);

        Casilla solar14 = new Casilla("Solar14", "Solar", 25, 2400000, banca);
        ladoNorte.add(solar14);

        Casilla trans3 = new Casilla("Trans3", "Transporte", 26, 500000, banca);
        ladoNorte.add(trans3);

        Casilla solar15 = new Casilla("Solar15", "Solar", 27, 2600000, banca);
        ladoNorte.add(solar15);

        Casilla solar16 = new Casilla("Solar16", "Solar", 28, 2600000, banca);
        ladoNorte.add(solar16);

        Casilla serv2 = new Casilla("Serv2", "Servicio", 29, 500000, banca);
        ladoNorte.add(serv2);

        Casilla solar17 = new Casilla("Solar17", "Solar", 30, 2800000, banca);
        ladoNorte.add(solar17);

        Casilla irCarcel = new Casilla("IrCarcel", "Especial", 31, banca);
        ladoNorte.add(irCarcel);

        // Grupos norte (para pintar)
        grupos.put("rojo", new Grupo(solar12, solar13, solar14, "Rojo"));
        solar12.setGrupo(grupos.get("rojo"));
        solar13.setGrupo(grupos.get("rojo"));
        solar14.setGrupo(grupos.get("rojo"));

        grupos.put("amarillo", new Grupo(solar15, solar16, solar17, "Amarillo"));
        solar15.setGrupo(grupos.get("amarillo"));
        solar16.setGrupo(grupos.get("amarillo"));
        solar17.setGrupo(grupos.get("amarillo"));
    }

    // Lado Este
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();
        this.posiciones.add(ladoEste);

        Casilla solar18 = new Casilla("Solar18", "Solar", 32, 3000000, banca);
        ladoEste.add(solar18);

        Casilla solar19 = new Casilla("Solar19", "Solar", 33, 3000000, banca);
        ladoEste.add(solar19);

        Casilla caja = new Casilla("Caja", "Comunidad", 34, banca);
        ladoEste.add(caja);

        Casilla solar20 = new Casilla("Solar20", "Solar", 35, 3200000, banca);
        ladoEste.add(solar20);

        Casilla trans4 = new Casilla("Trans4", "Transporte", 36, 500000, banca);
        ladoEste.add(trans4);

        Casilla suerte = new Casilla("Suerte", "Suerte", 37, banca);
        ladoEste.add(suerte);

        Casilla solar21 = new Casilla("Solar21", "Solar", 38, 3500000, banca);
        ladoEste.add(solar21);

        Casilla imp2 = new Casilla("Imp2", 39, 2000000, banca);
        ladoEste.add(imp2);

        Casilla solar22 = new Casilla("Solar22", "Solar", 40, 4000000, banca);
        ladoEste.add(solar22);

        // Grupos leste (para pintar)
        grupos.put("verde", new Grupo(solar18, solar19, solar20, "Verde"));
        solar18.setGrupo(grupos.get("verde"));
        solar19.setGrupo(grupos.get("verde"));
        solar20.setGrupo(grupos.get("verde"));

        grupos.put("morado", new Grupo(solar21, solar22, "Morado"));
        solar21.setGrupo(grupos.get("morado"));
        solar22.setGrupo(grupos.get("morado"));
    }

    // ---- helpers para ANSI / largura visível ----
    private static final java.util.regex.Pattern ANSI = java.util.regex.Pattern.compile("\\u001B\\[[;\\d]*m");

    private static String stripAnsi(String s) {
        return (s == null) ? "" : ANSI.matcher(s).replaceAll("");
    }

    private static String padRightDisplay(String s, int width) {
        if (s == null)
            s = "";
        String noAnsi = stripAnsi(s);
        int pad = Math.max(0, width - noAnsi.length());
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < pad; i++)
            sb.append(' ');
        return sb.toString();
    }

    // Mostra nome (com cor se for grupo) + até 2 avatares (ex.: "Solar3 &X &G +1")
    public String renderCasilla(Casilla casilla) {
        if (casilla == null)
            return "";
        String nombre = casilla.getNombre();
        String prefix = "", suffix = "";
        if (casilla.getGrupo() != null) {
            prefix = Valor.getColor(casilla.getGrupo().getColorGrupo());
            suffix = Valor.RESET;
        }
        StringBuilder avs = new StringBuilder();
        java.util.List<Avatar> lista = casilla.getAvatares();
        if (lista != null && !lista.isEmpty()) {
            avs.append(" ");
            int shown = 0;
            for (Avatar a : lista) {
                if (shown < 2) {
                    avs.append(a.toString()).append(" ");
                    shown++;
                } else
                    break;
            }
            int rest = lista.size() - shown;
            if (rest > 0)
                avs.append("+").append(rest);
            int len = avs.length();
            if (len > 0 && avs.charAt(len - 1) == ' ')
                avs.setLength(len - 1);
        }
        return prefix + nombre + suffix + avs.toString();
    }

    // Impressão do tabuleiro (ASCII) — colunas 100% alinhadas
    @Override
    public String toString() {
        final int CELLW = 12; // largura VISÍVEL do conteúdo
        final int COLS = 11; // 11 colunas
        StringBuilder sb = new StringBuilder();

        // renderiza uma linha completa (Norte/Sul)
        java.util.function.Function<Casilla[], String> renderRow = cols -> {
            StringBuilder row = new StringBuilder();
            row.append("| ");
            for (int col = 0; col < COLS; col++) {
                String cell = (cols[col] == null) ? "" : renderCasilla(cols[col]);
                row.append(padRightDisplay(cell, CELLW)).append(" | ");
            }
            return row.toString();
        };

        // ===== Norte =====
        Casilla[] north = new Casilla[COLS];
        for (int i = 0; i < COLS; i++)
            north[i] = posiciones.get(2).get(i);

        String northLine = renderRow.apply(north);
        int lineVisibleLen = stripAnsi(northLine).length();
        String horiz = "-".repeat(lineVisibleLen); // << sem \n embutido

        // topo (sem linha em branco sobrando)
        sb.append(horiz).append("\n");
        sb.append(northLine).append("\n");
        sb.append(horiz).append("\n");

        // ===== Miolo (9 linhas) =====
        String leftSep = "| " + "-".repeat(CELLW) + " | ";
        String rightSep = leftSep;
        int visLeft = stripAnsi(leftSep).length();
        int visRight = stripAnsi(rightSep).length();
        int midSepGap = Math.max(1, lineVisibleLen - visLeft - visRight);

        for (int i = 0; i < 9; i++) {
            String west = "| " + padRightDisplay(renderCasilla(posiciones.get(1).get(8 - i)), CELLW) + " | ";
            String east = "| " + padRightDisplay(renderCasilla(posiciones.get(3).get(i)), CELLW) + " | ";

            int visWest = stripAnsi(west).length();
            int visEast = stripAnsi(east).length();
            int midGap = Math.max(1, lineVisibleLen - visWest - visEast);

            sb.append(west).append(" ".repeat(midGap)).append(east).append("\n");

            if (i != 8) {
                sb.append(leftSep).append(" ".repeat(midSepGap)).append(rightSep).append("\n");
            }
        }

        // ===== Sul =====
        sb.append(horiz).append("\n");
        Casilla[] south = new Casilla[COLS];
        for (int i = 0; i < COLS; i++)
            south[i] = posiciones.get(0).get(10 - i);
        sb.append(renderRow.apply(south)).append("\n");
        sb.append(horiz); // << sem \n final para não sobrar uma linha em branco

        return sb.toString();
    }

    // Busca casilla por nome (case-insensitive)
    public Casilla encontrar_casilla(String nombre) {
        if (nombre == null)
            return null;
        for (ArrayList<Casilla> lado : posiciones) {
            for (Casilla c : lado) {
                if (c != null && nombre.equalsIgnoreCase(c.getNombre()))
                    return c;
            }
        }
        return null;
    }
}
