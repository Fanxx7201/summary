package com.javabasic.lianbiao;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.lianbiao
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 10:29
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ReverseKth {
    /**
     * 链表元素的节点
     * @param <T> 值类型
     */
    static class Node<T>{
        //节点值
        T value;
        //节点的下一个节点的指针
        Node<T> next;
        public Node(T value){
            this(value,null);
        }
        public Node(T value,Node next){
            this.next=next;
            this.value=value;
        }
    }

    public Node getKth(Node head,int k){
        Node a=head,b=head;
        for(int i=0;i<k-1;i++){
            b=b.next;
        }
        while(b.next!=null){
            a=a.next;
            b=b.next;
        }
        return a;
    }

    public static void main(String[] args){
        Node<String> a=new Node<String>("A");
        Node<String> b=new Node<String>("B");
        Node<String> c=new Node<String>("C");
        Node<String> d=new Node<String>("D");
        Node<String> e=new Node<String>("E");
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;
        ReverseKth list=new ReverseKth();
        Node result=list.getKth(a,2);
        System.out.println(result.value);
    }

}
