import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GiftAssignerTest {

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDueToNoPossibleResult() throws InterruptedException {
        List<String> participants = Arrays.asList("Lucjan");
        List<String> buyers = Arrays.asList("Lucjan");
        GiftAssigner giftAssigner = new GiftAssigner(participants, buyers);
        giftAssigner.assign();
    }

    @Test
    public void shouldAssignBuyers() throws InterruptedException {
        List<String> participants = Arrays.asList("Lucjan", "Luizka", "Ewa", "Mateusz");
        List<String> buyers = Arrays.asList("Lucjan", "Luizka");
        GiftAssigner giftAssigner = new GiftAssigner(participants, buyers);
        Map<String, List<String>> assignedPeople = giftAssigner.assign();
        List<String> recipients1 = assignedPeople.get("Lucjan");
        List<String> recipients2 = assignedPeople.get("Luizka");
        assertEquals(recipients1.size(), 2);
        assertEquals(recipients2.size(), 2);
        assertListNotContains(recipients1, "Lucjan");
        assertListNotContains(recipients2, "Luzka");
    }

    private void assertListNotContains(List<String> recipients, String name) {
        recipients.forEach(
                recipient -> {
                    assertNotEquals(recipient, name);
                }
        );
    }

}