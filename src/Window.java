import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by john_bachman on 5/16/17.
 */
public class Window extends JFrame {

    //TODO:: Score Tracking

    private JPanel gamePanel;
    private QuestionPane questionPane;
    private AnswerPane answerPane;
    private ResultPane resultPane;
    private JPanel rootPane;
    private CardLayout layout;
    private QuestionLoader questionLoader = new QuestionLoader("Questions.txt");

    public Window() {
        super();
        layout = new CardLayout();
        rootPane = new JPanel();
        resultPane = new ResultPane();
        gamePanel = new JPanel();
        addSubPanels();

        this.setSize(600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rootPane.setLayout(layout);
        this.add(rootPane);
        rootPane.add(gamePanel, "Game Panel");
        rootPane.add(resultPane, "Result Panel");
        this.setVisible(true);

        Timer questionTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(gamePanel.getComponent(0) instanceof QuestionPane) {
                    ((QuestionPane) gamePanel.getComponent(0)).secondPassed();
                }
            }
        });

        questionTimer.start();
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        if(questionLoader.hasNextQuestion()) {
            Question question = questionLoader.nextQuestion();
            questionPane.setNewQuestion(question);
            answerPane.setNewQuestion(question);
        } else {
            //TODO: Deal with there being no more questions.
        }
    }

    private void addSubPanels() {
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add((questionPane = new QuestionPane()), BorderLayout.NORTH);
        gamePanel.add((answerPane = new AnswerPane()), BorderLayout.CENTER);
    }

    public void answerCorrect() {
        resultPane.setResultText("Correct");
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
    }

    public void answerIncorrect() {
        resultPane.setResultText("Incorrect");
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
    }

    public void answerOutOfTime() {
        resultPane.setResultText("Out of Time");
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
    }

    public void toggleGamePanel() {
        layout.first(rootPane);
        loadNextQuestion();
    }

}
