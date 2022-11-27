import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinaryTree {
    private final Node root;
    private final LinkedList<Integer> path;

    public BinaryTree(int[] values) {
        root = buildTree(values, 0, values.length - 1);
        path = new LinkedList<>();
    }

    public LinkedList<Integer> getPath() {
        return path;
    }

    private Node buildTree(int[] values, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;
            return new Node(values[mid],
                    buildTree(values, low, mid - 1),
                    buildTree(values, mid + 1, high));
        }
        return null;
    }

    public boolean findDFS(int searchVal) {
        path.clear();
        return findBFS(searchVal);
    }

    private boolean findDFS(int searchVal, Node node) {
        boolean found;

        if (node == null)
            found = false;
        else if (node.value == searchVal)
            found = true;
        else
            found = findDFS(searchVal, node.left) || findDFS(searchVal, node.right);

        if (found)
            path.add(0, node.value);

        return found;
    }

    public boolean findBFS(int searchVal) {
        Stack<Node> nodeStack = new Stack<>();

        if (this.root == null)
            return false;

        nodeStack.push(this.root);

        while (!nodeStack.isEmpty()) {
            Node current = nodeStack.pop();
            System.out.println("Current node: " + current);

            if (current.value == searchVal) {

                nodeStack.push(current);

                for (Node node : nodeStack){
                    path.add(0, node.value);
                }

                return true;
            }

            if (current.left != null)
                nodeStack.push(current.left); // for stack, push

            if (current.right != null)
                nodeStack.push(current.right); // for stack, push
        }

        return false;


    }

    // Inner class.  Private data can be accessed by outer class
    private record Node(int value, BinaryTree.Node left, BinaryTree.Node right) {
    }
}