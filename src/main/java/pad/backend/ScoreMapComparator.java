package pad.backend;

import java.util.Comparator;
import java.util.Map;

public class ScoreMapComparator implements Comparator<Map<String, Object>> {
    private final String key;

    public ScoreMapComparator(String key) {
        this.key = key;
    }

    /**
     * Method created to compare scores, used in ScoreHandler in method getHighScoreList(), to put the scores in the
     * right order - from the highest to the lowest
     */
    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        Integer valueOne = (Integer) o1.get(key);
        Integer valueTwo = (Integer) o2.get(key);
        if (valueOne > valueTwo) { return 1; }
        else if (valueOne.equals(valueTwo)) { return 0;}
        else return -1;
    }
}
