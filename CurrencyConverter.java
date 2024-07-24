import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Allow user to choose base and target currencies
        System.out.print("Enter the base currency code (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency code (e.g., USD, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Fetch real-time exchange rates from API
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate == -1.0) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
            return;
        }

        // Step 3: Take input amount from user
        System.out.print("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
        double amountToConvert = scanner.nextDouble();

        // Step 4: Currency Conversion
        double convertedAmount = amountToConvert * exchangeRate;

        // Step 5: Display Result
        System.out.printf("%.2f %s is equivalent to %.2f %s\n",
                amountToConvert, baseCurrency, convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(API_URL + baseCurrency);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse JSON response to get exchange rates
            String jsonResponse = response.toString();
            double exchangeRate = parseExchangeRate(jsonResponse, targetCurrency);
            return exchangeRate;

        } catch (IOException e) {
            e.printStackTrace();
            return -1.0; // indicate failure
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        // Example JSON response structure: {"rates":{"USD":1.0,"EUR":0.86,...}}
        // Parse JSON to get exchange rate for targetCurrency
        try {
            int startIndex = jsonResponse.indexOf("\"" + targetCurrency + "\":");
            int endIndex = jsonResponse.indexOf(",", startIndex);
            String rateString = jsonResponse.substring(startIndex + targetCurrency.length() + 3, endIndex);
            return Double.parseDouble(rateString);
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // indicate failure
        }
    }
}
