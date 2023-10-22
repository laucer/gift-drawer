import java.util.*;

public class GiftAssigner {

    private static final int MAX_DRAW_ATTEMPTS = 50;
    private final List<String> participants;
    private final List<String> buyers;
    private final Random random = new Random();

    public GiftAssigner(List<String> participants, List<String> buyers) {
        this.participants = participants;
        this.buyers = buyers;
    }

    public Map<String, List<String>> assign() throws InterruptedException {
        Map<String, List<String>> assignedPeople;
        int attempts = 0;
        do {
            if (attempts > MAX_DRAW_ATTEMPTS)
                throw new RuntimeException("Could not draw. Infinite loop");
            assignedPeople = tryToAssign();
            attempts++;
        } while (assignedPeople == null);
        return assignedPeople;
    }

    // returns null if can't assign. Example buyers = {A}. Paricipants = {A}
    private Map<String, List<String>> tryToAssign() throws InterruptedException {
        List<String> peopleToAssign = new ArrayList<>(this.participants);
        Map<String, List<String>> assignedPeople = createResultMap();
        int currentBuyer = 0;
        while (!peopleToAssign.isEmpty()) {
            int recipientIndex = getRecipientIndex(buyers.get(currentBuyer), peopleToAssign);
            if (recipientIndex == -1)
                return null;
            String buyer = buyers.get(currentBuyer);
            if (buyer.equals("Ewa") && !assignedPeople.get("Ewa").isEmpty()) {
                continue;
            }
            String recipient = peopleToAssign.get(recipientIndex);
            assignedPeople.get(buyer).add(recipient);
            currentBuyer = (currentBuyer + 1) % buyers.size();
            peopleToAssign.remove(recipientIndex);
        }
        return assignedPeople;
    }

    private Map<String, List<String>> createResultMap() {
        Map<String, List<String>> result = new HashMap<>();
        buyers.forEach(
                buyer -> {
                    result.put(buyer, new ArrayList<>());
                }
        );
        return result;
    }

    private int getRecipientIndex(String buyer, List<String> peopleToAssign) throws InterruptedException {
        int recipientIndex;
        int attempts = 0;
        do {
            if (attempts > MAX_DRAW_ATTEMPTS)
                return -1;
            recipientIndex = random.nextInt(peopleToAssign.size());
            attempts++;
            Thread.sleep(10);
        }
        while (buyer.equals(peopleToAssign.get(recipientIndex)));
        return recipientIndex;
    }
}
