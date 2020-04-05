/**
 * A BST implementation of a dictionary that stores key value pairs
 * 
 * Author: kshitij sawhney
 * Version : 4/4/2020
 */
public class BSTDictionary<V extends String ,K extends SortableString>{
    protected BSTNode rootNode,currentNode,parentNode,newNode; 
    
    /**
     * prints the tree using inorder traversal
     * @param BSTNode node being visited in that call
     */
    protected void inorder(BSTNode node){ // recursive helper function to print the the tree in order 
        if(node==null){
            return;
        }
        inorder(node.getLeft()); // visit the left node
        System.out.print(node.getKey()+" "); // print the current node
        inorder(node.getRight());  // visit the right node
    } 
    
    /**
     * returns the smallest node present in the tree rooted by current
     * @param BSTNode the root node for the tree under speculation
     * @return BSTNode the smallest value in the subtree
     */
    protected BSTNode minValue(BSTNode current){//helper function to return the next smallest node presetn in the tree starting at current
        BSTNode min = current;
        while(current.getLeft()!=null){ // if node to the left exists, thats the new minimum.
            min = current.getLeft();
            current = current.getLeft();
        }
        return min;
    }
    
    /**
     *recursive function to return the length of the longest branch in the subtree in the tree rooted by node
     *@param BSTNode the root node for the tree under speculation
     *@return int the length of the longest brach
     */
    protected int size(BSTNode node) // recursive helper function that returns the length of the largest subtree present
    { 
        if (node == null) 
            return 0; 
        else
            return(1 + Math.max(size(node.left),size(node.right))); // add one to the larger of the 2 children
    } 
    
    
    /**
     * helper function that recursively looks for a node to delet and once found, removes its reference from the tree
     * @param BSTNode root the root node of the given tree
     * @param SortableString the key of the node to be deleted
     * @return BSTNode the reference to the original rootnode
     */
    protected BSTNode recursiveDelete(BSTNode root,SortableString K){//function to delete
        { 
            //tree is empty 
            if (root == null)  return root; 

            // Otherwise, recur down the tree 
            if (K.compareTo(root.getKey())<0) {
                root.setLeft(recursiveDelete(root.getLeft(),K));
            }else if (K.compareTo(root.getKey())>0){
                root.setRight(recursiveDelete(root.getRight(),K)); 
            }
            // if key is same as root's key, then This is the node  to be deleted 
            else
            { 
                // node with only one child or no child 
                if (root.getLeft() == null) 
                    return root.getRight(); 
                else if (root.getRight() == null) 
                    return root.getLeft(); 
                // node with two children: Get the smallest in the right subtree 
                root = minValue(root.getRight()); // defining the variable so as to not have to run the recursive function twice. 
                // Delete the successor 
                root.setRight(recursiveDelete(root.getRight(),(SortableString) root.getKey())); 
            } 
            return root; 
        } 
    }
    
    /**
     * recursive search function which returns the Node if found
     * @param SortableString key for the node being searched for
     * @param BSTNode current the current node under consideration
     * @return BSTNode the node with the key n=being searched for
     */
    protected BSTNode recursiveSearch(SortableString K, BSTNode current){ // searches for a given node and return it if its found or null if it is not.
        if (current==null || K.compareTo(current.getKey()) == 0){
            return current;
        }else if ((K.compareTo(current.getKey()) > 0)){
            return recursiveSearch(K,current.getRight());
        }else{
            return recursiveSearch(K,current.getLeft());
        }
    }
    
    /**
     * function that is supposed to print the tree inorder
     */
    public void printTree(){
        inorder(rootNode); // only purpose is to call the recursive function
    }
    
    /**
     * function to delete a node with a certain key
     * @param SortableString Key for the node to be deleted.
     */
    public void delete(SortableString K){ // only function is to call the recursive helper function
        rootNode = recursiveDelete(rootNode,K);
    }
    
    /**
     * function to search for a node with a certain key
     * @param SortableString Key for the node being searched for.
     */
    public BSTNode search(SortableString K){ // only calls the recursive function
        return recursiveSearch(K,rootNode);
    }
    
    /**
     * return the lenght of the lingest subtree branch present in the tree.
     */
    public int depth(){ 
        return size(rootNode);
    }
    
    /**
     * recursive searches and inserts a given key-value pair into BST
     * @param SortableString Key for the given node
     * @param String value of the given node to be inserted
     */
    public void insert(SortableString K,String V){ //recursive function to add nodes into the binary search tree
        if(rootNode==null){ // empty tree, new node
            rootNode = new BSTNode(K,V,null,null);
        }else if(parentNode.getLeft()==null && K.compareTo(parentNode.getKey())< 0){ //comparing too its parents to decide whether to attach 
            parentNode.setLeft(new BSTNode (K,V,null,null));  //to the left or the right
        }else if(parentNode.getRight()==null && K.compareTo(parentNode.getKey())>= 0){
            parentNode.setRight(new BSTNode (K,V,null,null));
        }else if (K.compareTo(parentNode.getKey())>= 0){ // searching for an appropriate place to insert the node
            parentNode = parentNode.getRight();
            insert(K,V);
        }else if(K.compareTo(parentNode.getKey()) < 0){
            parentNode =parentNode.getLeft();
            insert(K,V);
        }
        parentNode=rootNode;
    }
}