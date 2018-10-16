import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ResultSaver {

    public static void save(Map<String, List<String>> assignedPeople) {
        File dir = new File("output");
        dir.mkdirs();
        assignedPeople.forEach(
                (person, recipients) -> {
                    try (PrintWriter out = new PrintWriter("output/" + person + ".txt")) {
                        out.println(recipients);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
