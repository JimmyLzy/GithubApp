/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class LanguageCounter {

    private final String language;
    private int count = 0;

    public LanguageCounter(String language, int count) {
        this.language = language;
        this.count = count;
    }

    public void increaseCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
