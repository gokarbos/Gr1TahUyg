/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatDetay;

/**
 *
 * @author Administrator
 */
@Stateless
public class ThsTahsilatDetayFacade extends AbstractFacade<ThsTahsilatDetay> {
    @PersistenceContext(unitName = "tr.gov.ptt_GR1TahsilatUyg_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThsTahsilatDetayFacade() {
        super(ThsTahsilatDetay.class);
    }
    
}
