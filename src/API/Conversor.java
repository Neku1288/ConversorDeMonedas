package API;

import com.google.gson.JsonObject;

public class Conversor {
    private JsonObject cambio;

        public Conversor(JsonObject tasasDeCambio) {
        this.cambio = tasasDeCambio;
    }
    public String convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        monedaOrigen = monedaOrigen.toUpperCase();
        monedaDestino = monedaDestino.toUpperCase();

        if (cambio.has(monedaOrigen) && cambio.has(monedaDestino)) {
            double deOrigenADestino = cambio.get(monedaDestino).getAsDouble();
            double deDestinoAOrigen = cambio.get(monedaOrigen).getAsDouble();
            double cantidadConvertida = (cantidad * deOrigenADestino) / deDestinoAOrigen;
            return String.format("%.2f", cantidadConvertida);
        } else {
            return "Moneda no encontrada en las tasas de cambio.";
        }
    }
}