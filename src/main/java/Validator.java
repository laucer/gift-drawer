import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Validator {

    public static void assertEveryoneWillReceiveAGift(List<String> people, Map<String, List<String>> assignedPeople) {
        people.forEach(
                person -> {
                    AtomicBoolean foundMatch = new AtomicBoolean(false);
                    assignedPeople.forEach(
                            (buyer, receivers) -> receivers.forEach(
                                    name -> {
                                        if (name.equals(person))
                                            foundMatch.set(true);
                                    }
                            )
                    );
                    if (!foundMatch.get())
                        throw new RuntimeException("Drawing failed. " + person + " will not receive a gift.");
                }
        );

    }

    public static void assertProperNumberOfFiles(List<String> buyers) {
        buyers.forEach(
                name -> {
                    File giftFile = new File("output/" + name + ".txt");
                    if (!giftFile.exists())
                        throw new RuntimeException("File for " + name + " was not created.");
                }
        );
    }

    public static void assertBuyerDoNotBuyAGiftForHimself(Map<String, List<String>> assignedPeople) {
        assignedPeople.forEach(
                (buyer, receivers) -> {
                    receivers.forEach(
                            name -> {
                                if (name.equals(buyer))
                                    throw new RuntimeException("Buyer " + buyer + " buys a gift for himself.");
                            }
                    );
                }
        );
    }

    public static void assertProperNumberOfReceiversForEveryBuyer(
            Map<String, List<String>> assignedPeople,
            int numberOfParticipants,
            int numberOfBuyers
    ) {
        int minNumberOfReceivers = numberOfParticipants / numberOfBuyers;
        int maxNumberOfReceivers = minNumberOfReceivers + 1;
        assignedPeople.forEach(
                (buyer, receivers) -> {
                    if (receivers.size() != minNumberOfReceivers && receivers.size() != maxNumberOfReceivers)
                        throw new RuntimeException("Wrong number of receivers for " + buyer + ".");
                }
        );
    }

}
