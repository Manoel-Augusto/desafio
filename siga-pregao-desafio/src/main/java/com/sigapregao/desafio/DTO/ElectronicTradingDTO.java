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
	private String info;

	public ElectronicTradingDTO() {
	}

	public ElectronicTradingDTO(Long id, String tradeNumber, String organ, Instant instant, String info,
			String proposalSubmission, String openingProposal) {
		this.id = id;
		this.tradeNumber = tradeNumber;
		this.organ = organ;
		this.instant = instant;
		this.info = info;
	}

	public ElectronicTradingDTO(ElectronicTrading entity) {
		this.id = entity.getId();
		this.tradeNumber = entity.getTradeNumber();
		this.organ = entity.getOrgan();
		this.instant = entity.getInstant();
		this.info = entity.getInfo();

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}