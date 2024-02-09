import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        if(length==0){
            front = insnode;
            back = insnode;
        }else{
            back.next = insnode;
            insnode.previous = back;
            back = insnode;
        }
        length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        if(front==null || back== null){
            return false;
        }else if(front==delnode){
            front = front.next;
            if(front!=null){
                front.previous = null;
            }else{
                back = null;
            }
        }else if(back==delnode){
            back = back.previous;
            if(back!=null){
                back.next = null;
            }else{
                front = null;
            }
        }else{
            Node<RequestData> temp = front.next;
            while(temp != null){
                if(temp==delnode){
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                    break;
                }
                temp = temp.next;
            }
            if(temp == null){
                return false;
            }
        }
        length--;
        return true;
    }

    public Node<RequestData> find(int ISBN) {
        if(front.data.ISBN==ISBN){
            return front;
        }else if(back.data.ISBN==ISBN){
            return back;
        }else{
            Node<RequestData> tmp = front.next;
            while(tmp != null){
                if(tmp.data.ISBN==ISBN){
                    return tmp;
                }
                tmp = tmp.next;
            }
        }
        Node<RequestData> nrd = null;
        return nrd;
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
