package code.repository.dev.backjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class FriendNetwork_v2 {
    private static Map<String, String> parents;
    private static Map<String, Integer> friends;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.valueOf(scanner.nextLine());

        while (testCase-- > 0) {
            parents = new HashMap<>();
            friends = new HashMap<>();

            int input = Integer.valueOf(scanner.nextLine());
            while (input-- > 0) {
                String friendConnect = scanner.nextLine();
                String[] network = friendConnect.split(" ");
                int networkCount = union(network[0], network[1]);

                System.out.println(networkCount);
            }
        }
    }

    public static String find(String name) {
        String parentName = parents.get(name);
        if (name.equals(parentName) || Objects.isNull(parentName)) {
            return name;
        } else {
            return find(parentName);
        }
    }

    public static int union(String name1, String name2) {
        String aGroup = find(name1);
        String bGroup = find(name2);

        int networkCount;
        if (!aGroup.equals(bGroup)) {
            parents.put(aGroup, aGroup);
            parents.put(bGroup, aGroup);

            networkCount = friends.getOrDefault(aGroup, 1) + friends.getOrDefault(bGroup, 1);

            friends.put(aGroup, networkCount);
            friends.put(bGroup, networkCount);
        } else {
            networkCount = friends.getOrDefault(aGroup, 1);
        }

        return networkCount;
    }
}
