import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println(getDateDiff(LocalDate.of(2024, 1, 20)));
        getDateDiffWithUserInput();
    }

    public static void getDateDiffWithUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date: ");
        String date = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Date date2 = null;
        try {
            date2 = (Date) dateFormat.parse(date);
            System.out.print("You have " + ((Temporal) date2).until(LocalDate.now(), ChronoUnit.DAYS) + " days left.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static long getDateDiff(LocalDate date) {
        return date.until(LocalDate.now(), ChronoUnit.DAYS);
    }

}
