package implementations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;


public class AdtAVLTree {
    
 protected AdtAVLNode root; // the root node
 
public static AdtAVLTree create() {
   return new AdtAVLTree();
}

 // Fuegt ein Integer in den Baum ein
 public void insert(int element) {
  AdtAVLNode newNode = new AdtAVLNode(element);
  // start recursive procedure for inserting the node
  insertAVL(this.root,newNode);
 }
 
 // Fuegt ein AdtAVLNode in den Baum ein
 public void insertAVL(AdtAVLNode curNode, AdtAVLNode newNode) {
  // Wenn actNode null ist, befinden wir uns an der Wurzel des gesamten Baumes
  if(curNode == null) {
    this.root = newNode;
  } else {
   
   // Wenn die zu neue Node kleiner als die aktuelle Node ist, dann in den linken Teil
   if(newNode.value < curNode.value) {
    // Falls ich am Blatt bin, einfügen und Balance herstellen
    if(curNode.leftchild == null) {
     curNode.leftchild = newNode;
     newNode.parent = curNode;
    // wir sind noch nicht am Blatt, also im linken Teil weitergehen
    } else {
     insertAVL(curNode.leftchild,newNode);
    }
   
   // Wenn die zu neue Node größer als die aktuelle Node ist, dann in den rechten Teil
   } else if(newNode.value > curNode.value) {
    // falls am Blatt, einfügen und Balance herstellen
    if(curNode.rightchild == null) {
     curNode.rightchild = newNode;
     newNode.parent = curNode;
     
    // wir sind noch nicht am Blatt, also im rechten Teil weitergehen
    } else {
     insertAVL(curNode.rightchild,newNode);
    }
   // Ein Node diesen Werts existiert bereits. Tue nichts
   } else {
   }
   recursiveBalance(curNode);
  }
 }
 
 // Prüft für jeden Node bis zur Root die Balance zwischen den Höhen und rotiert falls nötig
 public void recursiveBalance(AdtAVLNode curNode) {
 
  setHeights(curNode);
  int balance = curNode.rightheight - curNode.leftheight;
 
  // Wenn Teilbaum linkslastig oder rechtslastig
  if(balance==-2) {
   
   if(curNode.leftchild.leftheight >= curNode.leftchild.rightheight) {
    curNode = rotateRight(curNode);
   } else {
    curNode = doubleRotateLeftRight(curNode);
   }
  } else if(balance == 2) {
   if(curNode.rightchild.rightheight >= curNode.rightchild.leftheight) {
    curNode = rotateLeft(curNode);
   } else {
    curNode = doubleRotateRightLeft(curNode);
   }
  }
 
  // Bis zur Wurzel gehen, diese ggf. anpassen und dvom Baum die Wurzel auf die aktuelle Node setzen
  if(curNode.parent == null) {
   this.root = curNode;
  }
 }

 // Löscht ein Integer aus dem Baum
 public void remove(int delMeInt) {
  removeAVL(this.root,delMeInt);
 }
 
 // Sucht nach einem Node im Baum der den zu löschenden Wert hat. Wenn gefunden lösche ihn.
 public void removeAVL(AdtAVLNode curNode,int delMeInt) {
  // Beginne mit der Suche nach dem Node mit dem value von delMeInt
  if(curNode == null) {
   // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
  } else {
   if(curNode.value > delMeInt)  {
    removeAVL(curNode.leftchild,delMeInt);
   } else if(curNode.value < delMeInt) {
    removeAVL(curNode.rightchild,delMeInt);
   } else if(curNode.value == delMeInt) {
    // zu löschende Node wurde gefunden -> Lösche diese Node aus dem Baum
    removeFoundNode(curNode);
   }
  }
     recursiveBalance(curNode);
 }
 
 // Löscht eine Node aus dem Baum
 public void removeFoundNode(AdtAVLNode delMeNode) {
  AdtAVLNode r;
  // delMeNode hat mindestens ein Kind
  if(delMeNode.leftchild==null || delMeNode.rightchild==null) {
   // delMeNode ist die Wurzel des Baumes
   if(delMeNode.parent == null) {
    this.root = null;
    delMeNode = null;
    return;
   }
   r = delMeNode;
  } else {
   // delMeNode hat zwei Kinder --> ersetze mit dem Nachfolgeknoten
   r = successor(delMeNode);
   delMeNode.value = r.value;
  }
 
  AdtAVLNode p;
  if(r.leftchild != null) {
   p = r.leftchild;
  } else {
   p = r.rightchild;
  }
 
  if(p!=null) {
   p.parent = r.parent;
  }
 
  if(r.parent==null) {
   this.root = p;
  } else {
   if(r == r.parent.leftchild) {
    r.parent.leftchild=p;
   } else {
    r.parent.rightchild = p;
   }
   
   recursiveBalance(r.parent);
  }
  r = null;
 }
 
 // Linksrotation mit der übergebenen Node. Gibt die Wurzel des neuen Baums zurück.
 public AdtAVLNode rotateLeft(AdtAVLNode actNode) {
 
  AdtAVLNode newNode = actNode.rightchild;
  newNode.parent = actNode.parent;
 
  actNode.rightchild = newNode.leftchild;
 
  if(actNode.rightchild != null) {
   actNode.rightchild.parent = actNode;
  }
 
  newNode.leftchild = actNode;
  actNode.parent = newNode;
 
  if(newNode.parent != null) {
   if(newNode.parent.rightchild == actNode) {
    newNode.parent.rightchild = newNode;
   } else if(newNode.parent.leftchild == actNode) {
    newNode.parent.leftchild = newNode;
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
 
  actNode.leftchild = newNode.rightchild;
 
  if(actNode.leftchild!=null) {
   actNode.leftchild.parent=actNode;
  }
 
  newNode.rightchild = actNode;
  actNode.parent = newNode;
 
 
  if(newNode.parent!=null) {
   if(newNode.parent.rightchild==actNode) {
    newNode.parent.rightchild = newNode;
   } else if(newNode.parent.leftchild==actNode) {
    newNode.parent.leftchild = newNode;
   }
  }
 
  setHeights(actNode);
  setHeights(newNode);
 
  return newNode;
 }

 public AdtAVLNode doubleRotateLeftRight(AdtAVLNode actNode) {
  actNode.leftchild = rotateLeft(actNode.leftchild);
  return rotateRight(actNode);
 }
 
 public AdtAVLNode doubleRotateRightLeft(AdtAVLNode actNode) {
  actNode.rightchild = rotateRight(actNode.rightchild);
  return rotateLeft(actNode);
 }
 
 // Sucht nach dem Nachfolger für einen gegebenen Node
 public AdtAVLNode successor(AdtAVLNode predecessorNode) {
  //Wenn Kind vorhanden welches größer ist als der aktuelle Wert, suche den kleinsten von diesen
  if(predecessorNode.rightchild != null) {
   AdtAVLNode r = predecessorNode.rightchild;
   while(r.leftchild!=null) {
    r = r.leftchild;
   }
   return r;
  } else {
   AdtAVLNode successorNode = predecessorNode.parent;
   // Wir sind nicht Wurzel und das rechte Kind vom Vorgänger
   while(successorNode!=null && predecessorNode==successorNode.rightchild) {
    predecessorNode = successorNode;
    successorNode = predecessorNode.parent;
   }
   return successorNode;
  }
 }

private int getMaxHeight(AdtAVLNode actNode) {
    if(actNode.leftheight >= actNode.rightheight) {
        return actNode.leftheight;
    } else {
        return actNode.rightheight;
    }
}

private void setHeights(AdtAVLNode actNode) {
   if(actNode.leftchild == null && actNode.rightchild == null) {
    actNode.leftheight = actNode.rightheight = 0;
   } else if(actNode.leftchild == null) {
    actNode.rightheight =  1 + getMaxHeight(actNode.rightchild);
    actNode.leftheight = 0;
   } else if(actNode.rightchild == null) {
    actNode.leftheight =  1 + getMaxHeight(actNode.leftchild);
    actNode.rightheight = 0;
   } else {
    actNode.rightheight =  1 + getMaxHeight(actNode.rightchild);
    actNode.leftheight =  1 + getMaxHeight(actNode.leftchild);
   }
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
//            String stringToExecute = "dot.exe -Tsvg " + filename + ".dot > " + filename + ".svg";
//            String[] cmdCall= new String[] {"cmd.exe", "/c",stringToExecute};
//            Runtime.getRuntime().exec(cmdCall);
//            System.out.println("stringToExecute: " + stringToExecute);
    } catch (IOException e) {

    }
}


}
