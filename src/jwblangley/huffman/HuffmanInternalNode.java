package jwblangley.huffman;

public class HuffmanInternalNode<T> extends HuffmanNode<T> {

  private HuffmanNode left, right;

  public HuffmanInternalNode(int frequency) {
    super(frequency);
  }

  public HuffmanNode getLeft() {
    return left;
  }

  public void setLeft(HuffmanNode left) {
    this.left = left;
  }

  public HuffmanNode getRight() {
    return right;
  }

  public void setRight(HuffmanNode right) {
    this.right = right;
  }

}
