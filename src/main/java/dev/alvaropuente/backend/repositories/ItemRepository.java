package dev.alvaropuente.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.alvaropuente.backend.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByListId(Long list_id);
	Optional<Item> findByListIdAndId(Long list_id, Long item_id);
	
	@Modifying
	@Query("DELETE FROM Item WHERE id = :item_id")
	void deleteItemById(Long item_id);
}
