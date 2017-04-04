/**
 * @author Abdelali Boussadi
 */
package dAO;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 
 * 
 * //A generic DAO //This architecture is based on the
 * "Programming to interfaces" design pattern
 * 
 * 
 */
public interface GenericDao<T, Id extends Serializable> {

	public void persist(T entity);

	public void update(T entity);

	public T findById(Id id);

	public void delete(T entity);

	public List<T> findAll();

	public void deleteAll();

}
