package pl.mariusz.db;

import org.springframework.data.repository.CrudRepository;

public interface ZadanieDB extends CrudRepository<Zadanie, Long> {
}
