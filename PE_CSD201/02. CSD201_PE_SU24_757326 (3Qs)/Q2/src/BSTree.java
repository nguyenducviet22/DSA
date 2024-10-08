/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xPlace, int xPrice, int xType) {
        //You should insert here statements to complete this function
        if (xPlace.charAt(0) == 'A') {
            return;
        }
        Node node = new Node(new Brick(xPlace, xPrice, xType));
        if (isEmpty()) {
            root = node;
            return;
        }
        Node cur = root;
        Node par = null;
        while (cur != null) {
            if (xType == cur.info.type) {
                return;
            }
            par = cur;
            if (xType < cur.info.type) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (xType < par.info.type) {
            par.left = node;
        } else {
            par.right = node;
        }

    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.left != null || p.right != null) {
            fvisit(p, f);
        }
        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        inOrder(root);
        deleteByCopying(findNodeF3);
        //------------------------------------------------------------------------------------
         breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    int countF3 = 0;
    Node findNodeF3 = null;

    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (countF3 == 3) {
            findNodeF3 = node;
        }
        countF3++;
        inOrder(node.right);
    }

    void deleteByCopying(Node node) {
        Node cur = root, par = null;
        while (cur != null) {
            if (cur.info == node.info) {
                break;
            }
            par = cur;
            if (cur.info.type > node.info.type) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        //No child
        if (cur.left == null && cur.right == null) {
            if (par == null) {
                root = null;
                return;
            }
            if (par.left == cur) {
                par.left = null;
            } else {
                par.right = null;
            }
            return;
        }

        //1 child
        if (cur.left != null && cur.right == null) {
            if (par == null) {
                root = cur.left;
                return;
            }
            if (par.left == cur) {
                par.left = cur.left;
            } else {
                par.right = cur.left;
            }
        } else if (cur.left == null && cur.right != null) {
            if (par == null) {
                root = cur.right;
                return;
            }
            if (par.left == cur) {
                par.left = cur.right;
            } else {
                par.right = cur.right;
            }
            return;
        }
        //2 children
        if (node.left != null && node.right != null) {
            Node rightMost = node.left;
            while (rightMost.right != null) {
                par = rightMost;
                rightMost = rightMost.right;
            }
            node.info = rightMost.info;
            if (rightMost.right == null) {
                node.left = rightMost.left;
            } else {
                par.right = rightMost.left;
            }
            return;
        }
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder3(root);
        Node par = searchParent(findNodeF4);
        rotateLeft(findNodeF4, par);
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node findNodeF4 = null;
    int countF4 = 0;

    void preOrder3(Node node) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            countF4++;
            if (countF4 == 3) {
                findNodeF4 = node;
            }
        }
        preOrder3(node.left);
        preOrder3(node.right);
    }

    Node searchParent(Node node) {
        if (node == null) {
            return null;
        }
        Node cur = root, par = null;
        while (cur != null && cur != node) {
            par = cur;
            if (cur.info.type > node.info.type) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return par;
    }

    void rotateLeft(Node node, Node par) {
        if (node == null || node.right == null) {
            return;
        }
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;

        if (par.right == node) {
            par.right = temp;
        } else {
            par.left = temp;
        }
    }

}
