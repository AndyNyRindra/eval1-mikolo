package com.eval1.models;

import com.eval1.models.shop.Shop;
import com.fasterxml.jackson.annotation.JsonProperty;
import custom.springutils.LoginEntity;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.String;

import static com.eval1.util.RegexUtil.isEmailValid;


@Getter
@Setter
@Entity
@Table(name = "seller")
public class Seller extends HasName implements LoginEntity, LoggedUser {

	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;

	public void setPassword(String password) throws CustomException {
		if (password == null || password.equals("")) throw new CustomException("Veuillez ajouter un mot de passe");
		this.password = DigestUtils.sha1Hex(password);
	}

	public void setEmail(String email) throws CustomException {
		if (email == null || email.equals("")) throw new CustomException("Veuillez ajouter un email");
		if (isEmailValid(email)) this.email = email;
	}

	public Boolean isSimpleSeller() {
		return getShop().getRole().getId() == 0;
	}

}