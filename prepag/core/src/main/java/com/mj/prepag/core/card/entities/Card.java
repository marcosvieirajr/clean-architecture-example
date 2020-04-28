package com.mj.prepag.core.card.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import com.mj.prepag.core.card.usecase.EncodePasswordPort;

import lombok.Builder;
import lombok.Getter;

@Getter()
public class Card {
	
	private UUID uuid;
	private String nome;
	private BigDecimal saldo;
	private String numero;
	private String cvv;
	private LocalDate validade;
	private String senhaPlana;
	private String senhaCodificada;
	
	@Builder()
	private Card(String nome, BigDecimal saldo, EncodePasswordPort passwordEncoder) {
		super();
		this.nome = nome;
		this.saldo = saldo;
		gerarCard(passwordEncoder);
	}
	
	private static CardBuilder builder() {
        return new CardBuilder();
    }
	
	public static CardBuilder builder(String nome, BigDecimal saldo, EncodePasswordPort passwordEncoder) {
        return builder().nome(nome).saldo(saldo).passwordEncoder(passwordEncoder);
    }
	
	
	private void gerarCard(EncodePasswordPort passwordEncoder) {
		gerarUUID();
		gerarValidade();
		gerarNumero();
		gerarCvv();
		gerarSenha(passwordEncoder);
	}
	
	private void gerarUUID() {
		this.uuid = UUID.randomUUID();
	}
	
	private void gerarValidade() {
		this.validade = LocalDate.now().plusYears(2);
	}

	private void gerarNumero() {
		
		final var BIM = "123456";
		final var totalDeDigitos = 16;

		var random = new Random(System.currentTimeMillis());
		
		int randomNumberLength = totalDeDigitos - (BIM.length() + 1);

		StringBuilder builder = new StringBuilder(BIM);
		for (int i = 0; i < randomNumberLength; i++) {
			int digit = random.nextInt(10);
			builder.append(digit);
		}

		int checkDigit = getDigitoVerificador(builder.toString());
		builder.append(checkDigit);

		this.numero =  builder.toString();
	}
	
	private void gerarCvv() {
		
		var soma = validade.getYear() * validade.getMonthValue() * validade.getDayOfMonth();
		
		for (char digito : numero.substring(6).toCharArray()) {
			if(digito == '0') continue;
			soma *= Integer.parseInt(digito +"");
			soma /= 2;
		}
		
		var somaStr = soma +"";
		
		var digito1 = somaStr.substring(0, 1);
		var digito2 = somaStr.substring(somaStr.length() - 1);
		var digito3 = somaStr.substring(somaStr.length() / 2, somaStr.length() / 2 + 1);
		this.cvv =  digito1 + digito2 + digito3;
	}
	
	private void gerarSenha(EncodePasswordPort passwordEncoder) {
		
		var random = new Random(System.currentTimeMillis());
		StringBuilder senha = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int digit = random.nextInt(10);
			senha.append(digit);
		}
		this.senhaPlana = senha.toString();
		this.senhaCodificada = passwordEncoder.encode(this.senhaPlana);
	}

	private int getDigitoVerificador(String number) {

		int sum = 0;
		for (int i = 0; i < number.length(); i++) {

			// Get the digit at the current position.
			int digit = Integer.parseInt(number.substring(i, (i + 1)));

			if ((i % 2) == 0) {
				digit = digit * 2;
				if (digit > 9) {
					digit = (digit / 10) + (digit % 10);
				}
			}
			sum += digit;
		}

		int mod = sum % 10;
		return ((mod == 0) ? 0 : 10 - mod);
	}



}
