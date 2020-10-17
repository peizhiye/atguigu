package com.atguigu.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread ht = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(ht).start();
        }
    }

}

class HelloThread implements Runnable{

//    private static List<String> list = new ArrayList<String>();	// 异常 java.util.ConcurrentModificationException
//	private static List<String> list = Collections.synchronizedList(new ArrayList<String>());	// 异常 java.util.ConcurrentModificationException

	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {

        Iterator<String> it = list.iterator();

        /*	注意：无论是否使用 Collections.synchronizedList 对 List 进行同步化加工，
                下面这个循环的结果都会抛出 java.util.ConcurrentModificationException 异常，
                因为都是对同一个 List 进行边迭代、边添加（或删除）的操作。
                java.util.ArrayList$Itr.next -> java.util.ArrayList$Itr.checkForComodification
                    if (modCount != expectedModCount)
                        throw new ConcurrentModificationException();
                而 CopyOnWriteArrayList 是采用写时复制的原理，操作的并不是同一个 List。
         */
        while(it.hasNext()){
            System.out.println(it.next());

            list.add("AA");
        }

    }

}