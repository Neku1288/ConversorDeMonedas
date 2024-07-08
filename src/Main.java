import API.ConeccionesAPI;
import API.Conversor;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                String baseCurrency = "CAD";
                JsonObject ratesObject = ConeccionesAPI.getExchangeRates(baseCurrency); // Llamada a un método para obtener las tasas de cambio
                JsonObject conversionRates = ratesObject.getAsJsonObject("conversion_rates"); // Extraer el objeto conversion_rates

                // Imprimir las monedas disponibles
                System.out.println("Bienvenido a El Conversor de Monedas Alura");


                System.out.println("Las Monedas disponibles para cambio son:");
                Set<String> currencies = conversionRates.keySet();
                int count = 0;
                for (String currency : currencies) {
                    System.out.printf("%-10s", currency);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
                System.out.println("\n");

                System.out.println("Por favor ingresa la moneda base que quieres cambiar:");
                System.out.println("(Ejemplo: MXN= Peso Mexicano, USD= Dollar Americano, CAD= Dollar Canadiense):");
                baseCurrency = scanner.nextLine();
                baseCurrency = baseCurrency.toUpperCase();


                System.out.println("¿A qué moneda quieres hacer el Cambio?");
                System.out.println("(Ejemplo: MXN= Peso Mexicano, USD= Dollar Americano, CAD= Dollar Canadiense):");
                String secondCurrency = scanner.nextLine();
                secondCurrency = secondCurrency.toUpperCase();
                System.out.println("¿Qué cantidad quieres convertir de " + baseCurrency + " a " + secondCurrency + "? :");
                double amountToConvert = scanner.nextDouble();
                scanner.nextLine();

                Conversor conversor = new Conversor(conversionRates); // crea una variable del conversor

                String convertedAmount = conversor.convertirMoneda(baseCurrency, secondCurrency, amountToConvert);
                System.out.println(baseCurrency + " a " + secondCurrency + " = " + convertedAmount);

                System.out.println("¿Deseas continuar con otra conversión? (s/n)");
                String respuesta2 = scanner.nextLine();
                if (!respuesta2.equalsIgnoreCase("s")) {
                    System.out.println("Te agradecemos que usaras nuestro servicio, vuelve pronto");
                    continuar = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }
}
//Este programa fue creado por Marcos Rcr, pero fue adaptado y modificado por Eduardo Valdespino para el challenge de Alura
//08/07/2024