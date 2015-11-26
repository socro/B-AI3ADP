package implementations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class AdtAVLTree {

    public int leftheight = 0;
    public int rightheight = 0;
    public int value = -1;
    public AdtAVLTree leftChild = null;
    public AdtAVLTree rightChild = null;
    public AdtAVLTree parent = null;
    public static boolean headerWritten = false;
    public static boolean footerWritten = false;

    private AdtAVLTree() {

    }

    public static AdtAVLTree create() {
        return new AdtAVLTree();
    }

    public boolean isEmpty() {
        return leftheight == 0 && rightheight == 0;
    }

    public int high() {
        if (isEmpty()) {
            return value == -1 ? 0 : 1;
        } else {
            return rightheight > leftheight ? rightheight : leftheight;
        }
    }

    public void insert(int element) {
        // Wenn AVL Baum leer, dann füge Wert als Wurzel ein
        if (value == -1) {
            value = element;

        } // Ansonsten wenn kleiner, füge links ein
        else if (element < value) {
            if (leftChild == null) {
                leftChild = create();
                leftChild.setParent(this);
            }
            leftheight++;
            leftChild.insert(element);
            checkForBalance();
        } // Ansonsten füge rechts ein
        else {
            if (rightChild == null) {
                rightChild = create();
                rightChild.setParent(this);
            }
            rightheight++;
            rightChild.insert(element);
            checkForBalance();
        }        
    }

    public void delete(int element) {
        // Das
        if (rightChild.value == element && rightChild.isEmpty()) {
            rightChild = null;
        } else if (leftChild.value == element && leftChild.isEmpty()) {
            leftChild = null;
        } else if (element < value) {
            leftChild.delete(element);
        } else {
            rightChild.delete(element);
        }
    }

    public void print(String filename) throws InterruptedException {

        String nl = System.lineSeparator();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename + ".dot", true), "utf-8"))) {
            // Nur die Wurzel schreibt header und footer
            if (parent == null) {
                writer.write("digraph AVLBaum" + nl);
                writer.write("{" + nl);
            }
            if (leftChild != null) {
                writer.write(this.value + " -> " + this.leftChild.value + ";");
                writer.write(nl);
                leftChild.print(filename);
            }
            if (rightChild != null) {
                writer.write(this.value + " -> " + this.rightChild.value + ";");
                writer.write(nl);
                rightChild.print(filename);
            }
            // Nur die Wurzel schreibt header und footer
            if (parent == null) {
                writer.write("}" + nl);
                Process p = Runtime.getRuntime().exec("cmd");
                PrintWriter stdin = new PrintWriter(p.getOutputStream());
                stdin.println("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe -Tsvg " + filename + ".dot > " + filename + ".png");// write any other commands you want here
                stdin.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParent(AdtAVLTree parent) {
        this.parent = parent;
    }

    private void checkForBalance() {
        // Linksrotation
        if (this.rightheight - this.leftheight > 1) {
            rotateLeft();
            if (this.rightheight - this.leftheight < -1) {
                this.rightChild.rotateRight();
                rotateLeft();
            }
        } else // Rechtsrotation
        if (this.rightheight - this.leftheight < -1) {
            rotateRight();
            if (this.rightheight - this.leftheight > 1) {
                this.leftChild.rotateLeft();
                rotateRight();
            }
        }
    }

    private void rotateRight() {
        AdtAVLTree tempLeftChild = this.leftChild;
        this.leftChild = this.leftChild.rightChild;
        tempLeftChild.rightChild = this;
        tempLeftChild.parent = this.parent;
        this.parent = tempLeftChild;
        parent.rightheight++;
        this.leftheight--;        
    }

    private void rotateLeft() {
        AdtAVLTree tempRightChild = this.rightChild;
        this.rightChild = this.rightChild.leftChild;
        tempRightChild.leftChild = this;
        tempRightChild.parent = parent;
        this.parent = tempRightChild;
        parent.leftheight++;
        this.rightheight--;
    }
}
