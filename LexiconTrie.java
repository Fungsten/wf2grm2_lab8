import structure5.*;
import java.util.Scanner;
import java.util.Iterator;

public class LexiconTrie implements Lexicon {

    LexiconNode groot;
    Vector<String> wordList;

    LexiconTrie(){
      //I am Groot
      this.groot = new LexiconNode(' ', false);
      this.wordList = new Vector<String>();
    }


    public boolean addWord(String word) {
      if (containsWord(word)){
        return false;
      } else {
        this.wordList.add(word);
        return true;
      }
    }

    public int addWordsFromFile(String filename) {

      Scanner scan = new Scanner(new FileStream(filename));
      int counter = 0;

      while (scan.hasNext()){
        addWord(scan.next());
        counter++;
      }
      return counter;
    }

    public boolean removeWord(String word) {
      if (containsWord(word)){
        this.wordList.remove(word);
        return true;
      } else {
        return false;
      }
    }

    public int numWords() {
      return this.wordList.size();
    }

    public boolean containsWord(String word){
      return this.wordList.contains(word);
    }

    public boolean containsPrefix(String prefix){
      if (prefix.equals("")){
        return true;
      } else {
        for (int i = 0; i < this.wordList.size(); i++){
          if (this.wordList.elementAt(i).startsWith(prefix)){
            return true;
          }
        }
        return false;
      }
    }

    public Iterator<String> iterator() {
      Iterator<String> it = wordList.iterator();
      it.sortAlphabetically();
      return it;
    }

    public Set<String> suggestCorrections(String target, int maxDistance) {
      return null;
    }

    public Set<String> matchRegex(String pattern){
      return null;
    }

    public static void main(String args[]){
      LexiconTrie chrisPratt =  new LexiconTrie();
      chrisPratt.addWordsFromFile("small.txt");
      System.out.println(chrisPratt.wordList.toString());
      chrisPratt.removeWord("top");
      System.out.println(chrisPratt.wordList.toString());
      System.out.println(chrisPratt.numWords());
      System.out.println(chrisPratt.containsPrefix("to"));
    }
}
