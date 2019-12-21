public class DoublyLinkedList<Item> {
    
    private DoublyNode<Item> first;
    private DoublyNode<Item> last;
    
    //can be accessed outside this class
    public static class DoublyNode<E>    {
        public E item;
        public DoublyNode<E> next;
        public DoublyNode<E> pre;
    }
    
    public DoublyLinkedList() {
        first = null;
        last = null;
    }
    
    /**
     * insert item at the beginning of the list
     * 
     * @param list the list to insert
     * @param item the item to be inserted
     */
    public static <T> void insertAtBeginning(DoublyLinkedList<T> list, T item) {
        
        DoublyNode<T> oldfirst = list.first;//save old first node
        
        list.first = new DoublyNode<T>();//new first node
        list.first.item = item;//save item
        list.first.next = oldfirst;//point to oldfirst
        list.first.pre = null;//pre initialize to null
        
        if(oldfirst != null) {
            oldfirst.pre = list.first;
        } else {//oldfirst is null
            list.last = list.first;
        }

    }
    
    /**
     * insert item at the end of the list
     * 
     * @param list the list to insert
     * @param item the item to be inserted
     */
    public static <T> void insertAtTheEnd(DoublyLinkedList<T> list, T item) {
        
        DoublyNode<T> oldlast = list.last;
        
        list.last = new DoublyNode<T>();
        list.last.item = item;
        list.last.next = null;
        list.last.pre = oldlast;
        
        if(oldlast == null) {
            list.first = list.last;
        } else {
            oldlast.next = list.last;
        }
        
    }
    
    /**
     * remove the first node in the list. If the first node in the list is null, then do nothing.
     * 
     * @param list the list whose first node will be removed
     */
    public static <T> void removeFromBeginning(DoublyLinkedList<T> list) {
        
        if(list.first == null) {
            return;//do nothing
        }
        
        list.first = list.first.next;//new position for first
        if(list.first == null) {//not more leave
            list.last = null;
        } else {
            list.first.pre.next = null;
            list.first.pre = null;
        }
        
    }
    
    /**
     * remove the last node in the list. If the last node in the list is null, then do nothing.
     * 
     * @param list the list whose last node will be removed
     */
    public static <T> void removeFromTheEnd(DoublyLinkedList<T> list) {
        
        if(list.last == null) {
            return;//do nothing
        }
        
        list.last = list.last.pre;
        if(list.last == null) {
            list.first = null;
        } else {
            list.last.next.pre = null;
            list.last.next = null;
        }
        
    }
    
    public static <T> DoublyNode<T> findDoublyNode(DoublyLinkedList<T> list, T item) {
        DoublyNode<T> current = list.first;
        
        while(current != null) {
            if(current.item.equals(item)) {
                break;
            }
            current = current.next;
        }
        
        return current;
    }
    
    /**
     * insert the item before the node in the list. if node is null or node isn't in the list, then do nothing.
     * 
     * @param list the list in which the node is
     * 
     * @param node the node before which the item will be inserted 
     * 
     * @param item the item to be inserted
     */
    public static <T> void insertBeforeNode(DoublyLinkedList<T> list, DoublyNode<T> node, T item) {
        
        if(node == null) {//do nothing
            return;
        }
        
        if(isInList(list, node)) {//node is in list
            DoublyNode<T> newnode = new DoublyNode<T>();
            newnode.item = item;
            
            newnode.next = node;
            if(node.pre != null) {//not first node in the list
                node.pre.next = newnode;
            } else {//first node in the list
                list.first = newnode;//change first node
            }
            newnode.pre = node.pre;
            node.pre = newnode;//should be last assign
        }
        
    }
    
    /**
     * insert the item after the node in the list. if node is null or node isn't in the list, then do nothing.
     * 
     * @param list the list in which the node is
     * 
     * @param node the node after which the item will be inserted 
     * 
     * @param item the item to be inserted
     */
    public static <T> void insertAfterNode(DoublyLinkedList<T> list, DoublyNode<T> node, T item) {
        
        if(node == null) {//do nothing
            return;
        }
        
        if(isInList(list, node)) {
            DoublyNode<T> newnode = new DoublyNode<T>();
            newnode.item = item;
            
            newnode.pre = node;
            if(node.next != null) {//not last node in the list
                node.next.pre = newnode;
            } else {//last node in the list
                list.last = newnode;
            }
            newnode.next = node.next;
            node.next = newnode;//should be last assign
        }
        
    }
    
    /**
     * remove the node in the list 
     * 
     * @param list the list in which the node is 
     * @param node the node to be removed
     */
    public static <T> void removeNode(DoublyLinkedList<T> list, DoublyNode<T> node) {
        
        if(node == null) {
            return;
        }
        
        if(isInList(list, node)) {
            if(list.first == node) {
                removeFromBeginning(list);
            }
            else if(list.last == node) {
                removeFromTheEnd(list);
            }
            else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
        }
        
    }
    
    /**
     * see if the node is in the list
     * 
     * @param list the list 
     * @param node the node
     * @return {@code true} the node is in the list
     *            {@code false} the node isn't in the list
     */
    public static <T> boolean isInList(DoublyLinkedList<T> list, DoublyNode<T> node) {
        
        DoublyNode<T> current = list.first;
        
        while(current != null) {
            if(current == node) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        String s = "";
        
        DoublyNode<Item> current = first;
        while(current != null) {
            s += current.item + " ";
            current = current.next;
        }
        
        s += first == null ? "first:null " : "first:" + first.item + " ";
        s += last == null ? "last:null " : "last:" + last.item + " ";
        
        return s;
    }

}