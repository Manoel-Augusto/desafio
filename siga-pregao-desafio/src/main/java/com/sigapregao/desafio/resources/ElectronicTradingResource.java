package com.sigapregao.desafio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigapregao.desafio.DTO.ElectronicTradingDTO;
import com.sigapregao.desafio.services.ElectronicTradingService;

@RestController
@RequestMapping(value = "/trades")
public class ElectronicTradingResource {
	@Autowired
	private ElectronicTradingService service;

	// Endpoint GET recebe a requisição envia ao service e retorna lista ordenada
	// por horário da consulta realizada
	@GetMapping
	public ResponseEntity<Page<ElectronicTradingDTO>> findAll(Pageable pageable) {

		Page<ElectronicTradingDTO> list = service.findAllPaged(pageable);

		return ResponseEntity.ok().body(list);
	}

	// Endpoint POST, chama o metodo do service passando o parametro recebido e após
	// retorno da solicitação manda como responta para o front um JSON com a lista
	// dos dados do objeto para o frontend
	@PostMapping(value = "/search")
	public ResponseEntity<List<ElectronicTradingDTO>> insert(@RequestBody String search) throws Exception {
		List<ElectronicTradingDTO> list = service.getSearch(search);
		return ResponseEntity.ok().body(list);
	}

}