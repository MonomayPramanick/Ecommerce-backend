package com.backend;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.config.AppConstraints;
import com.backend.model.Role;
import com.backend.repositories.RoleRepo;



@SpringBootApplication
public class EcommerceBackendApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo rolerepo;
	
	
	@Override
	public void run(String...args) throws Exception {
		
		
		try {
			
			Role role1=new Role();
			
			role1.setName("NORMAL_USER");
			
			Role role=new Role();
			
			role.setName("ADMIN_USER");
			
			List<Role>roles=List.of(role,role1);
			List<Role>result=this.rolerepo.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


