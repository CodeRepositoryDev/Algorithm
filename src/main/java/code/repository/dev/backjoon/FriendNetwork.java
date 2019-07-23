package code.repository.dev.backjoon;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class FriendNetwork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.valueOf(scanner.nextLine());
        for (int test = 0; test < testCase; test++) {
            int friendNetworkCount = Integer.valueOf(scanner.nextLine());
            List<FriendNode> friendGroup = new CopyOnWriteArrayList<>();

            for (int i = 0; i < friendNetworkCount; i++) {
                String friendNetwork = scanner.nextLine();
                String[] friends = friendNetwork.split(" ");

                boolean isNew = true;

                for (FriendNode node : friendGroup) {
                    if (node.findFriend(friends[0])) {
                        boolean connected = false;
                        for (FriendNode otherNode : friendGroup) {
                            if (otherNode.getName().equals(node.getName())) {
                                continue;
                            }

                            if (otherNode.findFriend(friends[1])) {
                                node.unionFriends(otherNode);
                                connected = true;
                                friendGroup.remove(otherNode);
                            }
                        }

                        if (!connected) {
                            node.addFriend(friends[1]);
                        }

                        System.out.println(node.getFriends().size() + 1);
                        isNew = false;
                        break;

                    } else if (node.findFriend(friends[1])) {
                        boolean connected = false;
                        for (FriendNode otherNode : friendGroup) {
                            if (otherNode.getName().equals(node.getName())) {
                                continue;
                            }

                            if (otherNode.findFriend(friends[0])) {
                                node.unionFriends(otherNode);
                                connected = true;
                                friendGroup.remove(otherNode);
                            }
                        }

                        if (!connected) {
                            node.addFriend(friends[0]);
                        }

                        System.out.println(node.getFriends().size() + 1);
                        isNew = false;
                        break;
                    }
                }

                if (isNew) {
                    FriendNode root = new FriendNode();
                    root.setName(friends[0]);
                    root.addFriend(friends[1]);

                    friendGroup.add(root);

                    System.out.println(root.getFriends().size() + 1);
                }
            }
        }
    }

    private static class FriendNode {
        private String name;
        private Set<String> friends = new HashSet<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getFriends() {
            return friends;
        }

        public void addFriend(String name) {
            this.friends.add(name);
        }

        public boolean findFriend(String name) {
            return friends.contains(name) || name.equals(this.name);
        }

        public void unionFriends(FriendNode node) {
            addFriend(node.getName());
            this.friends.addAll(node.getFriends());
        }
    }
}
