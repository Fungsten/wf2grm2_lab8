import structure5.*;
import java.util.Iterator;

public class LexiconTrie implements Lexicon {
    public boolean addWord(String word) {return true;}
    public int addWordsFromFile(String filename) {return 0;}
    public boolean removeWord(String word) {return true;}
    public int numWords() {return 0;}
    public boolean containsWord(String word){return true;}
    public boolean containsPrefix(String prefix){return true;}
    public Iterator<String> iterator() {return null;}
    public Set<String> suggestCorrections(String target, int maxDistance) {return null;}
    public Set<String> matchRegex(String pattern){return null;}
}