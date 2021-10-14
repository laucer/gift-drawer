import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
                "Babcia",
                "CiociaJola",
                "CiociaDanusia",
                "Jeremiasz"
        );
        List<String> buyers = Arrays.asList(
                "Ewa",
                "Emil",
                "Mateusz",
                "Honoratka",
                "Lucjan",
                "Luizka",
                "CiociaJola",
                "CiociaDanusia",
                "Mama",
                "Tata"
        );
        Collections.shuffle(buyers);
        GiftAssigner giftAssigner = new GiftAssigner(participants, buyers);
        Map<String, List<String>> assignedPeople = giftAssigner.assign();
        Validator.assertEveryoneWillReceiveAGift(participants, assignedPeople);
        Validator.assertEveryoneWillReceiveOneGift(participants, assignedPeople);
        Validator.assertBuyerDoNotBuyAGiftForHimself(assignedPeople);
        Validator.assertProperNumberOfReceiversForEveryBuyer(assignedPeople, participants.size(), buyers.size());
        ResultSaver.save(assignedPeople);
        Validator.assertProperNumberOfFiles(buyers);
    }

}
