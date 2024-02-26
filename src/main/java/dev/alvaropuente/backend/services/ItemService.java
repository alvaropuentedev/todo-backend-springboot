package dev.alvaropuente.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.alvaropuente.backend.models.Item;
import dev.alvaropuente.backend.repositories.ItemRepository;
import dev.alvaropuente.backend.repositories.ItemlistRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemlistRepository listsRepository;

	/**
	 * Get all items from the user with the same id
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Item> getAllItemsByListId(Long list_id) {
		return itemRepository.findByListId(list_id);
	}

	/**
	 * Create item
	 * @param userId
	 * @param todoItem
	 * @return
	 */
	@Transactional
	public Item createItem(Long list_id, Item item) {
		return listsRepository.findById(list_id).map(list -> {
			item.setLists(list);
			item.setCreationDate(LocalDateTime.now());
			return itemRepository.save(item);
		}).orElseThrow(()-> new IllegalArgumentException("List not found"));
	}

	/**
	 * Delete item by user and item ID
	 */
	@Transactional
	public void deleteItem(Long item_id) {
		itemRepository.deleteItemById(item_id);
	}
	
}
