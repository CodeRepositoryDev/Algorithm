package code.repository.dev.huffman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();

        Scanner sc = new Scanner(System.in);

        String result = huffmanAlgorithm.compression(sc.nextLine());

        System.out.println(result);

        System.out.println(huffmanAlgorithm.decompression(result));
    }
}
