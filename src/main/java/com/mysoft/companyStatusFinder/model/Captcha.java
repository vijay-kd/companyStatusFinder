package com.mysoft.companyStatusFinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Captcha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String base64;
	private boolean solved;
	private String solution;
	private int count;
}
