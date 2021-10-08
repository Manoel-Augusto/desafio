package com.sigapregao.desafio.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigapregao.desafio.entities.ElectronicTrading;

@Repository
public interface ElectronicTradingRepository extends JpaRepository<ElectronicTrading, Long> {

}
