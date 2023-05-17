package com.eval1.models.shop;

import com.eval1.models.Role;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Integer;
import java.lang.String;


@Getter
@Setter
@Entity
@Table(name = "shop")
public class Shop extends HasId {


	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	private String name;


	public Shop(Integer roleId) {
		Role role1 = new Role();
		role1.setId(Long.valueOf(roleId));
		setRole(role1);

	}

	public Shop() {

	}
}