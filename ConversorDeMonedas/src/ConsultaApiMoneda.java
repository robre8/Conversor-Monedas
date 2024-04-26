import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;

public class ConsultaApiMoneda {

    public double buscaMoneda(String deMoneda, String aMoneda) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/3c22f7b9b01974914dde5117/latest/" + deMoneda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Moneda moneda = new Gson().fromJson(response.body(), Moneda.class);

            Map<String, Double> conversionRates = moneda.getConversion_rates();
            Double tasa = conversionRates.get(aMoneda);

            if (tasa != null) {
                return tasa;
            } else {
                throw new RuntimeException("Tasa de conversión no encontrada para la moneda a convertir");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tasa de conversión");
        }
    }
}