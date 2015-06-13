/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilat;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatDetay;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatMuhasebe;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatBorcFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKisiFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKurumFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.ThsTahsilatFacade;

/**
 *
 * @author Administrator
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class TahsilatBorcService {

    @EJB
    private TahsilatKurumFacade tahsilatKurumFacade;

    @EJB
    private TahsilatBorcFacade tahsilatBorcFacade;

    @EJB
    private ThsTahsilatFacade tahsilatFacade;

    @EJB
    private TahsilatKisiFacade kisiFacade;

    @Resource
    private UserTransaction userTransaction;

    public List<String> kurumAdlariGetir(String p_sorgu) {
        List<TahsilatKurum> kurumListesi = tahsilatKurumFacade.findAll();

        List<String> kurumAdlari = new ArrayList<>();

        for (TahsilatKurum kurum : kurumListesi) {
            if (kurum.getAd().toUpperCase().contains(p_sorgu.toUpperCase())) {
                kurumAdlari.add(kurum.getAd());
            }
        }

        return kurumAdlari;
    }

    public List<TahsilatBorc> borclariGetir(String p_kurumAd, String p_aboneNo) {

        Integer kurumId = tahsilatKurumFacade.kurumIdBul(p_kurumAd);
        return tahsilatBorcFacade.borclariGetir(kurumId, p_aboneNo);
    }

    public void seciliFaturalariOde(List<TahsilatBorc> p_seciliBorclar, TahsilatKisi p_kisi) {
        ThsTahsilat tahsilat;
        ThsTahsilatDetay tahsilatDetay;
        ThsTahsilatMuhasebe tahsilatMuhasebe1;
        ThsTahsilatMuhasebe tahsilatMuhasebe2;

        try {
            for (TahsilatBorc borc : p_seciliBorclar) {
                
                tahsilat = new ThsTahsilat();
                tahsilat.setIslemTrh(new java.util.Date());
                tahsilat.setKisi(p_kisi);
                tahsilat.setKisiSiraNo(p_kisi.getSiraNo());
                tahsilat.setKurum(borc.getKurum());
                tahsilat.setTutar(borc.getFaturaTutar());
                
                tahsilatDetay = new ThsTahsilatDetay();
                tahsilatDetay.setAboneNo(borc.getAboneNo());
                tahsilatDetay.setFaturaNo(borc.getFaturaNo());
                tahsilatDetay.setFaturaSonOdemeTrh(borc.getFaturaSonOdemeTrh());
                tahsilatDetay.setTahsilat(tahsilat);
                tahsilatDetay.setTutar(borc.getFaturaTutar());
                
                tahsilatMuhasebe1 = new ThsTahsilatMuhasebe();
                tahsilatMuhasebe1.setHesapNo("10.10.10.131");
                tahsilatMuhasebe1.setTahsilat(tahsilat);
                tahsilatMuhasebe1.setTutar(borc.getFaturaTutar());
                
                tahsilatMuhasebe2 = new ThsTahsilatMuhasebe();
                tahsilatMuhasebe2.setHesapNo("KASA");
                tahsilatMuhasebe2.setTahsilat(tahsilat);
                tahsilatMuhasebe2.setTutar(borc.getFaturaTutar());
                
                List<ThsTahsilatDetay> detayList = new ArrayList();
                detayList.add(tahsilatDetay);
                
                List<ThsTahsilatMuhasebe> muhasebeList = new ArrayList();
                muhasebeList.add(tahsilatMuhasebe1);
                muhasebeList.add(tahsilatMuhasebe2);
                
                tahsilat.setThsTahsilatDetayList(detayList);
                tahsilat.setThsTahsilatMuhasebeList(muhasebeList);
                
                userTransaction.begin();
                tahsilatFacade.create(tahsilat);
                
                System.out.println("Tahsilat Id: " + tahsilat.getId());
                
                p_kisi.setSiraNo(p_kisi.getSiraNo() + 1);
                kisiFacade.edit(p_kisi);
                
                borc.setFaturaDurum(1);
                tahsilatBorcFacade.edit(borc);
                
                userTransaction.commit();
                
            }
        } catch (Exception notSupportedException) {
            try {
                userTransaction.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
