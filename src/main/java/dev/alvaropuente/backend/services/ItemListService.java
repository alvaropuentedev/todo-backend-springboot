package dev.alvaropuente.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alvaropuente.backend.models.ItemList;
import dev.alvaropuente.backend.models.User;
import dev.alvaropuente.backend.repositories.ItemlistRepository;
import dev.alvaropuente.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ItemListService {
	
	@Autowired
	ItemlistRepository itemlistRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Show List
	 * @param user_id
	 * @return
	 */
	@Transactional
	public List<ItemList> getAllListsByUserId() {
		return itemlistRepository.findAll();
	}
	
	
	@Transactional
	public String createListforUser(Long user_id, ItemList list) {
		User user = userRepository.findById(user_id).orElseThrow(
				() -> new IllegalArgumentException("User not found"));
        list.getUser().add(user); // Agrega el usuario a la lista
        list.setCreationDate(LocalDateTime.now()); // Establece la fecha de creación de la lista
        ItemList createdList = itemlistRepository.save(list); // Guarda la lista y obtén el objeto creado
        user.getItemLists().add(createdList); // Agrega la lista al usuario
        userRepository.save(user); // Guarda el usuario actualizado
        return "List Created!!"; // Devuelve la lista creada
	}
	
	@Transactional
	public void deleteList(Long list_id) {
		itemlistRepository.deleteItemListById(list_id);
	}
}	
