package com.eval1.models;

import custom.springutils.model.HasId;
import custom.springutils.model.HasName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends HasName {


}