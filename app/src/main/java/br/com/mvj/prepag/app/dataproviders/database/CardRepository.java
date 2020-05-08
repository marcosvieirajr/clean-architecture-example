package br.com.mvj.prepag.app.dataproviders.database;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mvj.prepag.app.dataproviders.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

}
