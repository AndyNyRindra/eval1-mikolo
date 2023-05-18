package com.eval1.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Integer;
import java.lang.Double;
import java.lang.Long;


@Getter
@Setter
@Entity
@Table(name = "v_stock")
public class VStock extends HasId {

	private Integer shopId;
	private Double quantity;
	private Long id;
	private Integer laptopId;

}