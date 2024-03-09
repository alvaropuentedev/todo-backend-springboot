package dev.alvaropuente.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alvaropuente.backend.models.ItemList;
import dev.alvaropuente.backend.services.ItemListService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = { "https://todo-angular-beta.vercel.app", "http://localhost:4200/", "https://alvaropuente.dev/" })
@RequestMapping("/apitodo")
public class ListsController {
	
	@Autowired
	private ItemListService itemListService;

	@GetMapping("/user/{user_id}/lists")
	public ResponseEntity<?> getMethodName(@PathVariable(value = "user_id") Long user_id) {
		try {
			List<ItemList> itemList = itemListService.getAllListsByUserId(user_id);
			return new ResponseEntity<>(itemList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}	
	
	@PostMapping("/user/{user_id}/list")
	public String createListForUser(@PathVariable(value = "user_id") Long user_id, @RequestBody ItemList list) {
		return itemListService.createListforUser(user_id, list);
	}
	
	@DeleteMapping("/list/{list_id}")
	public void deleteList(@PathVariable(value = "list_id") Long list_id) {
		itemListService.deleteList(list_id);
	} 
}
