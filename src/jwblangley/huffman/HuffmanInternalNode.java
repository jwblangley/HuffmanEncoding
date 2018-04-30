package jwblangley.huffman;

public class HuffmanInternalNode<T> extends HuffmanNode<T> {

  private HuffmanNode left, right;

  public HuffmanInternalNode(HuffmanNode<T> left, HuffmanNode<T> right) {
    super(left.frequency() + right.frequency());
    this.left = left;
    this.right = right;
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
