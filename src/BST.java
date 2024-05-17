public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void insert(K key, V value) {
        root = insertNode(root, key, value);
    }

    private Node insertNode(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = insertNode(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = insertNode(node.right, key, value);
        } else {
            node.value = value; // Update value if key already exists
        }
        return node;
    }

    public V search(K key) {
        return searchNode(root, key);
    }

    private V searchNode(Node node, K key) {
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = removeNode(node.left, key);
        } else if (compareResult > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = minNode(temp.right);
            node.right = removeMinNode(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    private Node removeMinNode(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMinNode(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    public Iterable<K> keys() {
        List<K> keysList = new ArrayList<>();
        inOrderTraversal(root, keysList);
        return keysList;
    }

    private void inOrderTraversal(Node node, List<K> keysList) {
        if (node != null) {
            inOrderTraversal(node.left, keysList);
            keysList.add(node.key);
            inOrderTraversal(node.right, keysList);
        }
    }

    public Iterable<Node> allNodes() {
        List<Node> nodesList = new ArrayList<>();
        inOrderNodeTraversal(root, nodesList);
        return nodesList;
    }

    private void inOrderNodeTraversal(Node node, List<Node> nodesList) {
        if (node != null) {
            inOrderNodeTraversal(node.left, nodesList);
            nodesList.add(node);
            inOrderNodeTraversal(node.right, nodesList);
        }
    }
}

