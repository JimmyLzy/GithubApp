import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class ConnectorTest {
    @Test
    public void connect() throws Exception {
        String username = "123";
        String password = "123";
        Connector connector = new Connector(username, password);
        assertFalse(connector.isConnected());
        connector.connect();
        assertTrue(connector.isConnected());
    }

    @Test
    public void getFavLanguage() throws Exception {
        String username = "123";
        String password = "";
        Connector connector = new Connector(username, password);
        connector.connect();
        assertTrue(connector.getFavLanguages().size() == 1);
        assertTrue(connector.getFavLanguages().get(0).equals("JavaScript"));
    }

    @Test
    public void checkFavLanguageNotNuLL() throws Exception {
        String username = "msohn";
        String password = "";
        Connector connector = new Connector(username, password);
        connector.connect();
        assertTrue(connector.getFavLanguages().size() == 1);
        assertTrue(connector.getFavLanguages().get(0) != null);
        assertTrue(connector.getFavLanguages().get(0).equals("Java"));

    }

    @Test
    public void checkFavLanguageWithSameCount() throws Exception {
        String username = "JimmyLzy";
        String password = "";
        Connector connector = new Connector(username, password);
        connector.connect();
        assertTrue(connector.getFavLanguages().size() == 2);
        assertTrue(connector.getFavLanguages().get(0).equals("Java"));
        assertTrue(connector.getFavLanguages().get(1).equals("Arduino"));

    }
}