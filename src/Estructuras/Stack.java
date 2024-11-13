package Estructuras;

public class Stack<Element> {
    private final int tamaño = 10;
    private Element[] stack;
    private int cuenta;


    public Stack() {
        this.stack = (Element[]) new Object [this.tamaño];
        this.cuenta = 0;
    }

    public Stack(int tam) {
        this.stack = (Element[]) new Object[tam];
        this.cuenta = 0;
    }

    public void push(Element elemento) {
        if(this.isFull()){
            throw new RuntimeException("Stack Full");
        }
        this.stack[this.cuenta] = elemento;
        this.cuenta++;
    }

    public Element pop() {
        if(this.isEmpty()){
            throw new RuntimeException("Stack Full");
        }
        --this.cuenta;
        return this.stack[this.cuenta];
    }

    public Element peek(){
        if(this.isEmpty()){
            throw new RuntimeException("Stack Empty");
        }
        return this.stack[this.cuenta-1];
    }

    public boolean isEmpty() {
        return this.cuenta <= 0;
    }

    public boolean isFull() {
        return this.cuenta >= this.stack.length;
    }

    public int count(){
        return this.cuenta;
    }

    @Override
    public String toString() {
        String stackS = "[";
        if(!this.isEmpty()){
            stackS += this.stack[0].toString();
            for(int i = 1; i < this.cuenta; i++){
                stackS += ", " + this.stack[i].toString();
            }
        }

        stackS += "]";
        return "Stack{" +
                "tamaño=" + tamaño +
                ", stack=" + stackS +
                ", cuenta=" + cuenta +
                '}';
    }
}

