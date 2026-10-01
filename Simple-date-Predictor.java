import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SimpleDatePredictor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Simple Date Predictor ===");
        System.out.print("Enter a date (accepted formats: yyyy-MM-dd, dd-MM-yyyy, dd/MM/yyyy): ");
        String inputDate = scanner.nextLine().trim();

        LocalDate enteredDate = parseDate(inputDate);
        if (enteredDate == null) {
            System.out.println("Invalid date format. Please enter a valid date.");
            return;
        }

        System.out.print("How many days ahead should be predicted? (default: 1): ");
        String daysInput = scanner.nextLine().trim();

        long daysAhead = 1;
        if (!daysInput.isEmpty()) {
            try {
                daysAhead = Long.parseLong(daysInput);
                if (daysAhead < 0) {
                    System.out.println("Days ahead must be zero or greater.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of days. Please enter a whole number.");
                return;
            }
        }

        LocalDate predictedDate = enteredDate.plusDays(daysAhead);

        System.out.println();
        System.out.println("Entered date: " + formatDate(enteredDate));
        System.out.println("Predicted date: " + formatDate(predictedDate));
        System.out.println("Prediction: " + daysAhead + " day(s) after the entered date.");

        scanner.close();
    }

    private static LocalDate parseDate(String dateText) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateText, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }

        return null;
    }

    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
