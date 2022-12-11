package com.mysoft.companyStatusFinder.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mysoft.companyStatusFinder.model.Captcha;
import com.mysoft.companyStatusFinder.repository.CaptchaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CaptchaService {

	private final CaptchaRepository repository;
	
	@Transactional
	public Captcha save(Captcha captcha) {
		
		try {
			Captcha duplicateCaptcha = repository.findByBase64(captcha.getBase64());
			
			if(null != duplicateCaptcha) {
				int count = duplicateCaptcha.getCount();
				
				duplicateCaptcha.setCount(count + 1);
				captcha = duplicateCaptcha;
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		
		return repository.save(captcha);
	}
}
