/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmulti;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author zishan
 */
public class Waiter extends Thread {

    Set<MenuBase> menuSet;

    int number = 0;

    Waiter(Set<MenuBase> menuSet) {

        this.menuSet = menuSet;
    }

    @Override
    public void run() {

        QueueHolder queue = QueueHolder.getObject();

        Scanner sc = new Scanner(System.in);

        while (1 == 1) {

            System.out.println("Enter item id :");

            int Id = sc.nextInt();

            Iterator<MenuBase> it = menuSet.iterator();

            while (it.hasNext()) {

                MenuBase menu = it.next();

                OrderHolder order = new OrderHolder(number, menu);

                if (menu.getId() == Id) {

                    queue.push(order);

                    System.out.println("OrderNumber:ORD" + (number++) + " for " + menu.getMenuName() + " has been placed at " + Calendar.getInstance().getTime());

                    synchronized (Locker.locker) {

                        Locker.locker.notify();

                    }
                    
                    break;
                }

            }

        }

    }

}
