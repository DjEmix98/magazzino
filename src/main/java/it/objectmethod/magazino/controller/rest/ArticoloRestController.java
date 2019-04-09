package it.objectmethod.magazino.controller.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.objectmethod.magazino.entity.Articolo;
import it.objectmethod.magazino.file.FileReadArticoli;
import it.objectmethod.magazino.repo.ArticoloRepo;

@RestController
@RequestMapping("api/articolo")
public class ArticoloRestController {

	@Autowired
	private FileReadArticoli readArticoli;
	
	@Autowired
	private ArticoloRepo articoloRepo;
	
	@PostMapping("/articolo-import")
	List<Articolo> importaArticoli(@RequestParam("file") MultipartFile file) throws Exception{
		
		InputStream is = file.getInputStream();
		List<Articolo> listArticoli = readArticoli.letturaArticoli(is);
		return articoloRepo.saveAll(listArticoli);
	}
	
}
