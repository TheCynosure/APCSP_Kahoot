/**
 * Created by jack on 5/21/17.
 */
public class Question {

    protected int time;
    protected String Question;
    protected String rightAnswer;
    protected String[] answers;

    public Question(String questionString) {
        String[] questionStringArr = questionString.split("\n");
        time = Integer.parseInt(questionStringArr[0]);
        Question = questionStringArr[1];
        rightAnswer = questionStringArr[2];
        answers = new String[] {questionStringArr[2], questionStringArr[3], questionStringArr[4], questionStringArr[5]};
        answers = randomizeArr(answers);
    }

    private String[] randomizeArr(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int firstRando = (int) (Math.random() * arr.length);
            int secondRando = (int) (Math.random() * arr.length);
            String temp = arr[firstRando];
            arr[firstRando] = arr[secondRando];
            arr[secondRando] = temp;
        }
        return arr;
    }
}
