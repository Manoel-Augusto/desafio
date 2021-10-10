package com.sigapregao.desafio.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sigapregao.desafio.DTO.ElectronicTradingDTO;
import com.sigapregao.desafio.services.ElectronicTradingService;

@RestController
@RequestMapping(value = "/trades")
public class ElectronicTradingResource {
	@Autowired
	private ElectronicTradingService service;

	// private static JSONObject jObject = null;

	@GetMapping
	public ResponseEntity<Page<ElectronicTradingDTO>> findAll(Pageable pageable) {

		Page<ElectronicTradingDTO> list = service.findAllPaged(pageable);

		return ResponseEntity.ok().body(list);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<List<ElectronicTradingDTO>> insert(@RequestBody String search) throws Exception {
		// jObject = new JSONObject(search);
		// JSONObject geoObject = jObject.getJSONObject("geodata");
		//List<ElectronicTradingDTO> dto = new ArrayList<>();
		List<ElectronicTradingDTO> list = service.getSearch(search);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<ElectronicTradingDTO> insert(@RequestBody ElectronicTradingDTO dto) {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}