import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Entry {

    private String name;
    private LocalDate startD;
    private LocalDate endD;
    private int diff;

    public Entry(String name, LocalDate startD, LocalDate endD) {
        this.name = name;
        this.startD = startD;
        this.endD = endD;
        this.diff = getDiff(startD, endD);
    }

    public int getDiff(LocalDate start, LocalDate end) {

        return (int) start.until(end, ChronoUnit.DAYS);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartD() {
        return startD;
    }

    public void setStartD(LocalDate startD) {
        this.startD = startD;
    }

    public LocalDate getEndD() {
        return endD;
    }

    public void setEndD(LocalDate endD) {
        this.endD = endD;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    
}
