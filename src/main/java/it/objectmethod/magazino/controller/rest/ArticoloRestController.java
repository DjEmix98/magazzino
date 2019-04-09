package it.objectmethod.magazino.controller.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import it.objectmethod.magazino.repo.ArticoloRepo;
import it.objectmethod.magazino.utility.FileReadCSV;

@RestController
@RequestMapping("api/articolo")
public class ArticoloRestController {

	
	@Autowired
	private ArticoloRepo articoloRepo;
	
	@PostMapping("/articolo-import")
	public List<Articolo> importaArticoli(@RequestParam("file") MultipartFile file) throws Exception{
		
		InputStream is = file.getInputStream();
		FileReadCSV fileRead = new FileReadCSV();
		List<String[]> listString = fileRead.letturaFileCSV(is);
		List<Articolo> listArticoli = new ArrayList<>();
for(String arrayRead[]: listString) {
			
			Articolo articolo = new Articolo();
			articolo.setId(Long.parseLong(arrayRead[0]));
			articolo.setCodice(arrayRead[1]);
			articolo.setDescrizione(arrayRead[2]);
			listArticoli.add(articolo);
			
		}
		return articoloRepo.saveAll(listArticoli);
	}
	
	@GetMapping("/find-all")
	public List<Articolo> findAll(){
		
		return articoloRepo.findAll();
	}
}
