package com.indra;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.indra.model.Users;
import com.indra.repository.UsersRepo;

@SpringBootTest
class EjemploBdApplicationTests {

	@Autowired
	private UsersRepo uR;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void crearUsuario(){
		Users u = new Users();
		u.setUSERNAME("Jesus");
		u.setPASSWORD(encoder.encode("Roberto"));
		u.setENABLED(3);
		Users retorno = uR.save(u);
		
		assertTrue(retorno.getPASSWORD().equalsIgnoreCase(u.getPASSWORD()));
		
	}

}
