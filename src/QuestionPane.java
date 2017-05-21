import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by jack on 5/20/17.
 */
public class QuestionPane extends JPanel {

    public JLabel questionLabel;
    public JLabel timerLabel;
    protected boolean questionBeingAsked = true;
    private int timeLeft;
    private Question currentQuestion;

    public QuestionPane() {
        questionLabel = new JLabel("Question Goes Here");
        timeLeft = 0;
        timerLabel = new JLabel("60s");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(questionLabel);
        questionLabel.setBorder(new EmptyBorder(0, 0, 0, 50));
        this.add(timerLabel);
    }

    public void setNewQuestion(Question question) {
        currentQuestion = question;
        timeLeft = question.time;
        timerLabel.setText(timeLeft + "s");
        questionBeingAsked = true;
        questionLabel.setText(question.Question);
    }

    public synchronized void secondPassed() {
        if(questionBeingAsked) {
            timerLabel.setText((timeLeft--) + "s");
            if(timeLeft <= 0) {
                ((Window)getRootPane().getParent()).answerOutOfTime();
            }
        }
    }
}
