package br.com.mvj.prepag.app.dataproviders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConstructorBinding;

import br.com.mvj.prepag.domain.card.entities.Card;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"uuid"})
@ToString
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class CardEntity {
	
//	public CardEntity(Card created) {
//		
//	}
	
	@Id
	private UUID uuid;
	public CardEntity(Card created) {
		super();
		this.uuid = created.getUuid();
		this.nome = created.getNome();
		this.saldo = created.getSaldo();
		this.numero = created.getNumero();
		this.validade = created.getValidade();
		this.senha = created.getSenhaCodificada();
	}
	
	@NotBlank
	private String nome;
	
	@NotNull
	@DecimalMin(value = "1.0")
	private BigDecimal saldo;
	private String numero;
	private LocalDate validade;
	private String senha;
}
