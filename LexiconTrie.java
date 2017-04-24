/*
Will Fung and Grace Mazzarella

would like their grader to know that they are trying not to do funny indention things,
but that they very much like editing in Atom, which may or may not do funny things.
Also, they were constantly pushing and pulling with Github, which might do weird
things too.

Thought Question:
Using the trie-based implementation is more efficient. The trie saves memory space
by not creating a massive vector the size of the Oxford dictionary, so there is efficiency
in data to space ratio. One can also find most relevant spelling suggestions by
following similar branches along a trie rather than sifting through the vector until
reaching the desired word(s). The latter would save time in both creation of the
data structure and in using it.
*/

import structure5.*;
import java.util.Scanner;
import java.util.Iterator;

public class LexiconTrie implements Lexicon {

    LexiconNode groot;
    //Vector<String> wordList;   //used as debugging tool
    int wordCount= 0;

    LexiconTrie(){
      this.groot = new LexiconNode(' ', false); //I am Groot
      //this.wordList = new Vector<String>();   //used as debugging tool
    }

    public boolean addWord(String word) {
      if (containsWord(word)){
        return false;
      } else {
        //this.wordList.add(word);    //used as debugging tool
        LexiconNode currentParent = groot;
        for (int i = 0; i < word.length(); i++){
          char ch = word.charAt(i);
          if (currentParent.getChild(ch) == null){
            LexiconNode babygroot; //i am groot
            if (i == word.length() - 1){
              babygroot = new LexiconNode(ch, true); //if about to add a node which is the end of a word
            } else {
              babygroot = new LexiconNode(ch, false);//if not
            }
            currentParent.addChild(babygroot);//addChild already checks for duplicates
            currentParent = babygroot; //switches over to looking at the child node
          } else {
            currentParent = currentParent.getChild(ch);
          }
        }
        currentParent.isWord = true;
        wordCount++; //have a parallel
        return true;
      }
    }

    //scanner to add words from a text file
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
      if (containsWord(word)){
        //this.wordList.remove(word);

        LexiconNode lastgroot = groot;
        int trueCounter = 0;
        for (int i = 0; i < word.length(); i++){
          lastgroot = lastgroot.getChild(word.charAt(i));
          if (lastgroot.isWord == true){
            trueCounter++;
          }
        }
        lastgroot.isWord = false;
        wordCount--;

        Iterator<LexiconNode> subsequentWords = lastgroot.iterator();
        //checks if the last node has any children, if not, execute order 66
        if (subsequentWords.hasNext() == false){
          LexiconNode currentgroot = groot;

          for (int i = 0; i < word.length() - 1; i++){
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
      return wordCount;
    }

    public boolean containsWord(String word){
      LexiconNode currentgroot = groot;
      for (int i = 0; i < word.length(); i++){
        if (currentgroot.getChild(word.charAt(i)) == null){
          return false;
        } else {
          currentgroot = currentgroot.getChild(word.charAt(i));
        }
      }
      return currentgroot.isWord;
    }

    public boolean containsPrefix(String prefix){
      LexiconNode currentgroot = groot;
      for (int i = 0; i < prefix.length(); i++){
        if (currentgroot.getChild(prefix.charAt(i)) == null){
          return false;
        } else {
          currentgroot = currentgroot.getChild(prefix.charAt(i));
        }
      }
      return true;
    }

    public Iterator<String> iterator(){
      Vector<String> theDictionary = new Vector<String>();
      LexiconNode currentGroot = groot;
      String word = "";
      Iterator<LexiconNode> iter = currentGroot.iterator();
      iterHelper(theDictionary, currentGroot, word, iter);
      Iterator<String> readDict = theDictionary.iterator();
      return readDict;
    }

    protected void iterHelper(Vector<String> dict, LexiconNode currGroot, String word, Iterator<LexiconNode> iter) {
      // builds theDictionary
      while (iter.hasNext()){ //so long as parent has children, will loop through them
        LexiconNode currChild = iter.next(); //takes next child
        word = word + currChild.letter(); //builds the word with the child's letter
        if (currChild.isWord == true){
          dict.add(word); //adds currently building word to dictionary if it's a word
        }
        Iterator<LexiconNode> childIter = currChild.iterator(); //an iterator for the children of the child
        if (childIter.hasNext()){ //condition that
          //System.out.println("continue" + word);

          iterHelper(dict, currChild, word, childIter);
          word = word.substring(0, word.length() - 1); //resets word

        } else {
          // Nothing following, need new String and to walk down new node
          word = word.substring(0, word.length() - 1);
        }
      }
    }


    public SetVector<String> suggestCorrections(String target, int maxDistance) {
      LexiconNode currGroot = groot;
      Iterator<LexiconNode> iter = currGroot.iterator();
      SetVector<String> theDictionary = new SetVector<String>();
      String word = "";
      correctionsHelper(theDictionary, currGroot, target, word, iter, maxDistance);
      return theDictionary;
  }

  protected void correctionsHelper(SetVector<String> dict, LexiconNode currGroot, String target, String word, Iterator<LexiconNode> iter, int maxDistance) {
    // builds theDictionary
    while (iter.hasNext()){ //so long as parent has children, will loop through them
      LexiconNode currChild = iter.next(); //takes next child
      word = word + currChild.letter(); //builds the word with the child's letter
      int currDistance = maxDistance;
      for (int i = 0; i < word.length() && i < target.length(); ++i){
          if (word.charAt(i) != target.charAt(i)){
            --currDistance;
        }
      }
      if (word.length() == target.length()){
        if (currDistance >= 0){
          if (currChild.isWord == true){
            System.out.println(word);
            dict.add(word); //adds currently building word to dictionary if it's a word
          }
        }
      }

      Iterator<LexiconNode> childIter = currChild.iterator(); //an iterator for the children of the child
      if (childIter.hasNext()){ //condition that
        //System.out.println("continue" + word);

        correctionsHelper(dict, currChild, target, word, childIter, maxDistance);
        word = word.substring(0, word.length() - 1); //resets word

      } else {
        // Nothing following, need new String and to walk down new node
        word = word.substring(0, word.length() - 1);
      }
    }
  }

    public SetVector<String> matchRegex(String pattern){
      SetVector<String> matches = new SetVector<String>();
      LexiconNode currGroot = groot;
      Iterator<LexiconNode> iter = currGroot.iterator();
      matchesHelper(pattern, matches, currGroot, iter);
      return matches;
    }

    protected void matchesHelper(String pattern, SetVector<String> dictionary, LexiconNode currGroot, Iterator<LexiconNode> iter){
      String word = "";
        if (pattern.length() == 0){
          dictionary.add(word);
        }
        if (pattern.charAt(0) == '*'){
          //iterate through til end
          if (pattern.charAt(1) != 0){
            while (iter.hasNext()){
              currGroot = iter.next(); //takes next child
              word = word + currGroot.letter(); //builds the word with the child's letter
              Iterator<LexiconNode> childIter = currGroot.iterator(); //an iterator for the children of the child
              matchesHelper(pattern.substring(1, pattern.length()), dictionary, currGroot, childIter);
            }
          } else {
            while (iter.hasNext()){
              currGroot = iter.next(); //takes next child
              word = word + currGroot.letter(); //builds the word with the child's letter
              Iterator<LexiconNode> childIter = currGroot.iterator(); //an iterator for the children of the child
              matchesHelper(pattern.substring(1, pattern.length()), dictionary, currGroot, childIter);
            }
          }
        } else if (pattern.charAt(0) == '?'){
          while (iter.hasNext()){
            currGroot = iter.next(); //takes next child
            word = word + currGroot.letter(); //builds the word with the child's letter
            Iterator<LexiconNode> childIter = currGroot.iterator(); //an iterator for the children of the child
            matchesHelper(pattern.substring(1, pattern.length()), dictionary, currGroot, childIter);
          }
          //iterate through just this level of children
          //then matches
        } else {
          if (currGroot.getChild(pattern.charAt(0)) == null){
            word = ""; //no matches, erases currently building word
          } else {
            while (iter.hasNext()){
              currGroot = iter.next(); //takes next child
              word = word + currGroot.letter(); //builds the word with the child's letter
              Iterator<LexiconNode> childIter = currGroot.iterator(); //an iterator for the children of the child
              matchesHelper(pattern.substring(1, pattern.length()), dictionary, currGroot, childIter);
            }
          }
        }

    }


    //We are Groot.
    public static void main(String args[]){
      LexiconTrie chrisPratt =  new LexiconTrie();
      chrisPratt.addWordsFromFile("small.txt");

      /*Iterator<String> starLord = chrisPratt.iterator();
      while (starLord.hasNext()){
        System.out.println("iterator test: " + starLord.next());*/
      //System.out.println("wha is word?: " + chrisPratt.groot.getChild('w').getChild('h').getChild('a').isWord);
      //System.out.println("wh is word?: " + chrisPratt.groot.getChild('w').getChild('h').isWord);
      //chrisPratt.removeWord("wh");
      //System.out.println("wh is word?: " + chrisPratt.groot.getChild('w').getChild('h').isWord);
      //System.out.println("Node to string: " + chrisPratt.groot.getChild('w').getChild('h').getChild('a').letter);

      //Assert.pre(CND, "Error msg");
      }
    }
