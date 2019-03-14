/* some code is taken from https://www.sanfoundry.com/java-program-implement-binary-tree/ */

package Trees;

import java.util.*;
/* Class BTNode */

class BTNode    {
    BTNode left, right;
    int data;

    /* Constructor */
    public BTNode() {
        left = null;
        right = null;
        data = 0;
    }
    /* Constructor */
    public BTNode(int n)    {
        left = null;
        right = null;
        data = n;
    }


    public void setLeft(BTNode n)    {
        left = n;
    }

    public void setRight(BTNode n) {
        right = n;
    }

    public BTNode getLeft()    {
        return left;
    }

    public BTNode getRight()    {
        return right;
    }

    public void setData(int d)     {
        data = d;
    }

    public int getData()    {
        return data;
    }
}

/* Class BT */
class BT    {
    private BTNode root;

    public BT()    {
        root = null;
    }

    public boolean isEmpty()    {
        return root == null;
    }

    public void insert(int data)    {
        root = insert(root, data);
    }

    private BTNode insert(BTNode node, int data)
    {
        Queue<BTNode> queue = new LinkedList<BTNode>();

        if(node == null)
            node = new BTNode(data);
        else    {
            queue.add(node);
            while(queue.size()>0){
                BTNode n = queue.poll();
                if(n.getLeft()!=null && n.getRight()!=null){
                    queue.add(n.getLeft());
                    queue.add(n.getRight());
                    continue;
                }
                if(n.getLeft() == null){
                    n.left = insert(n.left,data);
                    break;
                }
                if(n.getRight() == null){
                    n.right = insert(n.right,data);
                    break;
                }
            }
        }

//        if (node == null)
//           node = new BTNode(data);
//        else    {
//            if (node.getRight() == null)
//                node.right = insert(node.right, data);
//            else
//                node.left = insert(node.left, data);
//        }
        return node;
    }

    public BTNode BSTInsert(BTNode head,int data){
        BTNode tempHead = head;
        BTNode newNode = new BTNode(data);
        BTNode prev = null;
        if(head == null){
            head = newNode;
            return head;
        }

        while(head!=null){
            prev = head;
            if(head.data<=data){
                head = head.right;
            }else{
                head = head.left;
            }
        }

        if(prev.data<=data){
            prev.left = newNode;
        }else{
            prev.right = newNode;
        }
        return tempHead;
    }
    public int countNodes() {
        return countNodes(root);
    }


    private int countNodes(BTNode r) {
        if (r == null)
            return 0;
        else    {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(BTNode r, int val) {
        if (r.getData() == val)
            return true;
        if (r.getLeft() != null)
            if (search(r.getLeft(), val))
                return true;
        if (r.getRight() != null)
            if (search(r.getRight(), val))
                return true;
        return false;
    }

    public void inorder()   {
        inorder(root);
    }

    private void inorder(BTNode r)  {
        if (r != null) {
            inorder(r.getLeft());
            System.out.print(r.getData() +" ");
            inorder(r.getRight());
        }
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(BTNode r) {
        if (r != null)  {
            System.out.print(r.getData() +" ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(BTNode r)    {
        if (r != null) {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getData() +" ");
        }
    }

    public void levelOrder(){
        levelOrder(root);
    }

    private void levelOrder(BTNode r){
        Queue<BTNode> queue = new LinkedList<BTNode>();

        queue.add(r);

        while(queue.size()>0){
            BTNode n = queue.poll();
            System.out.print(n.getData()+" ");
            if(n.getLeft() !=null){
                queue.add(n.getLeft());
            }
            if(n.getRight() !=null){
                queue.add(n.getRight());
            }
        }

    }

    public void verticalOrder(){
        List<List<Integer>> list = verticalOrder(root);

        for(List<Integer> l:list){
            System.out.println(l);
        }

    }

    public List<List<Integer>> verticalOrder(BTNode root){
        List<List<Integer>> result = new ArrayList<>();

        if(root == null){
            return result ;
        }

        Queue<BTNode> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

        queue.add(root);
        cols.add(0);

        int min = 0; //keeps track of min
        int max = 0; // and max horizontal distances in hash map

        while(queue.size()>0){
            BTNode node = queue.poll();
            int col = cols.poll();

            if(!hm.containsKey(col)){
                hm.put(col,new ArrayList<Integer>());
            }
            hm.get(col).add(node.data);       // Update the horizontal distance for root

            if(node.getLeft()!=null){         // Check for left and right child and update
                queue.add(node.getLeft());    // HD in Hash Table
                cols.add(col-1);              // and Enqueue left and right child
                min = Math.min(min,col-1);
            }

            if(node.getRight()!= null){
                queue.add(node.getRight());
                cols.add(col+1);
                max = Math.max(max,col+1);
            }
        }
        for(int i=min;i<=max;i++){
            System.out.println("hm"+hm.get(i));
            List<Integer> l1 = hm.get(i);
            //result.add(hm.get(i));
            result.add(l1);
        }
        return result;
    }
}

/* Class binaryTree */
public class BinaryTree {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        /* Creating object of BT */
        BT bt = new BT();
        /*  Perform tree operations  */
        System.out.println("Binary Tree Test\n");
        char ch;



        do  {

            System.out.println("\nBinary Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");

            int choice = scan.nextInt();

            switch (choice) {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    bt.insert( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : "+ bt.search( scan.nextInt() ));
                    break;
                case 3 :
                    System.out.println("Nodes = "+ bt.countNodes());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ bt.isEmpty());
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }

            /*  Display tree  */
            System.out.print("\nPost order : ");
            bt.postorder();
            System.out.print("\nPre order : ");
            bt.preorder();
            System.out.print("\nIn order : ");
            bt.inorder();
            System.out.print("\nLevel order : ");
            bt.levelOrder();
            System.out.print("\nVertical order : ");
            bt.verticalOrder();
            System.out.println("\n\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}