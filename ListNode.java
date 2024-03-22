package org.example;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListNode<E> implements LinkedList<E> {
    Node<E> head;
    int size;
    ListNode(){}
    ListNode(E value){
        head = new Node<>(value);
        size++;
    }
    @SafeVarargs
    ListNode(E ... value){
        head = new Node<>(value[0]);
        size++;
        for (int i = 1; i < value.length; i++) {
            this.add(value[i]);
        }
    }
    public void add(E value){ //вставка в конец
        Node<E> _newNext = new Node<>(value);
        Node<E> _current = head;
        if (_current == null){
            head=_newNext;
            size++;
            return;
        }
        while (_current.next != null) {
            _current = _current.next; //передвигаем лист к концу
        }
        _current.next = _newNext;
        size++;
    }
    public void clear(){
        head = null;
        size = 0;
    }
    public void add(int position, E value) throws IndexOutOfBoundsException{
        Node<E> _current = head;
        int counter = 0;
        Node<E> newNext = new Node<>(value);
        if(position == 0){
            newNext.next = _current;
            head = newNext;
            return;
        }
        if(position > size){
            throw new IndexOutOfBoundsException();
        }
        while(counter < position-1 && _current != null){
            _current = _current.next;
            counter++;
        }
        newNext.next = _current.next;
        _current.next = newNext;
        size++;
    }
    public void removeIndex(int index) throws IndexOutOfBoundsException{
        Node<E> _current = head;
        int counter = 0;
        if(index == 0){
            head = head.next;
            return;
        }
        if(index > size){
            throw new IndexOutOfBoundsException();
        }
        while(counter < index-1 && _current != null){
            _current = _current.next;
            counter++;
        }
        _current.next = _current.next.next;
        size--;
    }
    public void removeElement(E value){
        Node<E> _current = head;
        while(_current != null){
            if(_current.value == value){
                _current.next = _current.next.next;
                _current.next.prev = _current;
            }
            _current = _current.next;
        }
        size--;
    }
    public E findIndex(int index) throws IndexOutOfBoundsException{
        Node<E> _current = head;
        int counter = 0;
        if(index > size){
            throw new IndexOutOfBoundsException();
        }
        while(counter < index && _current != null){
            _current = _current.next;
            counter++;
        }
        return _current.value;
    }
    public int findElement(E value){
        Node<E> _current = head;
        int counter = 0;
        while(_current != null){
            if(_current.value == value){
                return counter;
            }
            _current = _current.next;
            counter++;
        }
        return -1;
    }
    public E get(int index){
        Node<E> current = head;
        if(index>=size){
            throw new IndexOutOfBoundsException();
        }else{
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }
    public java.util.Iterator<E> iterator(){
        return new Iterator();
    }
    private class Iterator implements java.util.Iterator<E>{
        int index = 0;
        int lastIndex = -1;

        public boolean hasNext(){
            return index != size; // проверка на то что индекс входил в лист
        }

        public E next(){
            try{
                E next = get(index); // сохраняем значение
                lastIndex = index; // предыдущие значение сдвигаем на единицу
                index++; //сдвигаем индекс
                return next;
            }catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException(e);
            }
        }
        public void remove(){
            if(lastIndex<0){ //так как ремув мы вызываем в @hasNext() то нам нужно проверить если первый элемент
                throw new NoSuchElementException();
            }
            removeIndex(lastIndex); //удаляем элемент

            index = lastIndex; // индекс ставим чтобы он сместился на ластовый
            lastIndex = index-1; // индекс ставим на предыдущие индекса
        }
    }
}
