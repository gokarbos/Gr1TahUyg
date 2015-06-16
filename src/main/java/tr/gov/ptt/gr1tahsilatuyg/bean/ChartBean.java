/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */

@ManagedBean(name = "chart")
@RequestScoped
public class ChartBean {
    
    private PieChartModel pieChartModel;
    @EJB
    private TahsilatBorcService tahsilatBorcService;
    private List<Object[]> chartListe;

    public ChartBean() {
        pieChartModel = new PieChartModel();
        pieChartModel.setLegendPosition("ne");
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setTitle("Ne biçim chart");
        pieChartModel.setDataFormat("value");
        
        chartListe = new ArrayList<Object[]>();
        
    }
    
    @PostConstruct
    public void doldurChart()
    {
        chartListe = tahsilatBorcService.chartVerisiGetir();
        
        for (Object[] chartElement : chartListe) {
            pieChartModel.set(String.valueOf(chartElement[0]), Double.valueOf(chartElement[1].toString()));
        }
        
        /*pieChartModel.set("ASKİ", 1400);
        pieChartModel.set("PTT", 200);
        pieChartModel.set("TELEKOM", 1000);*/
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }
    
    
}
