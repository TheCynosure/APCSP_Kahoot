import javax.swing.*;

/**
 * Created by john_bachman on 5/16/17.
 */
public class Window extends JFrame {

    private JPanel gamePanel;

    public Window() {
        super();
        gamePanel = new JPanel();

        this.setSize(400,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setVisible(true);
    }
}
