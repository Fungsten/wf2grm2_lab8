import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable{
    char letter;
    boolean isWord;
    Vector<LexiconNode> node;

    // You also need a data structure for keeping track of the
    // children of this LexiconNode
    //Vector<LexiconNode> node = new Vector<LexiconNode>();
    // Constructor
    LexiconNode(char letter, boolean isWord) {
      this.letter = letter;
      this.isWord = isWord;
      this.node = new Vector<LexiconNode>();
    }

    /* Compare this LexiconNode to another.  You should just
     * compare the characters stored at the Lexicon Nodes.
     */

    public int compareTo(Object o) {
      //cast o to a lexicon node
      return this.letter - ((LexiconNode)o).letter;

    }

    /* Return letter stored by this LexiconNode */
    public char letter() {
	     return this.letter;
    }

    /* Add LexiconNode child to correct position in child data structure */
    public void addChild(LexiconNode ln) {
      if (node.contains(ln)){
      } else {
        for (int i = 0; i <= node.size(); i++){
          if (node.size() == 0 || ln.compareTo(node.elementAt(i)) > 0){
            node.add(ln);
            break;
          } else if (ln.compareTo(node.elementAt(i)) < 0){
            //System.out.println("i");
            node.insertElementAt(ln, i);
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
      return null;
    }

    /* Remove LexiconNode child for 'ch' from child data structure */
    public void removeChild(char ch) {
      for (int i = 0; i < node.size(); i++){
        if (node.elementAt(i).letter - ch == 0){
          node.removeElementAt(i);
          break;
        }
      }
    }

    /* Iterate over children */
    public Iterator<LexiconNode> iterator() {

    }

    public static void main(String args[]){
      LexiconNode lex = new LexiconNode('g', false);
      LexiconNode xel = new LexiconNode('o', true);
      lex.addChild(xel);
      System.out.println("Node to string: " + lex.node.toString());
      System.out.println("The CHILD: " + lex.getChild('o'));
      LexiconNode lexluthor = new LexiconNode('g', false);
      lex.addChild(lexluthor);
      System.out.println("Node to string: " + lex.node.toString());
      LexiconNode lexington = new LexiconNode('p', false);
      lex.addChild(lexington);
      System.out.println("Node to string: " + lex.node.toString());
      lex.removeChild('p');
      System.out.println("Node to string: " + lex.node.toString());
    }
}
