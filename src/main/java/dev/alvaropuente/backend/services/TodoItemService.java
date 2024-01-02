package dev.alvaropuente.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.alvaropuente.backend.models.TodoItem;
import dev.alvaropuente.backend.repositories.TodoItemRepository;
import dev.alvaropuente.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoItemService {

	@Autowired
	private final TodoItemRepository todoItemRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Get all items from the user with the same id
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TodoItem> getAllItemsByUserId(Long userId) {
		return todoItemRepository.findByUserId(userId);
	}

	/**
	 * Create item
	 * 
	 * @param userId
	 * 
	 * @param todoItem
	 * 
	 * @return
	 */
	@Transactional
	public TodoItem createItem(Long userId, TodoItem todoItem) {
		return userRepository.findById(userId).map(user -> {
			todoItem.setUser(user);
			return todoItemRepository.save(todoItem);
		}).orElseThrow();
	}

	/**
	 * Delete item by user and item ID
	 */
	@Transactional
	public void deleteItem(Long userId, Long itemId) {
		TodoItem item = todoItemRepository.findByIdAndUserId(itemId, userId).orElseThrow();
		todoItemRepository.delete(item);
	}
	
}
