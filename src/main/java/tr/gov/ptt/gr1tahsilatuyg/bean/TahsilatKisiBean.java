/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatKisiService;
import tr.gov.ptt.gr1tahsilatuyg.util.JSFUtil;

@ManagedBean
@SessionScoped
public class TahsilatKisiBean {
    @EJB
    private TahsilatKisiService kisiService;
    
    private TahsilatKisi kisi;

    public TahsilatKisiBean() {
        kisi = new TahsilatKisi();
    }
    
    public String girisKontrol()
    {
        TahsilatKisi t_kisi = kisiService.giriseYetkilimi(kisi);
        if (t_kisi != null) {
            this.kisi = t_kisi;
            /*for (TahsilatMenu menu : t_kisi.getTahsilatMenuList()) {
                System.out.println("Menüler: " + menu.getBaslik());
            }*/
            
            HttpSession session = JSFUtil.getSession();
            session.setAttribute("username", t_kisi.getKullaniciAd());
            
            session.setAttribute("kisi", kisi);
            
            return "menu.xhtml?faces-redirect=true";
        } else {
            JSFUtil.hataMesajiEkle("Hatalı Giriş", "Kullanıcı adı yada şifre yanlış");
            return "giris.xhtml?faces-redirect=true";
        }
    }

    public TahsilatKisi getKisi() {
        return kisi;
    }

    public void setKisi(TahsilatKisi kisi) {
        this.kisi = kisi;
    }
}
