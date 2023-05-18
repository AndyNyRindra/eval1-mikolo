package com.eval1.models;

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
@Table(name = "v_benefice_month")
public class VBeneficeMonth extends HasId {

	private Double montant;
	private Long id;
	private Date mois;

}