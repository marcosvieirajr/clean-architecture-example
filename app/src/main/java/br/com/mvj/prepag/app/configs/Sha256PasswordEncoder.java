package br.com.mvj.prepag.app.configs;

import javax.inject.Named;

import org.springframework.util.DigestUtils;

import br.com.mvj.prepag.domain.card.usecase.PasswordEncoderPort;
import lombok.NoArgsConstructor;

@Named
@NoArgsConstructor
public class Sha256PasswordEncoder implements PasswordEncoderPort {
	@Override
	public String encode(final String str) {
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}
}