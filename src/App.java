import javax.swing.SwingUtilities;

import view.LoginScreen;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
