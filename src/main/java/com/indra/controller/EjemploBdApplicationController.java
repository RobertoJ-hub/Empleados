package com.indra.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.indra.model.Employees;
import com.indra.service.CountriesService;
import com.indra.service.DepartmentsService;
import com.indra.service.EmployeesService;
import com.indra.service.JobHistoryService;
import com.indra.service.JobsService;
import com.indra.service.LocationsService;
import com.indra.service.RegionsService;

@Controller
public class EjemploBdApplicationController {
	
	@Autowired
	private EmployeesService eS;
	@Autowired
	private CountriesService cS;
	@Autowired
	private DepartmentsService dS; 
	@Autowired
	private JobHistoryService jhS;
	@Autowired
	private JobsService jS;
	@Autowired
	private LocationsService lS;
	@Autowired
	private RegionsService rS;
	
	@GetMapping("/")
	public String mostarEmpleados(Model m) {
		m.addAttribute("datos", eS.findAll());
		return "mostrarEmpleados"; 
	}
	
	@GetMapping("/guardar") 
	public String agregarEmpleado(Model m) {
		m.addAttribute("datosJobs", jS.findAll());
		m.addAttribute("datosDepartments", dS.findAll());
		return "agregarEmpleado";
	}
	
	@PostMapping("/guardados")
	public String agregarE(Employees e, Model m) {
		m.addAttribute("a", eS.save(e.getFIRSTNAME(), e.getLASTNAME(), e.getEMAIL(), e.getPHONE_NUMBER(), e.getHIRE_DATE(), e.getJOB_ID(), e.getSALARY(), e.getMANAGER_ID(), e.getDEPARTMENT_ID()));
		m.addAttribute("datos", eS.findAll());
		return "redirect:/";
	}
	
	@GetMapping("/verMasEmpleado/{EMPLOYEE_ID}")
	public String verMasEmpleado(@PathVariable("EMPLOYEE_ID") Integer e,Model m) {
		m.addAttribute("empleado", eS.findById(e));
		return "verMasEmpleado";
	}
	
	@GetMapping("/editar/{EMPLOYEE_ID}")
	public String editarEmpleado(@PathVariable("EMPLOYEE_ID") Integer e,Model m) {
		m.addAttribute("empleado", eS.findById(e));
		m.addAttribute("datosJobs", jS.findAll());
		m.addAttribute("datosDepartments", dS.findAll());
		return "editarEmpleado";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(Employees e, Model m) {
		//e.setEMPLOYEE_ID(e.getEMPLOYEE_ID());
		eS.update(e.getDEPARTMENT_ID(), e.getFIRSTNAME(), e.getLASTNAME(), e.getEMAIL(), e.getPHONE_NUMBER(), e.getHIRE_DATE(), e.getJOB_ID(), e.getSALARY(), e.getMANAGER_ID(), e.getDEPARTMENT_ID());
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{EMPLOYEE_ID}")
	public String eliminarEmpleado(@PathVariable("EMPLOYEE_ID") Integer e,Model m) {
		m.addAttribute("empleado", eS.findById(e));
		m.addAttribute("datosJobs", jS.findAll());
		m.addAttribute("datosDepartments", dS.findAll());
		return "eliminarEmpleado";
	}
	
	@GetMapping("/confirmacionEliminacion/{EMPLOYEE_ID}")
	public String confirmarEliminacion(@PathVariable("EMPLOYEE_ID") Integer e, Model m) {
		eS.deletedById(e);
		return "redirect:/";
	}
	@InitBinder()
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
