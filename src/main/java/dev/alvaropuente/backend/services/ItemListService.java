package dev.alvaropuente.backend.services;

import dev.alvaropuente.backend.models.ItemList;
import dev.alvaropuente.backend.models.User;
import dev.alvaropuente.backend.repositories.ItemlistRepository;
import dev.alvaropuente.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemListService {
	
	private final ItemlistRepository itemlistRepository;
	
	private final UserRepository userRepository;

	public ItemListService(ItemlistRepository itemlistRepository, UserRepository userRepository) {
		this.itemlistRepository = itemlistRepository;
		this.userRepository = userRepository;
	}

	/**
	 * GET all Lists by user ID
	 * param user_id
	 * return
	 */
	@Transactional
	public List<ItemList> getAllListsByUserId(Long user_id) {
		return itemlistRepository.findByUserIdOrderByListNameAsc(user_id);
	}

	@Transactional
	public ItemList createListforUser(Long user_id, ItemList list) {
		User user = userRepository.findById(user_id).orElseThrow(
				() -> new IllegalArgumentException("User not found"));
        list.getUser().add(user);
        list.setCreationDate(LocalDateTime.now());
        ItemList createdList = itemlistRepository.save(list);
        user.getItemLists().add(createdList);
        userRepository.save(user);

		return createdList;
	}

	/**
	 * Add user to list
	 * @param listId
	 * @param username
	 */
	@Transactional
	public void addUsersToList(Long listId, String username) {
		ItemList itemList = itemlistRepository.findById(listId).orElseThrow(() -> new IllegalArgumentException("List not found"));
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("User not found: " + username));    // Asociar el usuario a la lista
		itemList.getUser().add(user);

		// Asociar la lista al usuario (si la relación es bidireccional)
		user.getItemLists().add(itemList);

		// Guardar cambios
		itemlistRepository.save(itemList);
		userRepository.save(user);
	}

	@Transactional
	public void deleteList(Long list_id) {
		itemlistRepository.deleteItemListById(list_id);
	}
}	
