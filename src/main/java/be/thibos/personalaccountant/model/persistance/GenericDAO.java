package be.thibos.personalaccountant.model.persistance;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

	Optional<T> get(long id);

	List<T> getAll();

	void save(T entity);

	void update(T t);

	void delete(T t);

}
