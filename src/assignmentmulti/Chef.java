/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmulti;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zishan
 */
public class Chef extends Thread {

    File file;

    Chef(File file) {

        this.file = file;

    }

    @Override
    public void run() {

        QueueHolder queue = QueueHolder.getObject();

        while (1 == 1) {

            if (queue.isEmpty()) {

                synchronized (Locker.locker) {

                    try {

                        Locker.locker.wait();

                    } catch (InterruptedException ex) {

                        Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            }

            OrderHolder order = queue.pop();

            MenuBase menu = order.getMenu();

            Calendar cal = Calendar.getInstance();

            String time = (cal.get(Calendar.HOUR) < 10 ? "0" + cal.get(Calendar.HOUR) : cal.get(Calendar.HOUR)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE)) + ":" + (cal.get(Calendar.SECOND) < 10 ? "0" + cal.get(Calendar.SECOND) : cal.get(Calendar.SECOND)) + " " + (cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

            writeIntoFile("Chef:Picked up ORD" + order.getOrderId() + " at " + time);

            try {

                /**
                 * chef is cooking
                 *
                 */
                writeIntoFile("Chef:Cooking " + menu.getMenuName() + "...");

                sleep(menu.getTime() * 60 * 1000);

                cal = Calendar.getInstance();

                time = (cal.get(Calendar.HOUR) < 10 ? "0" + cal.get(Calendar.HOUR) : cal.get(Calendar.HOUR)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE)) + ":" + (cal.get(Calendar.SECOND) < 10 ? "0" + cal.get(Calendar.SECOND) : cal.get(Calendar.SECOND)) + " " + (cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

                writeIntoFile("Chef:Finished making " + menu.getMenuName() + " for ORD" + order.getOrderId() + " at " + time);

                /**
                 * cooked
                 *
                 */
            } catch (InterruptedException ex) {

                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }

    public void writeIntoFile(String element) {

        //File file = new File("/home/zishan/Pictures/amit/OrderProcessed.txt");
        try {

            FileWriter fileWritter = new FileWriter(file, true);

            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            bufferWritter.write(element);

            bufferWritter.write('\n');

            bufferWritter.close();

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
