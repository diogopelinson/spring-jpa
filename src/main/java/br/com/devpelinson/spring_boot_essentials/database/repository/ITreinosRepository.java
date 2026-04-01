package br.com.devpelinson.spring_boot_essentials.database.repository;

import br.com.devpelinson.spring_boot_essentials.database.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITreinosRepository extends JpaRepository<TreinosEntity, Integer> {

}
