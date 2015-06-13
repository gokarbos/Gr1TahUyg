/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.ArrayList;
import java.util.List;
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
    private final List<String> temaListesi;

    public TahsilatKisiBean() {
        kisi = new TahsilatKisi();
        temaListesi = new ArrayList();
        temaListesi.add("bluesky");
        temaListesiDoldur();
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
    
    public String guvenliCikis()
    {
        HttpSession session = JSFUtil.getSession();
        session.invalidate();
        return "giris.xhtml?faces-redirect=true";
    }
    
    public void temaListesiDoldur()
    {
        temaListesi.add("ui-darkness");
        temaListesi.add("bluesky");
        temaListesi.add("trontastic");
        temaListesi.add("excite-bike");
        temaListesi.add("rocket");
    }
    
    public List<String> getTemaListesi()
    {
        return temaListesi;
    }
    
    public void temaKaydet()
    {
        kisiService.kisiGuncelle(kisi);
    }
}
