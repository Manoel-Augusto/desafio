package com.sigapregao.desafio.DTO;

import java.io.Serializable;
import java.time.Instant;

import com.sigapregao.desafio.entities.ElectronicTrading;

public class ElectronicTradingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tradeNumber;
	private String organ;
	private Instant instant;
	private String object;

	public ElectronicTradingDTO() {
	}

	public ElectronicTradingDTO(Long id, String tradeNumber, String organ, Instant instant, String object,
			String proposalSubmission, String openingProposal) {
		this.id = id;
		this.tradeNumber = tradeNumber;
		this.organ = organ;
		this.instant = instant;
		this.object = object;
	}

	public ElectronicTradingDTO(ElectronicTrading entity) {
		this.id = entity.getId();
		this.tradeNumber = entity.getTradeNumber();
		this.organ = entity.getOrgan();
		this.instant = entity.getInstant();
		this.object = entity.getObject();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

}