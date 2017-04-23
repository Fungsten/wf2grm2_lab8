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
      if (this.wordList.contains(word)){
        return false;
      } else {
        this.wordList.add(word);
        LexiconNode currentParent = groot;
        for (int i = 0; i < word.length(); i++){
          char ch = word.charAt(i);
          if (currentParent.getChild(ch) == null){
            //i am groot
            LexiconNode babygroot;
            if (i == word.length() - 1){
              babygroot = new LexiconNode(ch, true);
            } else {
              babygroot = new LexiconNode(ch, false);
            }
            //addChild already checks for duplicates
            currentParent.addChild(babygroot);
            currentParent = babygroot;
          } else {
            currentParent = currentParent.getChild(ch);
          }
        }
        currentParent.isWord = true;
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

    /*
    if last child has a child,
      change true tag to false tag

    if last child does not have a child,
      remove child
      recurse
    */
    public boolean removeWord(String word) {
      if (this.wordList.contains(word)){
        this.wordList.remove(word);

        LexiconNode lastgroot = groot;
        int trueCounter = 0;
        for (int i = 0; i < word.length(); i++){
          lastgroot = lastgroot.getChild(word.charAt(i));
          if (lastgroot.isWord == true){
            trueCounter++;
          }
        }
        lastgroot.isWord = false;

        Iterator<LexiconNode> subsequentWords = lastgroot.iterator();
        //checks if the last node has any children, if not, execute order 66
        if (subsequentWords.hasNext() == false){
          LexiconNode currentgroot = groot;

          for (int i = 0; i < word.length(); i++){
            currentgroot = currentgroot.getChild(word.charAt(i));

            if (currentgroot.isWord == true){
              trueCounter--;

              if (trueCounter == 1){
                currentgroot.removeChild(word.charAt(i + 1));
              }
            }
          }
        }
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
          if (this.wordList.elementAt(i).toLowerCase().startsWith(prefix.toLowerCase())){
            return true;
          }
        }
        return false;
      }
    }

    public Iterator<String> iterator() {
      Iterator<String> it = wordList.iterator();
      //it.sortAlphabetically();
      return it;
    }


    public Set<String> suggestCorrections(String target, int maxDistance) {
      return null;
    }

    public Set<String> matchRegex(String pattern){

      return null;
    }


    //We are Groot.
    public static void main(String args[]){
      LexiconTrie chrisPratt =  new LexiconTrie();
      //chrisPratt.addWordsFromFile("small.txt");
      //System.out.println(chrisPratt.wordList.toString());
      //chrisPratt.removeWord("top");
      //System.out.println(chrisPratt.wordList.toString());
      System.out.println(chrisPratt.numWords());
      System.out.println(chrisPratt.containsPrefix("so"));
      chrisPratt.addWord("potato");
      chrisPratt.addWord("wow");
      chrisPratt.addWord("wha");
      chrisPratt.addWord("wh");
      //chrisPratt.addWord("whamo");
      System.out.println("Node to string: " + chrisPratt.groot.node.toString());
      System.out.println("wha is word?: " + chrisPratt.groot.getChild('w').getChild('h').getChild('a').isWord);
      System.out.println("wh is word?: " + chrisPratt.groot.getChild('w').getChild('h').isWord);
      chrisPratt.removeWord("wh");
System.out.println("wh is word?: " + chrisPratt.groot.getChild('w').getChild('h').isWord);
      System.out.println("Node to string: " + chrisPratt.groot.getChild('w').getChild('h').getChild('a').letter);
    }
}
