package com.To_Do.List.List.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.To_Do.List.List.Models.ListModel;

@Repository
public interface IList extends CrudRepository<ListModel,Integer>{

}
