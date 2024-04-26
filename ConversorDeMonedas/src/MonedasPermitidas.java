import java.util.*;

public class MonedasPermitidas {
    private static Conversion conversion = new Conversion();
    private Map<Integer, String> monedas = new HashMap<>();
    private Scanner lectura = new Scanner(System.in);
    private String monedaFinal = "";
    private List<String> historialConversiones = new ArrayList<>();

    public MonedasPermitidas() {
        Monedas();
    }

    private void Monedas() {
        monedas.put(1, "CRC - Colón costarricense");
        monedas.put(2, "ARS - Peso argentino");
        monedas.put(3, "BOB - Boliviano boliviano");
        monedas.put(4, "BRL - Real brasileño");
        monedas.put(5, "CLP - Peso chileno");
        monedas.put(6, "COP - Peso colombiano");
        monedas.put(7, "USD - Dólar estadounidense");
        monedas.put(8, "EUR - Euro Unión Europea");
    }

    public void iniciar() {
        System.out.println(" Bienvenido(a) al conversor de monedas ");
        do {
            resultado();
            System.out.println("¿Desea continuar en el conversor? (S/N):");
        } while (lectura.next().equalsIgnoreCase("S"));
        System.out.println("Gracias por usar el conversor de monedas");
        lectura.close();
    }

    public void resultado() {
        String deMoneda = seleccionarMonedaBase();

        System.out.println("Elija la moneda a convertir:");
        String aMoneda = seleccionarMonedaDestino();

        System.out.println("Ingrese el monto a convertir:");
        double valor = lectura.nextDouble();

        String resultado = conversion.resultadoConversion(deMoneda, aMoneda, valor);
        System.out.println(resultado + " " + monedaFinal);
        historialConversiones.add(resultado);
    }

    public void mostrarMonedas() {
        for (Map.Entry<Integer, String> entry : monedas.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("Elija el número de la moneda que desea convertir :");
    }

    public String seleccionarMonedaBase() {
        int opcionBase;
        do {
            mostrarMonedas();
            opcionBase = lectura.nextInt();
        } while (!monedas.containsKey(opcionBase));

        String[] monedaSplit = monedas.get(opcionBase).split(" - ");
        monedaFinal = monedaSplit[1];
        return monedaSplit[0];
    }

    public String seleccionarMonedaDestino() {
        int opcionDestino;
        do {
            mostrarMonedas();
            opcionDestino = lectura.nextInt();
        } while (!monedas.containsKey(opcionDestino));

        String[] monedaSplit = monedas.get(opcionDestino).split(" - ");
        monedaFinal = monedaSplit[1];
        return monedaSplit[0];
    }
}