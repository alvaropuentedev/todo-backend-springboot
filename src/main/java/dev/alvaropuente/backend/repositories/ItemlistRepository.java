package dev.alvaropuente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.alvaropuente.backend.models.ItemList;

public interface ItemlistRepository extends JpaRepository<ItemList, Long> {
	ItemList findByUserId(Long user_id);
	
	@Modifying
	@Query("DELETE FROM ItemList WHERE id = :list_id")
	void deleteItemListById(Long list_id);
}
