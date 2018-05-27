package code.repository.dev.huffman;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffmanAlgorithm {
    //    private int[] characterArray = new int[128];
    private HuffmanNode root;
    private Map<Character, String> map = new HashMap<>();

    public String compression(String target) {

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < target.length(); i++) {
            char key = target.charAt(i);
            if (frequency.containsKey(key)) {
                int count = frequency.get(key) + 1;
                frequency.remove(key);
                frequency.put(key, count);
            } else {
                frequency.put(key, 1);
            }

//            characterArray[key]++;
        }

        Set<Character> keys = frequency.keySet();
        for (char key : keys) {
            queue.add(new HuffmanNode(key, frequency.get(key)));
        }

//        for (int i = 0; i < 128; i++) {
//            if (characterArray[i] != 0) {
//                queue.add(new HuffmanNode((char) i, characterArray[i]));
//            }
//        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            queue.add(new HuffmanNode(left, right));
        }

        root = queue.poll();
        createMap(map, root, "");

        String result = "";

        for (int i = 0; i < target.length(); i++) {
            result += map.get(target.charAt(i));
        }

        return result;
    }

    private void createMap(Map map, HuffmanNode node, String bit) {
        if (node.getKey() == null) {
            if (node.getLeft() != null) {
                createMap(map, node.getLeft(), bit + "0");
            }

            if (node.getRight() != null) {
                createMap(map, node.getRight(), bit + "1");
            }
        } else {
            map.put(node.getKey(), bit);
        }
    }

    public String decompression(String target) {
        String result = "";
        HuffmanNode temp = root;
        int index = 0;
        while (index < target.length()) {
            while (temp.getKey() == null) {
                if (target.charAt(index) == '0' && temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else if (target.charAt(index) == '1' && temp.getRight() != null) {
                    temp = temp.getRight();
                }
                index++;
            }

            if (temp.isLeaf()) {
                result += temp.getKey();
                temp = root;
            }
        }

        return result;
    }
}
