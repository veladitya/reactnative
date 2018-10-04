package com.dhs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dhs.api.MasterDataApi;
import com.dhs.services.MovieService;

@SpringBootApplication
@EnableConfigurationProperties(value = { MasterDataApi.class })
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private MasterDataApi masterData;

	@Autowired
	private MovieService masterService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(masterData.getMovies());

		masterData.getPersons().forEach(p -> {
			masterService.insertOrUpdate(p);
		});

		masterData.getRoles().forEach(r -> {
			masterService.insertOrUpdate(r);
		});

		masterData.getMovies().forEach(movieApi -> {
			masterService.insertOrUpdate(movieApi);
		});
		if (masterData.getMovieRatings() != null) {
			masterData.getMovieRatings().forEach(movieRatings -> {
				masterService.addUserRatings(movieRatings);
			});
		}

		/*
		 * Role heroRole = new Role(); heroRole.setName("Hero");
		 * roleRepo.save(heroRole); Role villan = new Role(); villan.setName("Villan");
		 * roleRepo.save(villan);
		 * 
		 * Person p1 =new Person(); p1.setName("Chiru");
		 * 
		 * Person p2 =new Person(); p1.setName("Chiru1");
		 * 
		 * Person p3 =new Person(); p1.setName("Chiru2");
		 * 
		 * Movie m1 = new Movie(); m1.setTitle("Matrix"); m1.setImageUrl("xyz.jpg");
		 * List<PersonRole> pList = new ArrayList<>(); PersonRole pr1 = new
		 * PersonRole(); pr1.setPerson(p1); pr1.setRole(heroRole);
		 * 
		 * pList.add(pr1); m1.setPersonRole(pList);
		 */

	}
}
