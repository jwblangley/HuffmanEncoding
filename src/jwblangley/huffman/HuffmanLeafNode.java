package jwblangley.huffman;

public class HuffmanLeafNode<T> extends HuffmanNode<T> {

  private int frequency;
  private final T VALUE;

  public HuffmanLeafNode(T value, int frequency) {
    super(frequency);
    VALUE = value;
  }

  @Override
  public int frequency() {
    return frequency;
  }

  public T getValue() {
    return VALUE;
  }
}
