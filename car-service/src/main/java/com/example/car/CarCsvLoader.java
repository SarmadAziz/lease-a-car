package com.example.car;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarCsvLoader {

    private final CarRepository carRepository;

    private final ResourceLoader resourceLoader;

    public CarCsvLoader(CarRepository carRepository, ResourceLoader resourceLoader) {
        this.carRepository = carRepository;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void loadCsvData() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/testdata.csv");

        List<Car> cars = readCarsFromCsv(resource);
        carRepository.saveAll(cars);
    }

    private List<Car> readCarsFromCsv(Resource resource) throws IOException {
        List<Car> cars = new ArrayList<>();

        try (Reader reader = new InputStreamReader(resource.getInputStream());
                CSVParser csvParser = CSVFormat.Builder.create().setDelimiter(';').setHeader().build().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Car car = Car.builder()
                        .make(csvRecord.get("make"))
                        .model(csvRecord.get("model"))
                        .version(csvRecord.get("version"))
                        .numberOfDoors(Integer.parseInt(csvRecord.get("#doors")))
                        .grossPrice(BigDecimal.valueOf(Double.parseDouble(csvRecord.get("gross_price").replace(",", "."))))
                        .nettPrice(BigDecimal.valueOf(Double.parseDouble(csvRecord.get("nett_price").replace(",", "."))))
                        .horsePower(Integer.parseInt(csvRecord.get("hp")))
                        .build();
                cars.add(car);
            }
        }
        return cars;
    }
}
