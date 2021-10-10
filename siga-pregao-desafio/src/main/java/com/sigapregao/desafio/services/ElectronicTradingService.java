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
	private String url = "https://www.comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacao_Filtro.asp?numprp=&dt_publ_ini=01/09/2021&dt_publ_fim=15/09/2021&chkModalidade=5&chk_concor=&chk_pregao=1,2,3,4&chk_rdc=&optTpPesqMat=M&optTpPesqServ=S&chkTodos=&chk_concorTodos=&chk_pregaoTodos=-1&txtlstUf=to&txtlstMunicipio=&txtlstUasg=&txtlstGrpMaterial=&txtlstClasMaterial=&txtlstMaterial=&txtlstGrpServico=&txtlstServico=&txtObjeto=";
	@Autowired
	private ElectronicTradingRepository repository;

	@Transactional
	public List<ElectronicTradingDTO> getSearch(String search) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/manoe/Desktop/desafio/siga-pregao-desafio/src/main/java/driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(600, TimeUnit.MICROSECONDS);

		driver.get(url);

		driver.findElement(By.id("txtObjeto")).sendKeys(search);

		driver.findElement(By.id("ok")).click();

		String htm = driver.getPageSource();
		Document doc = Jsoup.parse(htm);

		List<Element> forms = doc.getElementsByTag("form");
		List<ElectronicTradingDTO> dto = new ArrayList<>();

		for (Element element : forms) {

			List<Element> b = element.getElementsByTag("b");

			Element tbody = element.getElementsByTag("tbody").first();
			List<Element> texto = tbody.getElementsByClass("tex3");

			ElectronicTrading list = new ElectronicTrading();

			list.setTradeNumber(b.get(1).text());
			list.setInstant(Instant.now());
			list.setOrgan(b.get(0).text());
			list.setInfo(texto.get(0).text());
			dto.add(new ElectronicTradingDTO(list));
			repository.save(list);

		}

		driver.quit();
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<ElectronicTradingDTO> findAllPaged(Pageable pageable) {
		Page<ElectronicTrading> list = repository
				.findAll(PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "instant")));

		return list.map(x -> new ElectronicTradingDTO(x));
	}
}