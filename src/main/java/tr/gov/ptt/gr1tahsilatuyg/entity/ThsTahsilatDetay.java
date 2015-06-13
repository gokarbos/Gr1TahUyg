/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_TAHSILAT_DETAY")
@NamedQueries({
    @NamedQuery(name = "ThsTahsilatDetay.findAll", query = "SELECT t FROM ThsTahsilatDetay t"),
    @NamedQuery(name = "ThsTahsilatDetay.findById", query = "SELECT t FROM ThsTahsilatDetay t WHERE t.id = :id"),
    @NamedQuery(name = "ThsTahsilatDetay.findByTutar", query = "SELECT t FROM ThsTahsilatDetay t WHERE t.tutar = :tutar"),
    @NamedQuery(name = "ThsTahsilatDetay.findByAboneNo", query = "SELECT t FROM ThsTahsilatDetay t WHERE t.aboneNo = :aboneNo"),
    @NamedQuery(name = "ThsTahsilatDetay.findByFaturaNo", query = "SELECT t FROM ThsTahsilatDetay t WHERE t.faturaNo = :faturaNo"),
    @NamedQuery(name = "ThsTahsilatDetay.findByFaturaSonOdemeTrh", query = "SELECT t FROM ThsTahsilatDetay t WHERE t.faturaSonOdemeTrh = :faturaSonOdemeTrh")})
public class ThsTahsilatDetay implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name="tahdetseq",sequenceName = "SEQ_THS_TAHSILAT_DETAY",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tahdetseq", strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    @Column(name = "TUTAR")
    private BigDecimal tutar;
    @Size(max = 100)
    @Column(name = "ABONE_NO")
    private String aboneNo;
    @Size(max = 100)
    @Column(name = "FATURA_NO")
    private String faturaNo;
    @Column(name = "FATURA_SON_ODEME_TRH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date faturaSonOdemeTrh;
    @JoinColumn(name = "TAHSILAT_ID", referencedColumnName = "ID")
    @ManyToOne
    private ThsTahsilat tahsilat;

    public ThsTahsilatDetay() {
    }

    public ThsTahsilatDetay(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTutar() {
        return tutar;
    }

    public void setTutar(BigDecimal tutar) {
        this.tutar = tutar;
    }

    public String getAboneNo() {
        return aboneNo;
    }

    public void setAboneNo(String aboneNo) {
        this.aboneNo = aboneNo;
    }

    public String getFaturaNo() {
        return faturaNo;
    }

    public void setFaturaNo(String faturaNo) {
        this.faturaNo = faturaNo;
    }

    public Date getFaturaSonOdemeTrh() {
        return faturaSonOdemeTrh;
    }

    public void setFaturaSonOdemeTrh(Date faturaSonOdemeTrh) {
        this.faturaSonOdemeTrh = faturaSonOdemeTrh;
    }

    public ThsTahsilat getTahsilat() {
        return tahsilat;
    }

    public void setTahsilat(ThsTahsilat tahsilat) {
        this.tahsilat = tahsilat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThsTahsilatDetay)) {
            return false;
        }
        ThsTahsilatDetay other = (ThsTahsilatDetay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatDetay[ id=" + id + " ]";
    }
    
}
