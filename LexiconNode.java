import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable{
    char letter;
    boolean isWord;

    // You also need a data structure for keeping track of the
    // children of this LexiconNode
    Vector<LexiconNode> node;
    // Constructor
    LexiconNode(char letter, boolean isWord) {
      this.letter = letter;
      this.isWord = isWord;
      this.node = new Vector<LexiconNode>();
    }

    /* Compare this LexiconNode to another.  You should just
     * compare the characters stored at the Lexicon Nodes.
     */

    public int compareTo(LexiconNode ln) {
      return this.letter - ln.letter;

    }

    /* Return letter stored by this LexiconNode */
    public char letter() {
	     return this.letter;
    }

    /* Add LexiconNode child to correct position in child data structure */
    public void addChild(LexiconNode ln) {
        for (int i = 0; i < node.size(); i++){
          if (ln.letter.compareTo(node.elementAt(i).letter) == 0){
            // Don't add
          } else {
            if (ln.letter.compareTo(node.elementAt(i).letter) < 0){
              node.insertElementAt(ln, i - 1);
              break;
          }
        }
      }
    }

    /* Get LexiconNode child for 'ch' out of child data structure */
    public LexiconNode getChild(char ch) {
      for (int i = 0; i < node.size(); i++){
        if (node.elementAt(i).letter - ch == 0){
          return node.elementAt(i);
        }
      }
    }

    /* Remove LexiconNode child for 'ch' from child data structure */
    public void removeChild(char ch) {
      for (int i = 0; i < node.size(); i++){
        if (node.elementAt(i).letter.equalTo(ch)){
          node.removeElementAt(i);
          break;
        }
      }
    }

    /* Iterate over children */
    public Iterator<LexiconNode> iterator() {
	     return null;
    }

    public static void main(String args[]){
      LexiconNode lex = new LexiconNode('g', false);
      LexiconNode xel = new LexiconNode('o', true);
      lex.addChild(xel);
      System.out.println(lex.toString());
      System.out.println(lex.getChild('o'));
      LexiconNode lexluthor = new LexiconNode('g', false);
      System.out.println(lexluthor.toString());
      System.out.println(lexluthor.getChild('o'));
    }
}
