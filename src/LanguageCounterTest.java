import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class LanguageCounterTest {

    @Test
    public void testIncreaseCount() {
        LanguageCounter counter = new LanguageCounter("Java", 16);
        int count = counter.getCount();
        counter.increaseCount();
        assertTrue(counter.getCount() == count + 1);
    }

}
