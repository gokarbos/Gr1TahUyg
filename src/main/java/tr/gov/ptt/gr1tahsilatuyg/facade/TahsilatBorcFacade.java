/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatBorcFacade extends AbstractFacade<TahsilatBorc> {

    @PersistenceContext(unitName = "tr.gov.ptt_GR1TahsilatUyg_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TahsilatBorcFacade() {
        super(TahsilatBorc.class);
    }

    public List<TahsilatBorc> borclariGetir(Integer p_kurumId, String p_aboneNo) {
       List<TahsilatBorc> borcListesi = em.createNamedQuery("TahsilatBorc.borclariGetirKurumIdAboneNo").setParameter("aboneNo", p_aboneNo).setParameter("kurumId", p_kurumId).getResultList();
       return borcListesi;
    }
}
