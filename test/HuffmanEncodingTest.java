import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import jwblangley.huffman.HuffmanTree;
import org.junit.Test;

public class HuffmanEncodingTest {

  @Test
  public void countTest() {
    List<Integer> integerList = Arrays.asList(1, 2, 2, 1, 1, 3);
    Map<Integer, Integer> counted = HuffmanTree.countItems(integerList);
    assertEquals(3, (int) counted.get(1));
    assertEquals(2, (int) counted.get(2));
    assertEquals(1, (int) counted.get(3));
  }

  @Test
  public void compressionDecompressionTest() {
    List<Integer> integerList = Arrays
        .asList(1, 2, 2, 1, 1, 3, 4, 2, 1, 2, 3, 4, 3, 2, 1, 5, 2, 3, 2, 1);
    Map<Integer, Integer> counted = HuffmanTree.countItems(integerList);
    HuffmanTree<Integer> hTree = new HuffmanTree<>(counted);
    assertArrayEquals(integerList.toArray(),
        hTree.decompressAll(hTree.compressAll(integerList)).toArray());
  }
}
