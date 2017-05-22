import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jack on 5/21/17.
 */
public class ResultPane extends JPanel {

    private JLabel result,score;
    private JButton nextButton;

    public ResultPane() {
        result = new JLabel("Result", SwingConstants.CENTER);
        this.setLayout(new GridLayout(3,1));
        result.setFont(new Font("Arial", Font.BOLD, 60));
        this.add(result);
        score = new JLabel("Score", SwingConstants.CENTER);
        score.setFont(new Font("Arial", Font.BOLD, 60));
        this.add(score);
        nextButton = new JButton();
        nextButton.setText("Next Question");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 25));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(getRootPane().getParent() instanceof Window) {
                    ((Window)getRootPane().getParent()).toggleGamePanel();
                }
            }
        });
        this.add(nextButton);
    }

    public void setResultText(String resultText) {
        result.setText(resultText);
    }
    public void setScoreText(String scoreText) {score.setText(scoreText);}

}
