package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class BFSandDFS {

    //定义树结点
    private static class TreeNode {
        //private只能用来修饰内部类，用来修饰外部类会报错，且private修饰说明该内部类不对其他外部类可见（非外嵌套类）而已

        Object data;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(Object data) {
            this.data = data;
        }

        //private修饰的内部类，方法属性等都能被public等其他修饰符修饰，
        // 一个角度看说明：private只说明内部类对其他外部类的可见性；
        // 另一个角度：private修饰的类要重写一些public方法(如下面继承Object类的equals)也要保留方法修饰符才能成功重写
        @Override
        public boolean equals(Object data) {
            return this.data.equals(data);
        }
    }

    public static boolean breadthFirstSearch(TreeNode root, Object data) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.equals(data)) {
                return true;
            }

            if (node.leftNode != null) {
                queue.add(node.leftNode);
            }
            if (node.rightNode != null) {
                queue.add(node.rightNode);
            }

        }
        return false;
    }

    public static boolean depthFirstSearchWithRecursion(TreeNode root, Object data) {
        if (root != null) {
            if (root.equals(data)) {
                return true;
            } else if (depthFirstSearchWithRecursion(root.leftNode, data)) {
                return true;
            } else {
                return depthFirstSearchWithRecursion(root.rightNode, data);
            }
        }
        return false;
    }

    public static boolean depthFirstSearchWithStack(TreeNode root, Object data) {
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while(!stack.isEmpty()){
//            TreeNode node = stack.pop();
//            if (node!=null) {
//                if (node.equals(data)) {
//                    return true;
//                }
//                stack.push(node.leftNode);
//                stack.push(node.rightNode);
//            }
//        }
//        return false;

        //优化
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.equals(data)) {
                    return true;
                }
                //先判空再入栈 减少了一些复杂度
                if (node.leftNode != null) {
                    stack.push(node.leftNode);
                }
                if (node.rightNode != null) {
                    stack.push(node.rightNode);
                }
            }
        }
        return false;
    }

    private static class GraphNode{
        Object data;
        List<GraphNode> children;

        public GraphNode(Object data){
            this.data = data;
        }

        @Override
        public boolean equals(Object data) {
            return this.data.equals(data);
        }
    }

    //使用Queue实现BFS
    public static boolean graphBFSWithQueue(GraphNode root,Object data) {
        Queue<GraphNode> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        //用set维护已经访问过的点
        Set<GraphNode> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            visited.add(node);

            if (node.equals(data)){
                return true;
            }

            //在这里处理遍历到的Node节点
            if (node.children != null) {
                for (GraphNode child : node.children) {
                    if (child != null && !visited.contains(child)){
                        queue.add(child);
                    }
                }
            }
        }

        return false;
    }


    public static final void main(String... args) {
        TreeNode root = new TreeNode(1);
        root.leftNode = new TreeNode(2);
        root.rightNode = new TreeNode(3);
        System.out.println(breadthFirstSearch(root, 4));
        System.out.println(depthFirstSearchWithRecursion(root, 4));
        System.out.println(depthFirstSearchWithStack(root, 3));
        System.out.println(depthFirstSearchWithStack(root, 4));


        List<GraphNode> children = new ArrayList<>();       //数组列表较链表列表访问效率高
        children.add(new GraphNode(2));
        children.add(new GraphNode(3));
        children.add(new GraphNode(4));
        GraphNode graphRoot = new GraphNode(1);
        graphRoot.children = children;
        System.out.println(graphBFSWithQueue(graphRoot,4));
        System.out.println(graphBFSWithQueue(graphRoot,5));
    }

}
