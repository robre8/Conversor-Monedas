public class Conversion {
    private final ConsultaApiMoneda consultaApi;

    public Conversion() {
        this.consultaApi = new ConsultaApiMoneda();
    }

    public String resultadoConversion(String deMoneda, String aMoneda, double valor) {
        try {
            double tasa = consultaApi.buscaMoneda(deMoneda, aMoneda);
            double resultado = tasa * valor;
            return String.format("Tasa de conversión de %s a %s: es de %.2f", deMoneda, aMoneda, resultado);
        } catch (RuntimeException e) {
            return "Error en la conversión: " + e.getMessage();
        }
    }
}