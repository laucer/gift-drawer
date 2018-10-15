import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<String> participants = Arrays.asList(
                "Luizka",
                "Lucjan",
                "Honoratka",
                "Mateusz",
                "Emil",
                "Ewa",
                "Mama",
                "Tata",
                "Grzesiek",
                "Laura",
                "Klara",
                "Babcia + Dziadek",
                "Kacper",
                "Ciocia Jola",
                "Ciocia Danusia"
        );
        List<String> buyers = Arrays.asList(
                "Ewa",
                "Emil",
                "Mateusz",
                "Honoratka",
                "Lucjan",
                "Luizka"
        );
        GiftAssigner giftAssigner = new GiftAssigner(participants, buyers);
        ResultSaver.save(giftAssigner.assign());
    }

}
