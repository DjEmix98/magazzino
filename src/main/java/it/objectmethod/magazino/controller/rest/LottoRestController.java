package it.objectmethod.magazino.controller.rest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;

import it.objectmethod.magazino.entity.Lotto;
import it.objectmethod.magazino.repo.LottoRepo;
import it.objectmethod.magazino.utility.FileReadCSV;

@RestController
@RequestMapping("api/lotto")
public class LottoRestController {

	@Autowired
	private LottoRepo lottoRepo;
	
	@PostMapping("/lotto-import")
	public List<Lotto> importaLotti(@RequestParam("file") MultipartFile file) throws Exception{
		
		InputStream is = file.getInputStream();
		FileReadCSV fileRead = new FileReadCSV();
		List<String[]> lottoString= fileRead.letturaFileCSV(is);
		List<Lotto>lottoList = new ArrayList<>();
	for(String arrayRead[]: lottoString) {
			
			Lotto lotto = new Lotto();
			lotto.setId(Long.parseLong(arrayRead[0]));
			lotto.setCodice(arrayRead[1]);
			lotto.setQuantita(Integer.parseInt(arrayRead[2]));
			lotto.setIdArticolo(Long.parseLong(arrayRead[3]));
			lottoList.add(lotto);	
			System.out.println(arrayRead[0]);
			
			
		}
		return lottoRepo.saveAll(lottoList);
	}
}
