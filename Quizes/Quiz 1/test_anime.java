import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class test_anime {

    @Test
    public void testLongerThanGiven() {

        Anime FMAB = new Anime(22, "Fullmetal Alchemist Brotherhood", true);

        assertEquals(FMAB.longerThanGiven(30), false);
        assertEquals(FMAB.longerThanGiven(15), true);
    }
}
