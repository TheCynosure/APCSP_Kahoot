import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by jack on 5/21/17.
 */
public class QuestionLoader {
    private String questionFilePath;
    private ArrayList<Question> questions;
    private int questionIndex = 0;

    public QuestionLoader(String path) {
        questions = new ArrayList<>();
        questionFilePath = path;
        loadQuestions();
        for(int i = 0; i < questions.size(); i++) { // Shuffle the Questions
            int firstRando = (int) (Math.random() * questions.size());
            int secondRando = (int) (Math.random() * questions.size());
            Question temp = questions.get(firstRando);
            questions.set(firstRando, questions.get(secondRando));
            questions.set(secondRando, temp);
        }
    }

    private void loadQuestions() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(questionFilePath)));
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                if(!line.startsWith("#")) { //Ignore Comments
                    //Load 6 Lines
                    String questionString = "";
                    questionString += line + "\n";
                    for(int i = 0; i < 5; i++) {
                        questionString += bufferedReader.readLine() + "\n";
                    }
                    questions.add(new Question(questionString));
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public Question nextQuestion() {
        questionIndex++;
        return questions.get(questionIndex - 1);
    }

    public boolean hasNextQuestion() {
        if(questionIndex < questions.size())
            return true;
        return false;
    }
}
