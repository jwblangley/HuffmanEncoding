package jwblangley.huffman;

public class HuffmanLeafNode<T> extends HuffmanNode<T> {

  private final T VALUE;

  public HuffmanLeafNode(T value, int frequency) {
    super(frequency);
    VALUE = value;
  }

  public T getValue() {
    return VALUE;
  }
}
