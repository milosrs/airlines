package htec.airlines.service;

import java.io.IOException;

import htec.airlines.dto.DataImportDto;

public interface DataImportService {
	boolean importDataToSystem(DataImportDto dataDto) throws IOException, Exception;
}
