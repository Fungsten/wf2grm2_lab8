/*
Will Fung and Grace Mazzarella
*/

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
        // Do nothing
      } else {
        for (int i = 0; i <= node.size(); i++){
          if (node.size() == 0 || i >= node.size()){
            //System.out.println("I added " + ln.letter());
            node.add(ln);
            break;
          } else {
            //System.out.println("" + i);
            //System.out.println("I'm comparing " + ln.letter() + " to " + node.elementAt(i).letter());
            if (ln.compareTo(node.elementAt(i)) > 0) {
              // char ln bigger than char i, must be inserted further down
              // so do nothing
              //System.out.println(ln.letter() + " is bigger than " + node.elementAt(i).letter());
              //++i;
            } else {
              // i is now at the appropriate spot
              node.insertElementAt(ln, i);
              //System.out.println("I added " + ln.letter() + " to the node at " + i);
              break;
            }
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
      Iterator<LexiconNode> iter = node.iterator();
      return iter;
    }

    public static void main(String args[]){
      LexiconNode lex = new LexiconNode('g', false);
      LexiconNode child1 = new LexiconNode('r', false);
      LexiconNode child2 = new LexiconNode('a', false);
      LexiconNode child3 = new LexiconNode('m', false);
      LexiconNode child4 = new LexiconNode('p', false);
      LexiconNode child5 = new LexiconNode('h', false);
      LexiconNode child6 = new LexiconNode('j', false);
      lex.addChild(child1);
      lex.addChild(child2);
      lex.addChild(child3);
      lex.addChild(child4);
      lex.addChild(child5);
      lex.addChild(child6);
      /*Iterator<LexiconNode> go = lex.iterator();
      while (go.hasNext()){
        System.out.println(go.next().letter());
      }*/
      lex.removeChild('a');
      Iterator<LexiconNode> go2 = lex.iterator();
      while (go2.hasNext()){
        System.out.println(go2.next().letter());
      }
    }
}
