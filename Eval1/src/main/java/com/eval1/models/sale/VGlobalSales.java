package com.eval1.models.sale;

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


@Getter
@Setter
@Entity
@Table(name = "v_global_sales")
public class VGlobalSales extends HasId {

	private Double recettes;
	private Long nombreVentes;
	private Date mois;

	@PDFColumn(value = "Recettes", width = 30, order=2)
	public Double getRecettes() {
		return recettes;
	}

	@PDFColumn(value = "Nombre de ventes", width = 30, order=1)
	public Long getNombreVentes() {
		return nombreVentes;
	}


	@PDFColumn(value = "Mois", width = 20, order=0)
	public Date getMois() {
		return mois;
	}
}