package jwblangley.huffman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree<T> implements Serializable {

  private Map<T, String> compressionMap = new HashMap<>();
  private HuffmanNode<T> root;

  public HuffmanTree(Map<T, Integer> valueCount) {
    assert valueCount.size() > 1 : "Cannot compress a single item";

    //build tree
    PriorityQueue<HuffmanNode<T>> queue = new PriorityQueue<>();
    valueCount.entrySet().stream()
        .map(e -> new HuffmanLeafNode(e.getKey(), e.getValue()))
        .forEach(queue::offer);

    while (queue.size() > 1) {
      HuffmanNode left = queue.poll();
      HuffmanNode right = queue.poll();
      queue.offer(new HuffmanInternalNode(left, right));
    }

    root = queue.poll();
    assert queue.isEmpty();

    generateMap(root, "");
  }

  private void generateMap(HuffmanNode<T> root, String path) {
    if (root instanceof HuffmanLeafNode) {
      compressionMap.put(((HuffmanLeafNode<T>) root).getValue(), path);
      return;
    }
    //internal node
    HuffmanInternalNode internalNode = (HuffmanInternalNode) root;
    generateMap(internalNode.getLeft(), path + "0");
    generateMap(internalNode.getRight(), path + "1");
  }

  public String compressAll(List<T> inputs) {
    StringBuilder sb = new StringBuilder();
    //stream is sequential by default;
    inputs.stream().map(compressionMap::get).forEach(sb::append);
    return sb.toString();
  }

  public List<T> decompressAll(String encoded) {
    List<T> results = new ArrayList<>();

    HuffmanNode<T> current = root;

    do {
      HuffmanInternalNode<T> internalNode = (HuffmanInternalNode<T>) current;
      current = encoded.charAt(0) == '0' ? internalNode.getLeft() : internalNode.getRight();
      if (current instanceof HuffmanLeafNode) {
        results.add(((HuffmanLeafNode<T>) current).getValue());
        current = root;
      }
      encoded = encoded.substring(1);
    } while (encoded.length() > 0);

    return results;
  }

  public static <S> Map<S, Integer> countItems(List<S> items) {
    Map<S, Integer> counted = new HashMap<>();
    for (S item : items) {
      int temp = counted.containsKey(item) ? counted.get(item) : 0;
      counted.put(item, temp + 1);
    }
    return counted;
  }

}
