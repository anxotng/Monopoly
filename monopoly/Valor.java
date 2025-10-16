package monopoly;

public class Valor {
    // ===== Dinheiro (use long) =====
    public static final long FORTUNA_BANCA       = 500_000L;     // ajuste se o prof especificar outro
    public static final long FORTUNA_INICIAL     = 15_000_000L;
    public static final long SUMA_VUELTA         = 2_000_000L;

    // Parte 1 – constantes de regras básicas
    public static final long IMPUESTO_FIJO       = 2_000_000L;
    public static final long SALIR_CARCEL        = 500_000L;
    public static final long PRECIO_TRANSP_SERV  = 500_000L;
    public static final long ALQUILER_TRANSP     = 250_000L;
    public static final long FACTOR_SERVICIO     = 50_000L; // Servicio: 4 * (d1+d2) * FACTOR_SERVICIO

    // ===== Cores ANSI =====
    public static final String RESET  = "\u001B[0m";
    public static final String BLACK  = "\u001B[30m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";
    public static final String PINK   = "\u001B[95m"; // magenta brilhante (rosa)

    public static String getColor(String colorGrupo){
        return switch (colorGrupo.toLowerCase()) {
            case "negro" -> BLACK;
            case "rojo" -> RED;
            case "verde" -> GREEN;
            case "amarillo" -> YELLOW;
            case "azul" -> BLUE;
            case "morado" -> PURPLE;
            case "cyan" -> CYAN;
            case "blanco" -> WHITE;
            case "rosa" -> PINK;
            default -> RESET;
        };
    }

    // Overload opcional para quando tivermos enum Grupo
    public static String getColor(Grupo grupo){
        if (grupo == null) return RESET;
        // Supondo que Grupo tenha getColorNombre() → "rojo", "azul", etc.
        return getColor(grupo.getColorNombre());
    }

    // Helper pra imprimir com cor
    public static String colorize(String text, String color) {
        if (text == null) return "";
        return color + text + RESET;
        // uso: System.out.println(Valor.colorize("Solar 1", Valor.RED));
    }
}
