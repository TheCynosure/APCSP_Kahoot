import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jack on 5/20/17.
 */
public class AnswerPane extends JPanel {
    private Question question;

    public AnswerPane() {
        this.setLayout(new GridLayout(2,2));
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(i + "");
            button.setFont(new Font("Arial", Font.PLAIN, 26));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(actionEvent.getSource() instanceof JButton) {
                        if(((JButton)actionEvent.getSource()).getText().equals(question.rightAnswer)) { //If this is the right answer, Fire correct signal.
                            if (getRootPane().getParent() instanceof Window) {
                                ((Window) getRootPane().getParent()).answerCorrect();
                            }
                        } else { //Else fire incorrect signal.
                            if (getRootPane().getParent() instanceof Window) {
                                ((Window) getRootPane().getParent()).answerIncorrect();
                            }
                        }
                    }
                }
            });
            this.add(button);
        }
    }

    public void setNewQuestion(Question question) {
        this.question = question;
        for (int i = 0; i < 4; i++) {
            if(this.getComponent(i) instanceof JButton) {
                ((JButton)this.getComponent(i)).setText(new String(question.answers[i].toCharArray()));
            }
        }
    }
}
