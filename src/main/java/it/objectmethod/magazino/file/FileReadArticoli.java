package it.objectmethod.magazino.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.objectmethod.magazino.entity.Articolo;
import it.objectmethod.magazino.entity.Lotto;

@Component
public class FileReadArticoli {

	public List<Articolo> letturaArticoli(InputStream f) throws Exception {
		BufferedInputStream inputStream = new BufferedInputStream(f);
		Reader reader =  new BufferedReader(new InputStreamReader(inputStream));
		List<Articolo> listArticoli = leggiTuttiArticoli(reader);
		reader.close();
		inputStream.close();
		return listArticoli;
	}
	private List<Articolo> leggiTuttiArticoli(Reader reader) throws IOException {


		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(';')
			    .withIgnoreQuotations(true)
			    .build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
				    .withCSVParser(parser)
				    .build();
		List<String[]> lista = new ArrayList<>();
		List<Articolo> articoloList = new ArrayList<>();
		lista = csvReader.readAll();
for(String arrayRead[]: lista) {
			
			Articolo articolo = new Articolo();
			articolo.setId(Long.parseLong(arrayRead[0]));
			articolo.setCodice(arrayRead[1]);
			articolo.setDescrizione(arrayRead[2]);
			articoloList.add(articolo);
			
		}
		reader.close();
		csvReader.close();
		return articoloList;
	}
}
