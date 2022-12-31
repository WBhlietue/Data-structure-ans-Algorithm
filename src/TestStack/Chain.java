package TestStack;

public class Chain {
    public Object item;
    public Chain previous;
    public Chain(Object obj){
        item = obj;
        previous = null;
    }
    public Chain(Object obj, Chain pre){
        item = obj;
        previous = pre;
    }
}
