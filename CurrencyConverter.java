import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class CurrencyConverter {
    private static final Map<String, String> SYMBOLS_CURRENCIES = new HashMap<String, String>() {{
        put("€", "EUR");
        put("A$", "AUD");
        put("R$", "BRL");
        put("C$", "CAD");
        put("C¥", "CNY");
        put("Kč", "CZK");
        put("dkr", "DKK");
        put("£", "GBP");
        put("HK$", "HKD");
        put("kn", "HRK");
        put("Ft", "HUF");
        put("Rp", "IDR");
        put("₪", "ILS");
        put("₹", "INR");
        put("J¥", "JPY");
        put("₩", "KRW");
        put("Mex$", "MXN");
        put("RM", "MYR");
        put("NZ$", "NZD");
        put("₱", "PHP");
        put("zł", "PLN");
        put("lei", "RON");
        put("₽", "RUB");
        put("skr", "SEK");
        put("S$", "SGD");
        put("฿", "THB");
        put("₺", "TRY");
        put("$", "USD");
        put("R", "ZAR");
    }};

    public static void main(String[] args) {
        String amount = null;
        String inputCurrency = null;
        String outputCurrency = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a") || args[i].equals("--amount")) {
                amount = args[i + 1];
            } else if (args[i].equals("-i") || args[i].equals("--input_currency")) {
                inputCurrency = args[i + 1];
            } else if (args[i].equals("-o") || args[i].equals("--output_currency")) {
                outputCurrency = args[i + 1];
            }
        }

        if (amount == null || inputCurrency == null) {
            System.out.println("Please provide the amount and input currency.");
            System.exit(1);
        }

        double result = convertCurrency(inputCurrency, amount, outputCurrency);
        String json = createJsonResponse(inputCurrency, amount, result, outputCurrency);
        System.out.println(json);
    }

    private static double convertCurrency(String base, String amount, String target) {
        String baseCurrency = SYMBOLS_CURRENCIES.getOrDefault(base, base);
        String targetCurrency = SYMBOLS_CURRENCIES.getOrDefault(target, target);

        String url = "http://api.fixer.io/latest?base=" + baseCurrency;
        if (targetCurrency != null) {
            url += "&symbols=" + targetCurrency;
        }

        try {
            String json = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
            Gson gson = new Gson();
            ExchangeRate exchangeRate = gson.fromJson(json, ExchangeRate.class);

            if (targetCurrency != null) {
                return Double.parseDouble(amount) * exchangeRate.getRates().get(targetCurrency);
            } else {
                double result = 0.0;
                for (Map.Entry<String, Double> entry : exchangeRate.getRates().entrySet()) {
                    result += Double.parseDouble(amount) * entry.getValue();
                }
                return result;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while fetching the exchange rate.");
            System.exit(1);
        }

        return 0.0;
    }

    private static String createJsonResponse(String base, String amount, double result, String target) {
        String baseCurrency = SYMBOLS_CURRENCIES.getOrDefault(base, base);

        Map<String, Double> output = new HashMap<>();
        if (target != null) {
            String targetCurrency = SYMBOLS_CURRENCIES.getOrDefault(target, target);
            output.put(targetCurrency, result);
        } else {
            for (Map.Entry<String, String> entry : SYMBOLS_CURRENCIES.entrySet()) {
                output.put(entry.getValue(), result);
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("input", Map.of("amount", amount, "currency", baseCurrency));
        data.put("output", output);

        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private static class ExchangeRate {
        private Map<String, Double> rates;

        public Map<String, Double> getRates() {
            return rates;
        }
    }
}


