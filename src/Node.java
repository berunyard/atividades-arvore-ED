public class Node {
    private Node left, right;
    private Integer value;

    public Node(Integer newValue) {
        this.value = newValue;
        this.left = null;
        this.right = null;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}