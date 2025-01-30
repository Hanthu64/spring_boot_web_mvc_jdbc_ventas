package org.iesvdm;

import com.mysql.cj.xdevapi.Client;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ClienteDAOImpl;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.mapper.ClienteMapper;
import org.iesvdm.modelo.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
class SpringBootWebMvcJdbcVentasApplicationTests {

	@Autowired
	ClienteMapper mapper;
	@Test
	void mapperTest() {
	}
}
