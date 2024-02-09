import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;

    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        return this.length;
    }

    public void push(int ISBN, int UserID) {
        Node<RequestData> n = new Node<>();
        n.data = new RequestData();
        n.data.ISBN = ISBN;
        n.data.UserID = UserID;
        //n.data.MyDate = myDate;
        if(front==null && back==null){
            front = n;
            back = n;
        }else{
            back.next = n;
            n.previous = back;
            back = n;
        }
        length++;
        return;
    }

    public void pop() {
        if(front==null){
            return;
        }
        front = front.next;
        if(front!=null){
            front.previous = null;
        }else{
            back = null;
        }
        length--;
        return;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
