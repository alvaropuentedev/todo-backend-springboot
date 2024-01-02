package dev.alvaropuente.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alvaropuente.backend.models.TodoItem;
import dev.alvaropuente.backend.services.TodoItemService;
import lombok.AllArgsConstructor;

@RestController
//@CrossOrigin(origins = {"https://todo-angular-beta.vercel.app"})
@CrossOrigin(origins = { "https://todo-angular-beta.vercel.app", "http://localhost:4200/" })
@AllArgsConstructor
@RequestMapping("/api")
public class TodoItemController {

	@Autowired
	private final TodoItemService todoItemService;

	@GetMapping("/user/{userId}/items")
	public ResponseEntity<?> getAllItemsByUserId(@PathVariable(value = "userId") Long userId) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<TodoItem> todoItemList = todoItemService.getAllItemsByUserId(userId);
			return new ResponseEntity<>(todoItemList, HttpStatus.OK);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/user/{userId}/items")
	public TodoItem createItem(@PathVariable(value = "userId") Long userId, @Validated @RequestBody TodoItem todoItem) {
		return todoItemService.createItem(userId, todoItem);
	}

	@DeleteMapping("/user/{userId}/items/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "itemId") Long itemId) {
		todoItemService.deleteItem(userId, itemId);
		return ResponseEntity.ok().build();
	}

}
