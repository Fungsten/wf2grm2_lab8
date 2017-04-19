import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable {
    char letter;
    boolean isWord;

    // You also need a data structure for keeping track of the 
    // children of this LexiconNode

    // Constructor
    LexiconNode(char letter, boolean isWord) {

    }

    /* Compare this LexiconNode to another.  You should just 
     * compare the characters stored at the Lexicon Nodes.
     */

    public int compareTo(Object o) {
	return 0;
    } 

    /* Return letter stored by this LexiconNode */
    public char letter() {
	return '1';
    }

    /* Add LexiconNode child to correct position in child data structure */
    public void addChild(LexiconNode ln) {

    }

    /* Get LexiconNode child for 'ch' out of child data structure */
    public LexiconNode getChild(char ch) {
	return null;
    }

    /* Remove LexiconNode child for 'ch' from child data structure */
    public void removeChild(char ch) {

    }

    /* Iterate over children */
    public Iterator<LexiconNode> iterator() {
	return null;
    }
}
