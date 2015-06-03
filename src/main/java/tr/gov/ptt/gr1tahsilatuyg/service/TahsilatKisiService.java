/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKisiFacade;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatKisiService {
    @EJB
    private TahsilatKisiFacade tahsilatKisiFacade;
    
    public TahsilatKisi giriseYetkilimi(TahsilatKisi p_kisi)
    {
        return tahsilatKisiFacade.giriseYetkilimi(p_kisi);
    }

}