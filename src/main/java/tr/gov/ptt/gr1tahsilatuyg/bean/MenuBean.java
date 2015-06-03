/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@SessionScoped
public class MenuBean {

    private final DefaultMenuModel simpleMenuModel = new DefaultMenuModel();

    public MenuBean() {

        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");

        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Giriş");
        menuItem.setUrl("tahsilatGiris.xhtml");
        subMenu.addElement(menuItem);

        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Sonuç");
        menuItem.setUrl("tahsilatSonuc.xhtml");
        subMenu.addElement(menuItem);

        simpleMenuModel.addElement(subMenu);

        subMenu = new DefaultSubMenu();
        subMenu.setLabel("Yönetici İşlemleri");

        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Yönetici");
        menuItem.setUrl("tahsilatYonetici.xhtml");
        subMenu.addElement(menuItem);
        
        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Rapor");
        menuItem.setUrl("tahsilatRapor.xhtml");
        subMenu.addElement(menuItem);
        
        simpleMenuModel.addElement(subMenu);
    }

    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
}
