import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class LanguageCounterTest {

    @Test
    public void increaseCount() throws Exception {
        LanguageCounter counter = new LanguageCounter("Java", 16);
        int count = counter.getCount();
        counter.increaseCount();
        assertTrue(counter.getCount() == count + 1);
    }

    @Test
    public void getCount() throws Exception {
        LanguageCounter counter = new LanguageCounter("Java", 1);
        int count = counter.getCount();
        assertTrue(count == 1);
    }

}