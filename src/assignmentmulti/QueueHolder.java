/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmulti;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author zishan
 */
public class QueueHolder {

    private static QueueHolder queueHolder;

    private static Queue<OrderHolder> queue;

    private QueueHolder() {

    }

    public synchronized static QueueHolder getObject() {

        if (queueHolder == null) {

            queueHolder = new QueueHolder();

            queue = new LinkedList();

        }

        return queueHolder;

    }

    public synchronized void push(OrderHolder order) {

        queue.add(order);
    }

    public synchronized OrderHolder pop() {

        return queue.remove();
    }

    public synchronized boolean isEmpty() {

        return queue.isEmpty();
    }

}
