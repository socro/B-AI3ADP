package implementations;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class AdtAVLNode {
 public AdtAVLNode leftchild;
 public AdtAVLNode rightchild;
 public AdtAVLNode parent;
 public int value;
 public int leftheight;
 public int rightheight;
 
 public AdtAVLNode(int val) {
  leftchild = rightchild = parent = null;
  leftheight = rightheight = 0;
  value = val;
 }

public void print(String filename) throws InterruptedException {

 String nl = System.lineSeparator();
 boolean waitForHeaderGodDamit = true;
 try (Writer writer = new BufferedWriter(new OutputStreamWriter(
         new FileOutputStream(filename + ".dot", true), "utf-8"))) {
     if (leftchild != null) {
         writer.write(this.value + " -> " + this.leftchild.value + ";");
         writer.write(nl);
     }
     if (rightchild != null) {
         writer.write(this.value + " -> " + this.rightchild.value + ";");
         writer.write(nl);
     }
 } catch (IOException e) {
     e.printStackTrace();
 }
 if (leftchild != null) {
     leftchild.print(filename);
 }
 if (rightchild != null) {
     rightchild.print(filename);
 }
 }
 
}
