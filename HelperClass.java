import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class HelperClass {
        public static boolean isDateInFuture(String givenDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                Date parsedDate = dateFormat.parse(givenDate);
                Date currentDate = new Date();
                return parsedDate.after(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

    public static boolean isCurrentDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Date currentDate = new Date();
        return date != null && date.equals(currentDate);
    }

    public static boolean isDateInPast(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Date currentDate = new Date();
        return date != null && date.before(currentDate);
    }

    public static boolean isMoreThanSixMonthsFromNow(String dateString) {
            if(dateString.length()!=11)
                dateString="0"+dateString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate givenDate = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();

        long monthsDifference = ChronoUnit.MONTHS.between(currentDate, givenDate);

        return monthsDifference > 6;
    }



    public static void main(String[] args) {
        System.out.println(HelperClass.isMoreThanSixMonthsFromNow("30-Dec-2024"));
        System.out.println(HelperClass.isMoreThanSixMonthsFromNow("30-Apr-2024"));
    }

}