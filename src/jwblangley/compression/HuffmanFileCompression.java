package jwblangley.compression;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import jwblangley.huffman.HuffmanTree;

public class HuffmanFileCompression {

  public static void main(String[] args) {
    File inputFile = new File("test.jpg");
    Path p = Paths.get(inputFile.getAbsolutePath());
    try {
      Map<Byte, Integer> countedBytes = countBytes(Files.readAllBytes(p));
      HuffmanTree<Byte> huffmanTree = new HuffmanTree<>(countedBytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Map<Byte, Integer> countBytes(byte[] items) {
    //TODO: @James parallelize
    Map<Byte, Integer> countMap = new HashMap<>();
    for (byte b : items) {
      int temp = countMap.containsKey(b) ? countMap.get(b) : 0;
      countMap.put(b, temp + 1);
    }
    return countMap;
  }

}
