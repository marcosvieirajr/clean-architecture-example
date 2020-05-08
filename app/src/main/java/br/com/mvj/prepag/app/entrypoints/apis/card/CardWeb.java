package br.com.mvj.prepag.app.entrypoints.apis.card;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import br.com.mvj.prepag.domain.card.entities.Card;
import lombok.Getter;

@Getter
public class CardWeb {
	
	private UUID uuid;
	private String nome;
	private BigDecimal saldo;
	private String numero;
	private String cvv;
	private LocalDate validade;
	private String senha;
	
	public CardWeb (Card card) {
		this.uuid = card.getUuid();
		this.nome = card.getNome();
		this.saldo = card.getSaldo();
		this.numero = card.getNumero();
		this.cvv = card.getCvv();
		this.validade = card.getValidade();
		this.senha = card.getSenhaPlana();
	}

}
