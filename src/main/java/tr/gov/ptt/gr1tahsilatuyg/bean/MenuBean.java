/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMenu;

@ManagedBean
@SessionScoped
public class MenuBean {

    private final DefaultMenuModel simpleMenuModel = new DefaultMenuModel();

    @ManagedProperty(value = "#{tahsilatKisiBean}")
    private TahsilatKisiBean tahsilatKisiBean;

    public MenuBean() {

    }

    @PostConstruct
    public void init() {
        List<TahsilatMenu> menuListesi = tahsilatKisiBean.getKisi().getTahsilatMenuList();

        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");

        DefaultMenuItem menuItem;

        for (TahsilatMenu menu : menuListesi) {
            menuItem = new DefaultMenuItem();
            menuItem.setValue(menu.getBaslik());
            menuItem.setCommand(menu.getLink());
            subMenu.addElement(menuItem);
        }

        simpleMenuModel.addElement(subMenu);
    }

    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }

    public void setTahsilatKisiBean(TahsilatKisiBean tahsilatKisiBean) {
        this.tahsilatKisiBean = tahsilatKisiBean;
    }
}
