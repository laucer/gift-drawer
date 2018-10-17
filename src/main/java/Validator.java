import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Validator {

    public static void assertEveryoneWillReceiveAGift(List<String> people, Map<String, List<String>> assignedPeople) {
        HashSet<String> receivals = new HashSet<>();

        assignedPeople.forEach(
                (buyer, receivers) -> receivals.addAll(receivers)
        );

        people.forEach(
                person -> {
                    if (!receivals.contains(person))
                        throw new RuntimeException("Drawing failed. " + person + " will not receive a gift.");
                }
        );
    }

    public static void assertEveryoneWillReceiveOneGift(List<String> people, Map<String, List<String>> assignedPeople) {
        HashMap<String, Integer> receivalCounts = new HashMap<>();

        assignedPeople.forEach(
                (buyer, receivers) -> receivers.forEach(
                        name -> {
                            if (!receivalCounts.containsKey(name))
                                receivalCounts.put(name, 1);
                            else {
                                receivalCounts.put(name,receivalCounts.get(name)+1);
                            }
                        }
                )
        );

        people.forEach(
                person -> {
                    if (receivalCounts.containsKey(person)){
                        int presentsCount = receivalCounts.get(person);
                        if (presentsCount != 1)
                            throw new RuntimeException("Drawing failed. " + person + " will receive " + presentsCount + " gifts.");
                    }
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
