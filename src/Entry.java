import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Entry implements Serializable {

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

    public void save() throws IOException{
        FileOutputStream fileOut = new FileOutputStream("EntryInfo.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    public void desave() throws IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream("//home//thea//Dokumente//GitHub//countdown//EntryInfo.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        in.readObject();
        in.close();
        fileIn.close();
        
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
