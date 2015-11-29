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

    public AdtAVLTree insert(int element) {
        // Wenn AVL Baum leer, dann füge Wert als Wurzel ein
        if (value == -1) {
            value = element;
            return this;
        } // Ansonsten wenn kleiner, füge links ein
        else if (element < value) {
            if (leftChild == null) {
                leftChild = create();
                leftChild.setParent(this);
            }
            leftheight++;
            leftChild.insert(element);
            // Gibt die neue Wurzel zurück
            return checkForBalance();
        } // Ansonsten füge rechts ein
        else {
            if (rightChild == null) {
                rightChild = create();
                rightChild.setParent(this);
            }
            rightheight++;
            rightChild.insert(element);
            // Gibt die neue Wurzel zurück
            return checkForBalance();
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
        boolean waitForHeaderGodDamit = true;
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
            }
            if (rightChild != null) {
                writer.write(this.value + " -> " + this.rightChild.value + ";");
                writer.write(nl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (leftChild != null) {
            leftChild.print(filename);
        }
        if (rightChild != null) {
            rightChild.print(filename);
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename + ".dot", true), "utf-8"))) {
            // Nur die Wurzel schreibt header und footer
            if (parent == null) {
                writer.write("}" + nl);
                Process p = Runtime.getRuntime().exec("cmd");
                PrintWriter stdin = new PrintWriter(p.getOutputStream());
                stdin.println("C:\\leckmich\\dot.exe -Tsvg " + filename + ".dot > " + filename + ".svg");// write any other commands you want here
                stdin.close();
            }
        } catch (IOException e) {

        }
    }

    public void setParent(AdtAVLTree parent) {
        this.parent = parent;
    }

    private AdtAVLTree checkForBalance() {
        // Linksrotation
        if (this.rightheight - this.leftheight > 1) {
            AdtAVLTree temptree = rotateLeft();
            if (temptree.rightheight - temptree.leftheight < -1) {
                temptree = temptree.leftChild.rotateLeft();
                return temptree.rotateRight();
            }
            return temptree;
        } // Rechtsrotation
        else if (this.rightheight - this.leftheight < -1) {
            AdtAVLTree temptree = rotateRight();
            if (temptree.rightheight - temptree.leftheight > 1) {
                temptree = temptree.rightChild.rotateRight();
                return temptree.rotateLeft();
            }
            return temptree;
        }
        return this;
    }

    private AdtAVLTree rotateRight() {
        // Rechter Baum wird neue Wurzel
        AdtAVLTree tempTree = this.leftChild;
        // was im alten Baum kleiner war als die Wurzel des rechten Teilbaums ist größer sein als die alte Wurzel
        this.leftChild = tempTree.rightChild;
        // Alte Wurzel wird zum linken Teilbaum
        tempTree.rightChild = this;
        tempTree.parent = this.parent;
        tempTree.rightChild.parent = tempTree;
        tempTree.rightheight++;// = this.leftheight++;
        tempTree.rightChild.leftheight--; //= this.leftheight--;
        tempTree.rightChild.leftheight--; //= this.leftheight--;
        if (tempTree.parent != null && tempTree.parent.value > tempTree.value) {
            tempTree.parent.rightChild = tempTree;
            tempTree.parent.leftheight--;
        } else if (tempTree.parent != null && tempTree.parent.value < tempTree.value) {
            tempTree.parent.leftChild = tempTree;
            tempTree.parent.leftheight--;
        }
        return tempTree;
    }

    private AdtAVLTree rotateLeft() {
        // Rechter Baum wird neue Wurzel
        AdtAVLTree tempTree = this.rightChild;
        // was im alten Baum kleiner war als die Wurzel des rechten Teilbaums ist größer sein als die alte Wurzel
        this.rightChild = tempTree.leftChild;
        // Alte Wurzel wird zum linken Teilbaum
        tempTree.leftChild = this;
        tempTree.parent = this.parent;
        tempTree.leftChild.parent = tempTree;
        tempTree.leftheight++;// = this.leftheight++;
        tempTree.leftChild.rightheight--; //= this.rightheight--;
        tempTree.leftChild.rightheight--; //= this.rightheight--;
        if (tempTree.parent != null && tempTree.parent.value > tempTree.value) {
            tempTree.parent.leftChild = tempTree;
            tempTree.parent.leftheight--;
        } else if (tempTree.parent != null && tempTree.parent.value < tempTree.value) {
            tempTree.parent.rightChild = tempTree;
            tempTree.parent.rightheight--;
        }
        return tempTree;
    }
}
