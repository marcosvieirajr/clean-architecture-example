package com.mj.prepag.app.configs;

import javax.inject.Named;

import org.springframework.util.DigestUtils;

import com.mj.prepag.core.card.usecase.EncodePasswordPort;

import lombok.NoArgsConstructor;

@Named
@NoArgsConstructor
public class Sha256PasswordEncoder implements EncodePasswordPort {
	@Override
	public String encode(final String str) {
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}
}