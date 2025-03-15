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
	/**
	 * Get all items from a list
	 * @param list_id from where get the items
	 * @return item list order by item_id
	 */
	List<Item> findByListIdOrderByIdAsc(Long list_id);

	/**
	 * Finds an item by its ID within a specific list.(for update item description)
	 * @param list_id The ID of the list to which the item belongs.
	 * @param item_id item_id The ID of the item to retrieve.
	 * @return An {@link Optional} containing the item if found, or {@link Optional#empty()} if not.
	 */
	Optional<Item> findByListIdAndId(Long list_id, Long item_id);

	/**
	 * Delete item by ID
	 * @param item_id to delete
	 */
	@Modifying
	@Query("DELETE FROM Item WHERE id = :item_id")
	void deleteItemById(Long item_id);
}
