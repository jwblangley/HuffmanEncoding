package jwblangley.huffman;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree<T> {

  public HuffmanTree(Map<T, Integer> valueCount) {

    //build tree
    PriorityQueue<HuffmanNode<T>> queue = new PriorityQueue<>();
    valueCount.entrySet().stream()
        .map(e -> new HuffmanLeafNode(e.getKey(), e.getValue()))
        .forEach(queue::offer);
  }

}
