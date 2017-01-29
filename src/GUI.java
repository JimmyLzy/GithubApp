import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class GUI extends JFrame {


    public GUI(String title) {
        super(title);
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        setFrameToCenter(this);


        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);


        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(100, 80, 80, 25);
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    String username = userText.getText();
                    String password = String.valueOf(passwordText.getPassword());
                    Connector connector = new Connector(username, password);
                    connector.connect();
                    String favLanguage = connector.getFavLanguage();
                    UserProfileWindow(favLanguage);
                }

                super.keyPressed(e);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                Connector connector = new Connector(username, password);
                connector.connect();
                String favLanguage = connector.getFavLanguage();
                UserProfileWindow(favLanguage);
            }
        });
        panel.add(loginButton);

    }

    private void UserProfileWindow(String favLanguage) {

        this.setVisible(false);
        this.dispose();

        JFrame jFrame = new JFrame("User profile");
        jFrame.setSize(300, 150);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        JPanel panel = new JPanel();
        jFrame.add(panel);
        panel.setLayout(null);
        setFrameToCenter(jFrame);

        JLabel label = new JLabel("Favourite Language: ");
        label.setBounds(10, 10, 150, 25);
        panel.add(label);


        JTextField text = new JTextField(50);
        text.setBounds(100, 50, 160, 25);
        panel.add(text);
        text.setText(favLanguage);
    }

    private void setFrameToCenter(JFrame jFrame) {
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int width = jFrame.getSize().width;
        int height = jFrame.getSize().height;
        int x = (dim.width-width)/2;
        int y = (dim.height-height)/2;

        // Move the window
        jFrame.setLocation(x, y);
    }
}
