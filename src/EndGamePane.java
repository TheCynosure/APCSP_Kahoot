import javax.swing.*;
import java.awt.*;

/**
 * Created by john_bachman on 5/22/17.
 */
public class EndGamePane extends JPanel {

    private JLabel score;

    public EndGamePane(int scoreNum, int questionMax) {
        this.setLayout(new GridLayout(8, 1));
        JLabel label = new JLabel("END GAME, GO HOME", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        this.add(label);
        score = new JLabel("Score: " + scoreNum + "/" + questionMax, SwingConstants.CENTER);
        score.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(score);
    }
}
