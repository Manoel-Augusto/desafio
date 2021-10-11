package com.sigapregao.desafio.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigapregao.desafio.DTO.ElectronicTradingDTO;
import com.sigapregao.desafio.entities.ElectronicTrading;
import com.sigapregao.desafio.repositories.ElectronicTradingRepository;

@Service
public class ElectronicTradingService {
	private String url = "https://www.comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacao_Filtro.asp?numprp=&dt_publ_ini=01/10/2021&dt_publ_fim=15/10/2021&chkModalidade=5&chk_concor=&chk_pregao=1,2,3,4&chk_rdc=&optTpPesqMat=M&optTpPesqServ=S&chkTodos=&chk_concorTodos=&chk_pregaoTodos=-1&txtlstUf=&txtlstMunicipio=&txtlstUasg=&txtlstGrpMaterial=&txtlstClasMaterial=&txtlstMaterial=&txtlstGrpServico=&txtlstServico=&txtObjeto=";

	@Autowired
	private ElectronicTradingRepository repository;

	// Metodo para realizar scraping
	@Transactional
	public List<ElectronicTradingDTO> getSearch(String search) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/manoe/Desktop/desafio/siga-pregao-desafio/src/main/java/driver/chromedriver.exe");
		// Instância e configuração do WebDriver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);

		// Scraping
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.MICROSECONDS);
		driver.get(url);
		driver.findElement(By.id("txtObjeto")).sendKeys(search);
		driver.findElement(By.id("ok")).click();
		String stringHtml = driver.getPageSource();
		driver.quit();

		Document doc = Jsoup.parse(stringHtml);

		List<Element> forms = doc.getElementsByTag("form");
		List<ElectronicTradingDTO> dto = new ArrayList<>();

		// Foreach para percorrer os elementos da lista de (Forms) extraindo os textos
		for (Element element : forms) {

			List<Element> b = element.getElementsByTag("b");
			Element tbody = element.getElementsByTag("tbody").first();
			List<Element> texto = tbody.getElementsByClass("tex3");
			ElectronicTrading electronicTrading = new ElectronicTrading();

			// Seta os atributos no objeto retorna uma lista para o Resource e salva os
			// dados no banco
			electronicTrading.setTradeNumber(b.get(1).text());
			electronicTrading.setInstant(Instant.now());
			electronicTrading.setOrgan(b.get(0).text());
			electronicTrading.setInfo(texto.get(0).text());
			dto.add(new ElectronicTradingDTO(electronicTrading));
			repository.save(electronicTrading);

		}

		return dto;
	}

	/*
	 * Metodo para recuperar os dados dos pregões salvo no banco retorna de forma
	 * ordenada do mais rescente para o mais antigo baseado no atributo instant que é o horário no
	 * qual foi persistido no banco.
	 */
	@Transactional(readOnly = true)
	public Page<ElectronicTradingDTO> findAllPaged(Pageable pageable) {
		Page<ElectronicTrading> electronicTrading = repository
				.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "instant")));
		return electronicTrading.map(x -> new ElectronicTradingDTO(x));
	}
}