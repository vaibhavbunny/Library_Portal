import java.util.*;

import Includes.*;

public class LibraryStorage {
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        RequestData m = rqQueue.getFront();
        int isbn = m.ISBN;
        int userid = m.UserID;
        BookData b = storage.get(isbn);
        if (b != null && !b.BorrowedStatus) {
            b.BorrowedByUserID = userid;
            b.BorrowedStatus = true;
            MyDate myDate = new MyDate();
            myDate.month = b.DateOfReturn.month + 1;
            myDate.year = b.DateOfReturn.year;
            b.DateOfReturn = myDate;
            rqQueue.pop();
            return true;
        }else if(b == null){
            rqQueue.pop();
            return false;
        }
        Node<RequestData> newNode = new Node<>();
        newNode.data = m;
        boolean boo = prLinkedList.insert(newNode);
        rqQueue.pop();
        return false;
    }

    public boolean processReturn(BookData book) {
        int i = book.ISBN;
        Node<RequestData> name = prLinkedList.find(i);
        if(name!=null){
            storage.get(i).BorrowedStatus = true;
            storage.get(i).BorrowedByUserID = name.data.UserID;
            MyDate md = name.data.RequestDate;
            MyDate vai = storage.get(i).DateOfReturn;
            if(md.month + 1 > 12){
                vai.month = 1;
                vai.year = md.year + 1;
            }else{
                vai.month = md.month + 1;
                vai.year = md.year;
            }
            boolean ans = prLinkedList.delete(name);
            return true;
        }
        storage.get(i).BorrowedStatus = false;
        return false;
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }

}
