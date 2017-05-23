import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by john_bachman on 5/16/17.
 */
public class Window extends JFrame {

    private JPanel gamePanel;
    private QuestionPane questionPane;
    private AnswerPane answerPane;
    private ResultPane resultPane;
    private JPanel rootPane;
    private CardLayout layout;
    private QuestionLoader questionLoader = new QuestionLoader("Questions.txt");
    private int Score,QAsked;

    private String[] colorArr = new String[] {
            "#DEF2C8",
            "#BBADA0",
            "#A9E5BB",
            "#F7B32B",
            "#F6E8EA"
    };

    public Window() {
        super();
        layout = new CardLayout();
        rootPane = new JPanel();
        resultPane = new ResultPane();
        gamePanel = new JPanel();
        Score = 0;
        QAsked = 0;
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
        Color color = Color.decode(colorArr[(int) (Math.random() * colorArr.length)]);
        gamePanel.setBackground(color);
        questionPane.setBackground(color);
        answerPane.setBackground(color);
        resultPane.setBackground(color);
    }

    private void loadNextQuestion() {
        if(questionLoader.hasNextQuestion()) {
            Question question = questionLoader.nextQuestion();
            questionPane.setNewQuestion(question);
            answerPane.setNewQuestion(question);
            QAsked++;
        }
    }

    private void addSubPanels() {
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add((questionPane = new QuestionPane()), BorderLayout.NORTH);
        gamePanel.add((answerPane = new AnswerPane()), BorderLayout.CENTER);
    }

    public void answerCorrect() {
        Score++;
        resultPane.setResultText("Correct");
        resultPane.setScoreText("Score = "+Score+"/"+QAsked);
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
        playSoundEffect("Right.wav");
    }

    public void answerIncorrect() {
        resultPane.setResultText("Incorrect");
        resultPane.setScoreText("Score = "+Score+"/"+QAsked);
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
        playSoundEffect("Wrong.wav");
    }

    public void answerOutOfTime() {
        resultPane.setResultText("Out of Time");
        resultPane.setScoreText("Score = "+Score+"/"+QAsked);
        questionPane.questionBeingAsked = false;
        layout.next(rootPane);
        playSoundEffect("Wrong.wav");
    }

    public void toggleGamePanel() {
        if(!questionLoader.hasNextQuestion()) {
            rootPane.add(new EndGamePane(Score, QAsked));
            layout.last(rootPane);
        } else {
            layout.first(rootPane);
            loadNextQuestion();
        }
        Color color = Color.decode(colorArr[(int) (Math.random() * colorArr.length)]);
        gamePanel.setBackground(color);
        questionPane.setBackground(color);
        answerPane.setBackground(color);
        resultPane.setBackground(color);
    }

    private void playSoundEffect(String name) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(name)));
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if(event.getType() == LineEvent.Type.CLOSE) {
                        clip.stop();
                        clip.drain();
                    }
                }
            });
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
