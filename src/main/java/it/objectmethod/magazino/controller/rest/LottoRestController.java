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
import it.objectmethod.magazino.file.FileReadLotti;
import it.objectmethod.magazino.repo.LottoRepo;

@RestController
@RequestMapping("api/lotto")
public class LottoRestController {

	@Autowired
	private LottoRepo lottoRepo;
	@Autowired
	private FileReadLotti fileRead;
	
	@PostMapping("/lotto-import")
	public List<Lotto> importaLotti(@RequestParam("file") MultipartFile file) throws Exception{
		
		InputStream is = file.getInputStream();
		List<Lotto> lottoList = fileRead.letturaLotti(is);
		return lottoRepo.saveAll(lottoList);
	}
}
