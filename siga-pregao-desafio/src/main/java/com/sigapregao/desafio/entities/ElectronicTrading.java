package com.sigapregao.desafio.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_electronic_trading")
public class ElectronicTrading implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String organ;
	private Instant instant;
	private String tradeNumber;
	@Column(columnDefinition = "TEXT", name="obj")
	private String object;

	public ElectronicTrading() {
	}

	public ElectronicTrading(Long id, String organ, Instant instant, String tradeNumber, String object) {
		this.id = id;
		this.organ = organ;
		this.instant = instant;
		this.tradeNumber = tradeNumber;
		this.object = object;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectronicTrading other = (ElectronicTrading) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/*
	 * 
	 * CREATE TABLE IF NOT EXISTS public.tb_electronic_trading
	(
	    id id_seq smallint NOT NULL DEFAULT nextval('id_seq') NOT NULL,
	    organ text COLLATE pg_catalog."default" NOT NULL,
	    instant date NOT NULL,
	    trade_number text COLLATE pg_catalog."default",
	    obj text COLLATE pg_catalog."default" NOT NULL,
	    CONSTRAINT tb_electronic_trading_pkey PRIMARY KEY (id)
	)

	TABLESPACE pg_default;

	ALTER TABLE public.tb_electronic_trading
	    OWNER to postgres;
	 * 
	 * 
	 */

}


