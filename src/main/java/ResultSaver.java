import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ResultSaver {

    public static void save(Map<String, List<String>> assignedPeople) {
        assignedPeople.forEach(
                (person, recipients) -> {
                    try (PrintWriter out = new PrintWriter(person + ".txt")) {
                        out.println(recipients);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
