package com.mysoft.companyStatusFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.companyStatusFinder.model.Captcha;

public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
	
	public Captcha findByBase64(String base64);

}
