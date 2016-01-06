package implementations;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;


public class AdtAVLTreeIO {
    
    protected AdtAVLNode root; // the root node
    private static final int AlgR = 0;
    private static final int AlgW = 1;
    private static final int AdtR = 2;
    private static final int AdtW = 3;
    public static long stepsA[] = new long[4];


    
    
    
public static AdtAVLTreeIO create() {
   return new AdtAVLTreeIO();
}

 // Fuegt ein Integer in den Baum ein
 public void insert(int element) {
  AdtAVLNode newNode = new AdtAVLNode(element);
  // start recursive procedure for inserting the node
  insertAVL(this.root,newNode);
 }
 
 // Fuegt ein AdtAVLNode in den Baum ein
 public void insertAVL(AdtAVLNode actNode, AdtAVLNode newNode) {
  // Wenn actNode null ist, befinden wir uns an der Wurzel des gesamten Baumes
  if(actNode == null) {
    this.root = newNode;
  } else {
   
   // Wenn die zu neue Node kleiner als die aktuelle Node ist, dann in den linken Teil
   if(newNode.value < actNode.value) {
       stepsA[AdtR]++;
       stepsA[AdtR]++;
    // Falls ich am Blatt bin, einfügen und Balance herstellen
    if(actNode.leftchild == null) {
     actNode.leftchild = newNode;
     newNode.parent = actNode;
     stepsA[AdtR]++;
     stepsA[AdtW]++;
     stepsA[AdtW]++;
     recursiveBalance(actNode);
    // wir sind noch nicht am Blatt, also im linken Teil weitergehen
    } else {
     insertAVL(actNode.leftchild,newNode);
    }
   
   // Wenn die zu neue Node größer als die aktuelle Node ist, dann in den rechten Teil
   } else if(newNode.value > actNode.value) {
       stepsA[AdtR]++;
       stepsA[AdtR]++;
    // falls am Blatt, einfügen und Balance herstellen
    if(actNode.rightchild == null) {
     actNode.rightchild = newNode;
     newNode.parent = actNode;
     stepsA[AdtR]++;
     stepsA[AdtW]++;
     stepsA[AdtW]++;
     recursiveBalance(actNode);
    // wir sind noch nicht am Blatt, also im rechten Teil weitergehen
    } else {
     insertAVL(actNode.rightchild,newNode);
    }
   // Ein Node diesen Werts existiert bereits. Tue nichts
   } else {
   }
  }
 }
 
 // Prüft für jeden Node bis zur Root die Balance zwischen den Höhen und rotiert falls nötig
 public void recursiveBalance(AdtAVLNode actNode) {
 
  // we do not use the balance in this class, but the store it anyway
  setHeights(actNode);
  int balance = actNode.rightheight - actNode.leftheight;
  stepsA[AdtR]++;
  stepsA[AdtR]++;
  // check the balance
  if(balance==-2) {
   
   if(actNode.leftchild.leftheight >= actNode.leftchild.rightheight) {
       stepsA[AdtR]++;
       stepsA[AdtR]++;
       stepsA[AdtR]++;
       stepsA[AdtR]++;
    actNode = rotateRight(actNode);
   } else {
    actNode = doubleRotateLeftRight(actNode);
   }
  } else if(balance == 2) {
   if(actNode.rightchild.rightheight >= actNode.rightchild.leftheight) {
       stepsA[AdtR]++;
       stepsA[AdtR]++;
       stepsA[AdtR]++;
       stepsA[AdtR]++;
    actNode = rotateLeft(actNode);
   } else {
    actNode = doubleRotateRightLeft(actNode);
   }
  }
 
  // we did not reach the root yet
  if(actNode.parent != null) {
   stepsA[AdtR]++;
   recursiveBalance(actNode.parent);
  } else {
   this.root = actNode;
   stepsA[AdtW]++;
  }
 }

 // Löscht ein Integer aus dem Baum
 public void remove(int delMeInt) {
  removeAVL(this.root,delMeInt);
 }
 
 // Sucht nach einem Node im Baum der den zu löschenden Wert hat. Wenn gefunden lösche ihn.
 public void removeAVL(AdtAVLNode actNode,int delMeInt) {
  // Beginne mit der Suche nach dem Node mit dem value von delMeInt
  if(actNode == null) {
   stepsA[AdtR]++;
   // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
  } else {
   if(actNode.value > delMeInt)  {
    stepsA[AdtR]++;
    removeAVL(actNode.leftchild,delMeInt);
   } else if(actNode.value < delMeInt) {
    stepsA[AdtR]++;
    removeAVL(actNode.rightchild,delMeInt);
   } else if(actNode.value == delMeInt) {
    stepsA[AdtR]++;
    // zu löschende Node wurde gefunden -> Lösche diese Node aus dem Baum
    removeFoundNode(actNode);
   }
  }
 }
 
 // Löscht eine Node aus dem Baum
 public void removeFoundNode(AdtAVLNode delMeNode) {
  AdtAVLNode r;
  // delMeNode hat mindestens ein Kind
  if(delMeNode.leftchild==null || delMeNode.rightchild==null) {
   stepsA[AdtR]++;
   stepsA[AdtR]++;
   // delMeNode ist die Wurzel des Baumes
   if(delMeNode.parent == null) {
    stepsA[AdtR]++;
    this.root = null;
    delMeNode = null;
    stepsA[AdtW]++;
    stepsA[AdtW]++;
    return;
   }
   r = delMeNode;
  } else {
   // delMeNode hat zwei Kinder --> ersetze mit dem Nachfolgeknoten
   r = successor(delMeNode);
   delMeNode.value = r.value;
   stepsA[AdtW]++;
   stepsA[AdtR]++;
  }
 
  AdtAVLNode p;
  if(r.leftchild != null) {
   p = r.leftchild;
   stepsA[AdtR]++;
   stepsA[AdtW]++;
  } else {
   p = r.rightchild;
   stepsA[AdtR]++;
   stepsA[AdtW]++;
  }
 
  if(p!=null) {
   p.parent = r.parent;
   stepsA[AdtR]++;
   stepsA[AdtW]++;
  }
 
  if(r.parent==null) {
   this.root = p;
   stepsA[AdtR]++;
   stepsA[AdtW]++;
  } else {
   if(r == r.parent.leftchild) {
    r.parent.leftchild=p;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
   } else {
    r.parent.rightchild = p;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
   }
   
   recursiveBalance(r.parent);
  }
  r = null;
 }
 
 // Linksrotation mit der übergebenen Node. Gibt die Wurzel des neuen Baums zurück.
 public AdtAVLNode rotateLeft(AdtAVLNode actNode) {
 
  AdtAVLNode newNode = actNode.rightchild;
  newNode.parent = actNode.parent;
  stepsA[AdtW]++;
  stepsA[AdtW]++;
  stepsA[AdtR]++;
 
  actNode.rightchild = newNode.leftchild;
 stepsA[AdtR]++;
 stepsA[AdtW]++;
  
  if(actNode.rightchild != null) {
   actNode.rightchild.parent = actNode;
  }
 stepsA[AdtR]++;
 stepsA[AdtR]++;
 stepsA[AdtW]++;
 
  newNode.leftchild = actNode;
  actNode.parent = newNode;
  stepsA[AdtW]++;
  stepsA[AdtW]++;
 
  if(newNode.parent != null) {
   if(newNode.parent.rightchild == actNode) {
    newNode.parent.rightchild = newNode;
    stepsA[AdtW]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
   } else if(newNode.parent.leftchild == actNode) {
    newNode.parent.leftchild = newNode;
    stepsA[AdtW]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
   }
  }
 
  setHeights(actNode);
  setHeights(newNode);
 
  return newNode;
 }

 // Rechtsrotation mit der übergebenen Node. Gibt die Wurzel des neuen Baums zurück.
 public AdtAVLNode rotateRight(AdtAVLNode actNode) {
 
  AdtAVLNode newNode = actNode.leftchild;
  newNode.parent = actNode.parent;
  stepsA[AdtW]++;
  stepsA[AdtR]++;
 
  actNode.leftchild = newNode.rightchild;
  stepsA[AdtW]++;
 
  if(actNode.leftchild!=null) {
   actNode.leftchild.parent=actNode;
  }
 stepsA[AdtR]++;
 stepsA[AdtR]++;
 stepsA[AdtW]++;
  newNode.rightchild = actNode;
  actNode.parent = newNode;
  stepsA[AdtW]++;
  stepsA[AdtW]++;
  stepsA[AdtR]++;
  stepsA[AdtR]++;
 
  if(newNode.parent!=null) {
      stepsA[AdtR]++;
   if(newNode.parent.rightchild==actNode) {
    newNode.parent.rightchild = newNode;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
   } 
   else if(newNode.parent.leftchild==actNode) {
    newNode.parent.leftchild = newNode;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
   }
  }
 
  setHeights(actNode);
  setHeights(newNode);
 
  return newNode;
 }

 public AdtAVLNode doubleRotateLeftRight(AdtAVLNode actNode) {
  actNode.leftchild = rotateLeft(actNode.leftchild);
  stepsA[AdtW]++;
  stepsA[AdtR]++;
  stepsA[AdtR]++;
  return rotateRight(actNode);
 }
 
 public AdtAVLNode doubleRotateRightLeft(AdtAVLNode actNode) {
  actNode.rightchild = rotateRight(actNode.rightchild);
  stepsA[AdtW]++;
  stepsA[AdtR]++;
  stepsA[AdtR]++;
  return rotateLeft(actNode);
 }
 
 // Sucht nach dem Nachfolger für einen gegebenen Node
 public AdtAVLNode successor(AdtAVLNode predecessorNode) {
  if(predecessorNode.rightchild != null) {
   AdtAVLNode r = predecessorNode.rightchild;
   stepsA[AdtW]++;
   stepsA[AdtW]++;
   stepsA[AdtR]++;
   stepsA[AdtR]++;
   while(r.leftchild!=null) {
    r = r.leftchild;
    stepsA[AdtW]++;
    stepsA[AdtR]++;
   }
   return r;
  } else {
   AdtAVLNode successorNode = predecessorNode.parent;
   while(successorNode!=null && predecessorNode==successorNode.rightchild) {
    predecessorNode = successorNode;
    successorNode = predecessorNode.parent;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
    stepsA[AdtW]++;
   }
   stepsA[AdtR]++;
   stepsA[AdtR]++;
   return successorNode;
  }
 }

private int getMaxHeight(AdtAVLNode actNode) {
    if(actNode.leftheight >= actNode.rightheight) {
        stepsA[AdtR]++;
        stepsA[AdtR]++;
        stepsA[AdtR]++;
        return actNode.leftheight;
    } else {
        stepsA[AdtR]++;
        return actNode.rightheight;
    }
}

private void setHeights(AdtAVLNode actNode) {
   if(actNode.leftchild == null && actNode.rightchild == null) {
    actNode.leftheight = actNode.rightheight = 0;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
   } else if(actNode.leftchild == null) {
    actNode.rightheight =  1 + getMaxHeight(actNode.rightchild);
    actNode.leftheight = 0;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
    stepsA[AdtW]++;
   } else if(actNode.rightchild == null) {
    actNode.leftheight =  1 + getMaxHeight(actNode.leftchild);
    actNode.rightheight = 0;
    stepsA[AdtW]++;
    stepsA[AdtW]++;
    stepsA[AdtR]++;
   } else {
    actNode.rightheight =  1 + getMaxHeight(actNode.rightchild);
    actNode.leftheight =  1 + getMaxHeight(actNode.leftchild);
    stepsA[AdtR]++;
    stepsA[AdtR]++;
    stepsA[AdtW]++;
    stepsA[AdtW]++;
   }
}

    public static long[] getStepsA() {
        return stepsA;
    }



public void print(String filename) throws InterruptedException {

    String nl = System.lineSeparator();

    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(filename + ".dot", true), "utf-8"))) {
        // Nur die Wurzel schreibt header und footer
            writer.write("digraph AVLBaum" + nl);
            writer.write("{" + nl);
    } catch (IOException e) {
        e.printStackTrace();
    }

    this.root.print(filename);

    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(filename + ".dot", true), "utf-8"))) {
        // Nur die Wurzel schreibt header und footer
            writer.write("}" + nl);
//            Process p = Runtime.getRuntime().exec("bash");
//            PrintWriter stdin = new PrintWriter(p.getOutputStream());
//            stdin.println("dot -Tsvg " + filename + ".dot > " + filename + ".svg");
//            stdin.close();
    } catch (IOException e) {

    }
}


}
