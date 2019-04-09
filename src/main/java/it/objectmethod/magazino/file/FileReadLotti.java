package it.objectmethod.magazino.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
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
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.objectmethod.magazino.entity.Lotto;

@Component
public class FileReadLotti {

	public List<Lotto> letturaLotti(InputStream f) throws Exception {

		BufferedInputStream inputStream = new BufferedInputStream(f);
		Reader reader =  new BufferedReader(new InputStreamReader(inputStream));
		List <Lotto>lottoList = leggiTuttiLotti(reader);
		reader.close();
		inputStream.close();
		return lottoList;
	}

	private List<Lotto> leggiTuttiLotti(Reader reader) throws IOException {

		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(';')
			    .withIgnoreQuotations(true)
			    .build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
				    .withCSVParser(parser)
				    .build();
			 
		List<String[]> lista = new ArrayList<>();
		List<Lotto> lottoList = new ArrayList<>();
		lista = csvReader.readAll();
		for(String arrayRead[]: lista) {
			
			Lotto lotto = new Lotto();
			lotto.setId(Long.parseLong(arrayRead[0]));
			lotto.setCodice(arrayRead[1]);
			lotto.setQuantita(Integer.parseInt(arrayRead[2]));
			lotto.setIdArticolo(Long.parseLong(arrayRead[3]));
			lottoList.add(lotto);	
			System.out.println(arrayRead[0]);
			
			
		}

		

		reader.close();
		csvReader.close();
		return lottoList;
	}
}
