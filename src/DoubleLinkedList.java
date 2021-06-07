
public class DoubleLinkedList {

    //a varaible to keep tack of the number of nodes
    public int size = 0;

    // Defining the node itslef
    public class Node {
        public String data;
        //indexy is the unquie id for the indexing system
        public int indexy;
        public Node next;
        public Node prev;

    }// end of the node class

    Node head = null;
    Node tail = null;

    //a praivate function to create a node and connect it with the node before it and after it
    private Node CreateNode(int Indexy, String Data, Node Prev, Node Next) {
        Node node = new Node();
        node.indexy = Indexy;
        node.prev = Prev;
        node.next = Next;
        node.data = Data;
        return node;
    }

    // get the node by index and return the desiered node
    public Node get(int indexy) {
        //try and catch statment to handle errors
        try {
            //we start with the head and scan the last in a linear fashion to find the desierd node
            Node node = head;
            if (indexy == 0) {
                return node;
            } else {
                for (int i = 0; i < size; i++) {
                    node = node.next;
                    if (node.indexy == indexy) {
                        break;
                    }
                }
            }
            return node;
        } catch (Exception e) {
            System.out.println("No Node with this index was found");
            return null;

        }
    }

    //thiss function is just a function to keep track of all nodes and thier pointers
    public void displayAllElements() {
        if (head == null) {
            System.out.println("Empty list");
        } else {
            Node temp = head;
            while (temp != null) {
                System.out.println("value: " + temp.data);
                System.out.println("Index: " + temp.indexy);
                temp = temp.next;
            }
            temp = head;
            while (temp != null) {
                System.out.println("------------");
                if (temp.prev == null) {
                    System.out.println("null");
                } else {
                    System.out.println("Prev: " + temp.prev.data);
                }

                System.out.println("value: " + temp.data);
                System.out.println("Index: " + temp.indexy);

                if (temp.next == null) {
                    System.out.println("null");
                } else {
                    System.out.println("Next: " + temp.next.data);
                }
                temp = temp.next;
            }
        }
    }

    //creating node function
    public void AddNode(String Data) {
        //we create nodes in a linear fashion, where we add a new node after the tail
        if (head == null) {
            head = CreateNode(0, Data, null, null);
            size = size + 1;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = CreateNode(size, Data, temp, tail);
            size = size + 1;
        }
    }

    //this is a private function to rearrange the indexes incase we delete a node
    //so that every node will take the next node index
    private void rearrange(int indexy) {
        Node temp = get(indexy);

        for (int i = indexy; i < size - 1; i++) {
            temp = temp.next;
            temp.indexy = temp.indexy - 1;
        }
    }

    //a node to delete the nodes
    public void delete(int indexy) {
        try {
            Node temp = get(indexy);
            // if we will delete the firsts node, we will just assign it as head
            if (indexy == 0) {
                rearrange(indexy);
                head = temp.next;
                head.prev = null;
            }
            // if we will delete the last node, we will assign the one before it as the tail
            else if (indexy == size - 1) {
                rearrange(indexy);
                temp = temp.prev;
                temp.next = temp.next.next;

            } else {
                rearrange(indexy);
                temp = temp.prev;
                temp.next = temp.next.next;
                temp = temp.next;
                temp.prev = temp.prev.prev;
            }
            size = size - 1;
        } catch (Exception e) {
            System.out.println("It look like you're trying to delete an index that doesn't exist");
        }
    }

    //this function change the data stored the node with the spacified index into the new data
    public void set(int indexy, String Data) {
        get(indexy).data = Data;
    }

    //node to calculate the size of the full list
    public int size() {
        int count = 0;
        if (head == null) {
            count = 0;
        } else {
            Node temp = head;
            while (temp != null) {
                temp = temp.next;
                count++;
            }
        }
        return count;
    }

}
