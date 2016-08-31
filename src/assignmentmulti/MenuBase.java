/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmulti;

/**
 *
 * @author zishan
 */
public class MenuBase {

    private final int Id;

    private final String menuName;

    private final int time;

    MenuBase(int Id, String menuName, int time) {

        this.Id = Id;

        this.menuName = menuName;

        this.time = time;

    }


    @Override
    public int hashCode() {

        return Id;
    }

    @Override
    public boolean equals(Object menu) {

        if (menu instanceof MenuBase) {

            MenuBase menu1 = (MenuBase) menu;

            if (this.Id == menu1.Id) {

                return true;

            }
        }
        return false;

    }

    public int getId() {
        return Id;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getTime() {
        return time;
    }

}
