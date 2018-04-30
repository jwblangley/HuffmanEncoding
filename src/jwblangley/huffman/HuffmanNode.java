package jwblangley.huffman;

public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {

  private int frequency;

  public HuffmanNode(int frequency) {
    this.frequency = frequency;
  }

  public int frequency() {
    return frequency;
  }

  @Override
  public int compareTo(HuffmanNode other) {
    return this.frequency() - other.frequency();
  }

}
