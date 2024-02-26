package dev.alvaropuente.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alvaropuente.backend.models.ItemList;
import dev.alvaropuente.backend.services.ItemListService;

@RestController
@CrossOrigin(origins = { "https://todo-angular-beta.vercel.app", "http://localhost:4200/", "https://alvaropuente.dev/" })
@RequestMapping("/apitodo")
public class ListsController {
	
	@Autowired
	private ItemListService itemListService;
	
	@PostMapping("/user/{user_id}/list")
	public String createListForUser(@PathVariable(value = "user_id") Long user_id, @RequestBody ItemList list) {
		return itemListService.createListforUser(user_id, list);
	}
	
	@DeleteMapping("/list/{list_id}")
	public void deleteList(@PathVariable(value = "list_id") Long list_id) {
		itemListService.deleteList(list_id);
	} 
}
