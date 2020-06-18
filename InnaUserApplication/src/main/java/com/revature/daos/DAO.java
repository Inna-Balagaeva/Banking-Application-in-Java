package com.revature.daos;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, I extends Serializable> {
	
	// to create abstract methods that's shared across classes
	// a class can implement many interfaces but
	// can extends only one class
List<T> findAll();	
T findById(I id);
int create(T obj);
int update(T obj);
// optional to create a T delete(T obj)

/*adding default methods so that we can instantiate
 * DAO concrete classes with a REFERENCE to the DAO interface
 * and override these methods;
 */



}
