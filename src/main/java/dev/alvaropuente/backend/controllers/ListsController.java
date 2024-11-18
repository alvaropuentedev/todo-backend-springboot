package dev.alvaropuente.backend.controllers;

import dev.alvaropuente.backend.models.ItemList;
import dev.alvaropuente.backend.models.User;
import dev.alvaropuente.backend.services.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {"https://todo-angular-beta.vercel.app", "http://localhost:4200/", "https://alvaropuente.dev/"})
@RequestMapping("/apitodo")
public class ListsController {

    @Autowired
    private ItemListService itemListService;

    @GetMapping("/user/{user_id}/lists")
    public ResponseEntity<?> getAllListsByUserId(@PathVariable(value = "user_id") Long user_id) {
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

    @PostMapping("/list/{list_id}/user")
    public ResponseEntity<?> addUsersToList(@PathVariable(value = "list_id") Long list_id, @RequestBody String user) {
        try {
            if (!StringUtils.hasText(user)) {
                return new ResponseEntity<>("El nombre de usuario no puede estar vacío", HttpStatus.BAD_REQUEST);
            }

            itemListService.addUsersToList(list_id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/list/{list_id}")
    public void deleteList(@PathVariable(value = "list_id") Long list_id) {
        itemListService.deleteList(list_id);
    }
}
