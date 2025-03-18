package dev.alvaropuente.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.alvaropuente.backend.models.ItemList;

public interface ItemlistRepository extends JpaRepository<ItemList, Long> {
	List<ItemList> findByUserIdOrderByListNameAsc(Long userId);

	@Modifying
	@Query("DELETE FROM ItemList WHERE id = :list_id")
	void deleteItemListById(Long list_id);
}
