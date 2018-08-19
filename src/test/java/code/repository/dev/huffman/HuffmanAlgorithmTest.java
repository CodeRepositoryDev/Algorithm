package code.repository.dev.huffman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HuffmanAlgorithmTest {

    @Test
    public void huffmanAlgorithmTest() {

        String expect = "asdfnlkqwenflksdanfasdflknasdfasdfdsadfasdf";
        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
        String encodeString = huffmanAlgorithm.compression(expect);
        assertEquals(expect, huffmanAlgorithm.decompression(encodeString));
    }


    @Test
    public void test() {
        System.out.println(1|1<<1);
        System.out.println(1|1<<2);
    }
}
