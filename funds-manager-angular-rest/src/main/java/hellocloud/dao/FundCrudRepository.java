package hellocloud.dao;

import hellocloud.model.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundCrudRepository extends CrudRepository<Fund, String> {
}
