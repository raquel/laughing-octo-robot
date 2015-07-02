package info.rlira.bm.services.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
public class GenericDAO<T, I extends Serializable> {

	@PersistenceContext(unitName = "MySQLDS")
	protected EntityManager entityManager;
	
	protected Class<T> entityClass;
 
	/**
	 * 
	 */
	public GenericDAO() {
		loadEntityClass();
	}

	/**
	 * 
	 * @param entityManager
	 */
	public GenericDAO(EntityManager entityManager) {
		loadEntityClass();
		this.entityManager = entityManager;
	}

	/**
	 * {@link GenericDAO#loadEntityClass}
	 *
	 *
	 */
	@SuppressWarnings("unchecked")
	private void loadEntityClass() {
		Type type = getClass().getGenericSuperclass();

		if (type instanceof ParameterizedType) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			Type genericType = ((Class<?>) type).getGenericSuperclass();
			entityClass = (Class<T>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
		}
	}

	// === EVENT SAVE

	/**
	 * {@link GenericDAO#save}
	 *
	 *
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	/**
	 * {@link GenericDAO#saveAndFlush}
	 *
	 *
	 * @param entity
	 * @return
	 */
	public T saveAndFlush(T entity) {
		save(entity);
		entityManager.flush();
		return entity;
	}

	/**
	 * {@link GenericDAO#save}
	 *
	 *
	 * @param entityList
	 */
	public void save(Collection<T> entityList) {
		for (T entity : entityList) {
			save(entity);
		}
	}

	/**
	 * {@link GenericDAO#saveAndFlush}
	 *
	 *
	 * @param entityList
	 */
	public void saveAndFlush(Collection<T> entityList) {
		save(entityList);
		entityManager.flush();
	}

	// === EVENT UPDATE

	/**
	 * {@link GenericDAO#update}
	 *
	 *
	 * @param entity
	 * @return
	 */
	public T update(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	/**
	 * {@link GenericDAO#updateAndFlush}
	 *
	 *
	 * @param entity
	 * @return
	 */
	public T updateAndFlush(T entity) {
		update(entity);
		entityManager.flush();
		return entity;
	}

	/**
	 * {@link GenericDAO#update}
	 *
	 *
	 * @param entityList
	 */
	public void update(Collection<T> entityList) {
		for (T entity : entityList) {
			update(entity);
		}
	}

	/**
	 * {@link GenericDAO#updateAndFlush}
	 *
	 *
	 * @param entityList
	 */
	public void updateAndFlush(Collection<T> entityList) {
		update(entityList);
		entityManager.flush();
	}

	// === EVENT DELETE

	/**
	 * {@link GenericDAO#delete}
	 *
	 *
	 * @param entity
	 */
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	/**
	 * {@link GenericDAO#deleteAndFlush}
	 *
	 *
	 * @param entity
	 */
	public void deleteAndFlush(T entity) {
		delete(entity);
		entityManager.flush();

	}

	/**
	 * {@link GenericDAO#delete}
	 *
	 *
	 * @param entityList
	 */
	public void delete(Collection<T> entityList) {
		for (T entity : entityList) {
			delete(entity);
		}
	}

	/**
	 * {@link GenericDAO#deleteAndFlush}
	 *
	 *
	 * @param entityList
	 */
	public void deleteAndFlush(Collection<T> entityList) {
		delete(entityList);
		entityManager.flush();
	}

	// === EVENT SEARCH

	/**
	 * {@link GenericDAO#getById}
	 *
	 *
	 * @param id
	 * @return T
	 */
	public T getById(I id) {
		return entityManager.find(entityClass, id);
	}
 
	/**
	 * {@link GenericDAO#findAll}
	 *
	 *
	 * @return  {@link List}&lt;T&gt;
	 */
	public List<T> findAll() {
		Query query = entityManager.createQuery("SELECT obj FROM " + entityClass.getSimpleName() + " obj");
		return query.getResultList();
	}
  
	/**
	 * {@link GenericDAO#findByNativeQuery}
	 *
	 *
	 * @param query
	 * @param params
	 * @return {@link List}&lt;T&gt;
	 */
	public List<T> findByNativeQuery(String query, Map<String, Object> params) {
		Query q = getEntityManager().createNativeQuery(query, entityClass); 

		for (String key : params.keySet()) {
			q.setParameter(key, params.get(key));
		}

		return q.getResultList();
	}

	/**
	 * {@link GenericDAO#getEntityManager}
	 * 
	 *
	 * @return {@link EntityManager}
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * {@link GenericDAO#setEntityManager}
	 *
	 *
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * {@link GenericDAO#findSingleByQuery}
	 *
	 *
	 * @param query
	 * @param parametros
	 * @return
	 */
	public T findSingleByQuery(String query, Map<String, Object> parametros) {
		Query q = getEntityManager().createQuery(query);

		if ((parametros != null) && !parametros.isEmpty()) {
			for (Map.Entry<String, Object> entry : parametros.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return (T)q.getSingleResult();
	}
	/**
	 * {@link GenericDAO#searchWithPaginationQueryJPQL}
	 *
	 *
	 * @param pagina
	 * @param qtdRegistrosPorPagina
	 * @param jpql
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> searchWithPaginationQueryJPQL(int pagina, int qtdRegistrosPorPagina, String jpql, Map<String, Object> parametros) throws Exception{
		try {
			TypedQuery<T> query = (TypedQuery<T>)  getEntityManager().createQuery(jpql);
			Iterator it = parametros.entrySet().iterator();
		    while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
		        query.setParameter(pairs.getKey().toString(), pairs.getValue());
		        it.remove();
		    }
			query.setMaxResults(qtdRegistrosPorPagina);
			query.setFirstResult(pagina * qtdRegistrosPorPagina);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
