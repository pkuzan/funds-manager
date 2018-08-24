package hellocloud.dao;

import hellocloud.model.AssetClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetClassCrudRepository extends CrudRepository<AssetClass, String> {
}
