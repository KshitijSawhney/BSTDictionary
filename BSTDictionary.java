public class BSTDictionary<V extends String ,K extends SortableString>{
    BSTNode rootNode,currentNode,parentNode,newNode; 
    protected void inorder(BSTNode node){
        if(node==null){
            return;
        }
        inorder(node.getLeft());
        System.out.print(node.getKey()+" ");
        inorder(node.getRight());
    } 

    protected BSTNode minValue(BSTNode current){
        BSTNode min = current;
        while(current.getLeft()!=null){
            min = current.getLeft();
            current = current.getLeft();
        }
        return min;
    }
    protected int size(BSTNode node) 
    { 
        if (node == null) 
            return 0; 
        else
            return(1 + Math.max(size(node.left),size(node.right))); 
    } 
    
    protected BSTNode recursiveDelete(BSTNode root,SortableString K){
        { 
            /* Base Case: If the tree is empty */
            if (root == null)  return root; 

            /* Otherwise, recur down the tree */
            if (K.compareTo(root.getKey())<0) {
                root.setLeft(recursiveDelete(root.getLeft(),K));
            }else if (K.compareTo(root.getKey())>0){
                root.setRight(recursiveDelete(root.getRight(),K)); 
            }
            // if key is same as root's key, then This is the node 
            // to be deleted 
            else
            { 
                // node with only one child or no child 
                if (root.getLeft() == null) 
                    return root.getRight(); 
                else if (root.getRight() == null) 
                    return root.getLeft(); 
                // node with two children: Get the inorder successor (smallest 
                // in the right subtree) 
                root = minValue(root.getRight());
                // Delete the inorder successor 
                root.setRight(recursiveDelete(root.getRight(),(SortableString) root.getKey())); 
            } 
            return root; 
        } 
    }

    protected BSTNode recursiveSearch(SortableString K, BSTNode current){
        if (current==null || K.compareTo(current.getKey()) == 0){
            return current;
        }else if ((K.compareTo(current.getKey()) > 0)){
            return recursiveSearch(K,current.getRight());
        }else{
            return recursiveSearch(K,current.getLeft());
        }
    }

    public void printTree(){
        inorder(rootNode);
    }

    public void delete(SortableString K){
        rootNode = recursiveDelete(rootNode,K);
    }

    public BSTNode search(SortableString K){
        return recursiveSearch(K,rootNode);
    }

    public int depth(){
        return size(rootNode);
    }

    public void insert(SortableString K,String V){
        if(rootNode==null){
            rootNode = new BSTNode(K,V,null,null);
        }else if(parentNode.getLeft()==null && K.compareTo(parentNode.getKey())< 0){
            parentNode.setLeft(new BSTNode (K,V,null,null));
        }else if(parentNode.getRight()==null && K.compareTo(parentNode.getKey())>= 0){
            parentNode.setRight(new BSTNode (K,V,null,null));
        }else if (K.compareTo(parentNode.getKey())>= 0){
            parentNode = parentNode.getRight();
            insert(K,V);
        }else if(K.compareTo(parentNode.getKey()) < 0){
            parentNode =parentNode.getLeft();
            insert(K,V);
        }
        parentNode=rootNode;
    }
}