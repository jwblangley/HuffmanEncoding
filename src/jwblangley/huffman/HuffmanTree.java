package jwblangley.huffman;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree<T> implements Serializable {

  public Map<T, String> compressionMap = new HashMap<>();

  public HuffmanTree(Map<T, Integer> valueCount) {
    assert valueCount.size() > 1 : "Cannot compress a single item";

    HuffmanNode<T> root;

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
    System.out.println(compressionMap.values().stream().mapToInt(String::length).average());
  }

  public String compressAll(List<T> inputs) {
    StringBuilder sb = new StringBuilder();
    //stream is sequential by default;
    inputs.stream().map(compressionMap::get).forEach(sb::append);
    return sb.toString();
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

}
