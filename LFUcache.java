class Node{
    int key;
    int value;
    int count;
    Node prev;
    Node next;
    Node(int key,int value){
        this.value = value;
        this.key = key;
        this.count = 1;
    }
}
class list{
    Node head;
    Node tail;
    int size;
    public list(){
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    public void addFront(Node node){
        Node temp = head.next;
        node.next = temp;
        node.prev = head;
        head.next = node;
        temp.prev = node;
        size++;
    }
    public void remove(Node node){
        Node nodePrev = node.prev;
        Node nodeNext = node.next;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;
        size--;
    }

}
class LFUCache {
    Map<Integer,Node> keyNode;
    Map<Integer,list> freqList;
    int minFreq;
    int currSize;
    int maxCap;
    public LFUCache(int capacity) {
        maxCap= capacity;
        minFreq = 0;
        currSize = 0;
        keyNode = new HashMap<>();
        freqList = new HashMap<>();
    }
    private void updateFrequency(Node node){
        keyNode.remove(node.key);
        freqList.get(node.count).remove(node);
        if(node.count == minFreq && freqList.get(node.count).size == 0){
            minFreq++;
        }
        list higherOrder = new list();
        if(freqList.containsKey(node.count+1)){
            higherOrder = freqList.get(node.count+1);
        }
        node.count += 1;
        higherOrder.addFront(node);
        keyNode.put(node.key,node);
        freqList.put(node.count,higherOrder);
    }

    public int get(int key) {
        if(keyNode.containsKey(key)){
            Node node = keyNode.get(key);
            int value = node.value;
            updateFrequency(node);
            return value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(maxCap == 0){
            return;
        }
        if(keyNode.containsKey(key)){
            Node node = keyNode.get(key);
            node.value = value;
            updateFrequency(node);
        }
        else{
            if(maxCap == currSize){
                list lst = freqList.get(minFreq);
                keyNode.remove(lst.tail.prev.key);
                freqList.get(minFreq).remove(lst.tail.prev);
                currSize--;
            }
            currSize++;
            minFreq = 1;
            list freqL = new list();
            if(freqList.containsKey(minFreq)){
                freqL = freqList.get(minFreq);
            }
            Node node = new Node(key,value);

            freqL.addFront(node);
            keyNode.put(key,node);
            freqList.put(node.count,freqL);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
