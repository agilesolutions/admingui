package ch.agilesolutions.jdo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProfileRepository extends CrudRepository<Profile, Long> {

	List<Profile> findByName(@Param("name") String brand);

	List<Profile> findByEnvironment(@Param("environment") String color);



}
