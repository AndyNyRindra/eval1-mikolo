package com.eval1.models.benef;

import com.pdfutils.PDFColumn;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.lang.Long;
import java.sql.Date;

import static com.eval1.util.DateUtil.getMonth;


@Getter
@Setter
@Entity
@Table(name = "v_benefice_month")
public class VBeneficeMonth extends HasId {

	private Double montant;
	private Long id;
	private Date mois;

	@PDFColumn(value = "Benefices", width = 30, order=1)
	public Double getMontant() {
		return montant;
	}

	@PDFColumn(value = "Mois", width = 30, order=0)
	public Date getMois() {
		return mois;
	}

	public String getMonthToStr() {
		return getMonth(getMois());
	}
}