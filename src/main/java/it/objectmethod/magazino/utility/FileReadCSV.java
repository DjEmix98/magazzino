package it.objectmethod.magazino.utility;

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
public class FileReadCSV {

	public List<String[]> letturaFileCSV(InputStream f) throws Exception {
		BufferedInputStream inputStream = new BufferedInputStream(f);
		Reader reader =  new BufferedReader(new InputStreamReader(inputStream));
		List<String[]> listArticoli = leggiTutto(reader);
		reader.close();
		inputStream.close();
		return listArticoli;
	}
	private List<String[]> leggiTutto(Reader reader) throws IOException {


		CSVParser parser = new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true)
				.build();

		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withCSVParser(parser)
				.build();
		List<String[]> lista = new ArrayList<>();

		lista = csvReader.readAll();

		reader.close();
		csvReader.close();
		return lista;
	}
}
