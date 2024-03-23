/**
 * AIPieceList
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A list which stores a node which contains a piece and its location on the board
 */
public class AIPieceList {
    private Node top;
    private int size;

    public AIPieceList() {
        top = null;
        size = 0;
    }

    /**
     * adds a piece with its location to the end of the list
     *
     * @param piece the piece to be added
     * @param row   the row of the piece to be added
     * @param col   the col of the piece to be added
     */
    public void add(Piece piece, int row, int col) {
        Node data = new Node(piece, row, col);
        if (top == null) {
            top = data;
        } else {
            Node curr = top;

            while (curr.getNext() != null) {
                curr = curr.getNext();
            }

            curr.setNext(data);
        }
        size++;
    }

    /**
     * gets an item from the list
     *
     * @param i the position of the item to be removed
     * @return Node-the item to be gotten
     */
    public Node get(int i) {
        Node curr = top;
        int count = 1;

        while (curr != null) {
            if (count == i) {
                return curr;
            }
            curr = curr.getNext();
            count++;
        }
        return null;
    }

    /**
     * removes a piece from the list
     *
     * @param piece the piece to be removed
     * @param row   the row of the piece to be removed
     * @param col   the col of the piece to be removed
     */
    public void remove(Piece piece, int row, int col) {
        Node prev = null;
        Node curr = top;

        while (curr != null) {
            if (piece.name().equals(curr.getPiece().name()) && row == curr.getRow() && col == curr.getCol()) {
                if (prev == null) {
                    top = top.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }
                size--;
                return;
            }

            prev = curr;
            curr = curr.getNext();

        }


    }

    /**
     * gets the size of the list
     *
     * @return returns the size of the list
     */
    public int size() {
        return size;
    }

}
