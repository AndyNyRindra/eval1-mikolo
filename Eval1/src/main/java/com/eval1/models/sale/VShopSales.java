package com.eval1.models.sale;

import com.eval1.models.shop.Shop;
import com.pdfutils.PDFColumn;
import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.Integer;
import java.lang.Double;
import java.lang.Long;
import java.sql.Date;

import static com.eval1.util.DateUtil.getMonth;


@Getter
@Setter
@Entity
@Table(name = "v_shop_sales")
public class VShopSales extends HasId {

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;
	private Double recettes;
	private Long nombreVentes;
	private Date mois;

	@Transient
	private Double comissions;

	@Transient
	private Double recettesFinal;

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

	@PDFColumn(value = "Point de vente", width = 20, order=0)
	public String getShopName() {
		return getShop().getName();
	}

	public String getMonthToStr() {
		return getMonth(getMois());
	}

	public void setRecettesFinal() {
		this.recettesFinal = getRecettes() - getComissions();
	}

}