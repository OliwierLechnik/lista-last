package org.example.javatree;
import java.util.Stack;


public class Node<T extends Comparable<T>> {
    private T value;
    private Node left;
    private Node right;
    private Node parrent;

    private Node(T value, Node parrent){
        this.value = value;
        this.parrent = parrent;
        this.left = null;
        this.right = null;
    }

    public Node(T value){
        this.value = value;
        this.parrent = null;
        this.left = null;
        this.right = null;

    }

    public void insert(T value){
        if(value.compareTo(this.value) < 0){
            if(this.left != null){
                this.left.insert(value);
            }else{
                this.left = new Node(value, this);
            }
        }else{
            if(this.right != null){
                this.right.insert(value);
            }else{
                this.right = new Node(value, this);
            }
        }
    }

    public String serialize(){
        return String.format(
                "%s{%s,%s}",
                String.valueOf(this.value),
                this.left != null ? this.left.serialize() : "",
                this.right != null ? this.right.serialize() : ""
                );
    }


    public static Node<String> deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        return deserializeHelper(data);
    }

    private static Node<String> deserializeHelper(String data) {
        Stack<Node<String>> nodeStack = new Stack<>();
        Stack<Character> bracketStack = new Stack<>();

        StringBuilder valueBuilder = new StringBuilder();
        Node<String> root = null;
        Node<String> current = null;

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (c == '{') {
                if (valueBuilder.length() > 0) {
                    Node<String> newNode = new Node<>(valueBuilder.toString());
                    valueBuilder.setLength(0);
                    if (root == null) {
                        root = newNode;
                    } else {
                        if (current.left == null) {
                            current.left = newNode;
                        } else {
                            current.right = newNode;
                        }
                    }
                    nodeStack.push(current);
                    current = newNode;
                }
                bracketStack.push(c);
            } else if (c == '}') {
                if (valueBuilder.length() > 0) {
                    Node<String> newNode = new Node<>(valueBuilder.toString());
                    valueBuilder.setLength(0);
                    if (current.left == null) {
                        current.left = newNode;
                    } else {
                        current.right = newNode;
                    }
                }
                bracketStack.pop();
                if (!nodeStack.isEmpty()) {
                    current = nodeStack.pop();
                }
            } else if (c == ',') {
                if (valueBuilder.length() > 0) {
                    Node<String> newNode = new Node<>(valueBuilder.toString());
                    valueBuilder.setLength(0);
                    if (current.left == null) {
                        current.left = newNode;
                    } else {
                        current.right = newNode;
                    }
                }
            } else {
                valueBuilder.append(c);
            }
        }

        return root;
    }

    public String getRowAsString(int depth){
        if(depth == 0){
            return String.valueOf(this.value)+",";
        }else {
            return String.format(
                   "%s%s",
                    this.left == null ? repeatString("#,",(int) Math.pow(2,depth-1)) : this.left.getRowAsString(depth-1),
                    this.right == null ? repeatString("#,",(int) Math.pow(2,depth-1)) : this.right.getRowAsString(depth-1)
            );
        }

    }

    private static String repeatString(String str, int times) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < times; i++) {
            result.append(str);
        }

        return result.toString();
    }

    public boolean search(T value){
        if(this.value == value){
            return true;
        } else if (value.compareTo(this.value) < 0) {
            if(this.left == null){
                return false;
            }else{
                return this.left.search(value);
            }
        }else {
            if(this.right == null){
                return false;
            }else{
                return this.right.search(value);
            }
        }
    }



}
