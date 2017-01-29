import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class GUI extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;

    public GUI(String title) {
        super(title);
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        setFrameToCenter(this);


        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);


        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(100, 80, 80, 25);
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    setUpConnection();
                }

                super.keyPressed(e);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpConnection();
            }
        });
        panel.add(loginButton);

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new GUI("GithubApp");
                jFrame.setSize(300, 150);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });


    }

    /* Use the username and password from the textfields of login window to set up connections to github. */
    private void setUpConnection() {
        String username = userText.getText();
        String password = String.valueOf(passwordText.getPassword());
        Connector connector = new Connector(username, password);
        connector.connect();
        if(connector.isConnected()) {
            List<String> favLanguages = connector.getFavLanguages();
            UserProfileWindow(favLanguages);
        } else {
            userText.setText("");
            passwordText.setText("");
        }
    }

    /* Create new user profile window to show up the user's favourite language(s)
    *  after closing the login window. */
    private void UserProfileWindow(List<String> favLanguages) {

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
        String languages = "";
        for(String favLanguage: favLanguages) {
            languages += favLanguage + " ";
        }
        text.setText(languages + " ");
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
