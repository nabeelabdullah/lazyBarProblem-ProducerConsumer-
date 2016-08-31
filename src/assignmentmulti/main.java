/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmulti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zishan
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);

        if (!file.exists()) {

            throw new FileNotFoundException();
        }

        Set set = new HashSet();

        MenuBase m1 = new MenuBase(1, "Sandwich", 3);

        MenuBase m2 = new MenuBase(2, "Coffee", 2);

        MenuBase m3 = new MenuBase(3, "Cereal", 1);

        MenuBase m4 = new MenuBase(4, "Pizza", 5);

        set.add(m1);

        set.add(m2);

        set.add(m3);

        set.add(m4);

        Waiter waiter = new Waiter(set);

        Chef chef = new Chef(file);

        waiter.start();

        chef.start();

    }

}
