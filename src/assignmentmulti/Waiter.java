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

    int number = 1;

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

                    Calendar cal = Calendar.getInstance();

                    String time = (cal.get(Calendar.HOUR) < 10 ? "0" + cal.get(Calendar.HOUR) : cal.get(Calendar.HOUR)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE)) + ":" + (cal.get(Calendar.SECOND) < 10 ? "0" + cal.get(Calendar.SECOND) : cal.get(Calendar.SECOND)) + " " + (cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

                    System.out.println("OrderNumber:ORD" + (number++) + " for " + menu.getMenuName() + " has been placed at " + time);

                    synchronized (Locker.locker) {

                        Locker.locker.notify();

                    }

                    break;
                }

            }

        }

    }

}
