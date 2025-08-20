# CurrencyConverter in Java

# Overview

The CurrencyConverter Java program is a utility tool designed to perform real-time currency conversions by fetching real-time exchange rates from an external API. It enables users to specify an amount, input the currency symbol or currency abreviation, and the target currency. The program then calculates the converted amount and outputs the result as a structured JSON object. This tool utilizes RESTful API calls, handles currency symbol mappings, and produces user-friendly data representations. Making it easy to use for users of any tech level and allowing it to be suitable for integration into larger established financial systems or for personal use.

# Features:
- Supports live conversions between multiple currencies, utilizing symbol-to-code mappings
- Fetches live exchange rates from the Fixer.io API
- Handles command-line arguments for flexible user input
- Generates a structured JSON response detailing the conversion results
- Supports conversion into a specific target currency or all available currencies

## Technologies Used:
- Java (Java SE 11 or higher)
- Gson library for JSON parsing and formatting
- External API: Fixer.io

# Usage Instructions:

# File Pathway Tree/ File Directory:
\CurrencyConverter\
    | --- CurrencyConverter.java

## Installation & Setup:
1.	Ensure Java Development Kit (JDK) 11 or higher is installed on your system, alongside a runnable IDE (Such as Eclipse or IntelliJ IDEA).
2.	Download or clone this repository.
3.	Add the Gson library to your project dependencies. (If using command line to add this, include the Gson jar in your classpath.)
4.	After that no additional setup is required; just run the “CurrencyConverter.java” file.

## Running the CurrencyConverter Program:
1.	Compile and launch the program: CurrencyConverter.java.
2.	The program will then allow you to specify the amount, the input currency, and optionally, the output currency(s).
3.	Once you’ve entered at least the amount and the input currency, with the additional option to enter the output currency(s). The program will output a JSON string with the conversion details.

## How It Works:
- The program parses command-line arguments entered from the user for the amount needed to be converted, the input currency symbol, and optionally the output currency codes.
- It maps currency symbols to their standardized ISO currency codes using the SYMBOLS_CURRENCIES map.
- The tool constructs an API URL to fetch the latest exchange rates from Fixer.io.
- Parses JSON response from the API to retrieve rates.
- Performs the conversion calculation based on user input.
- Generates a JSON output depicting both the input details from the user and the conversion output results, either outputting a specific target currency or all available currencies based on the users choice.

# Contributions:
Contributions are welcome! Feel free to fork the repository, implement new features, improve and expand upon current functionality, or fix bugs. Pull requests are appreciated.
