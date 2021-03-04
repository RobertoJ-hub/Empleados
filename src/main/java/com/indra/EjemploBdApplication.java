package com.indra;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.indra.model.JobHistory;
import com.indra.model.JobHistoryEmbedded;
import com.indra.repository.EmployeesRepository2;
import com.indra.service.CountriesService;
import com.indra.service.DepartmentsService;
import com.indra.service.EmployeesService;
import com.indra.service.JobHistoryService;
import com.indra.service.JobsService;
import com.indra.service.LocationsService;
import com.indra.service.RegionsService;

@SpringBootApplication
public class EjemploBdApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EjemploBdApplication.class, args);
	}
	
	@Autowired
	private EmployeesService e;

	@Override
	public void run(String... args) throws Exception {
		e.findByFIRSTNAMELikeAndSALARYBetween("J%",0.0,2400.00);
	}
		
}
