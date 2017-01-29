import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class ConnectorTest {

    @Test
    public void testComputeFavLanguage() {
        String username = "123";
        String password = "123";
        Connector connector = new Connector(username, password);
        connector.connect();
        assertTrue(connector.getFavLanguage().equals("JavaScript"));

    }
}
