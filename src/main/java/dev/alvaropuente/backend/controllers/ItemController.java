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

import dev.alvaropuente.backend.models.Item;
import dev.alvaropuente.backend.services.ItemService;

@RestController
@CrossOrigin(origins = { "https://todo-angular-beta.vercel.app", "http://localhost:4200/", "https://alvaropuente.dev/" })
@RequestMapping("/apitodo")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/list/{list_id}/items")
	public ResponseEntity<?> getAllItemsByListId(@PathVariable(value = "list_id") Long list_id) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Item> itemList = itemService.getAllItemsByListId(list_id);
			return new ResponseEntity<>(itemList, HttpStatus.OK);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/list/{list_id}/items")
	public Item createItem(@PathVariable(value = "list_id") Long list_id, @Validated @RequestBody Item item) {
		return itemService.createItem(list_id, item);
	}

	@DeleteMapping("/item/{item_id}")
	public void deleteItem(@PathVariable(value = "item_id") Long item_id) {
		itemService.deleteItem(item_id);
	}

}
