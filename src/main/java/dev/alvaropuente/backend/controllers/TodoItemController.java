package dev.alvaropuente.backend.controllers;


import dev.alvaropuente.backend.models.TodoItem;
import dev.alvaropuente.backend.services.TodoItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping("/todoitems")
    public ResponseEntity<?> getAllItems() {
        try {
        List<TodoItem> todoItemList = todoItemService.getAllItems();
        return new ResponseEntity<>(todoItemList, HttpStatus.OK);
        }
        catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @GetMapping("/todoitems/{idItems}")
    public TodoItem getItemById(@PathVariable Long idItems) {
        return  todoItemService.getById(idItems).orElseThrow(
                ()->new NoSuchElementException("Requested item not found "));
    }

    @PostMapping("/todoitems")
    public TodoItem createItem(@RequestBody TodoItem todoItem) {
            return  todoItemService.save(todoItem);
    }

    @PutMapping("/todoitems/{idItems}")
    public ResponseEntity<?> updateItem(@RequestBody TodoItem itemParams, @PathVariable Long idItems) {
        if (todoItemService.existById(idItems)) {
            TodoItem todoItem = todoItemService.getById(idItems).orElseThrow(
                    () -> new NoSuchElementException("Requested item not found"));
            todoItem.setDescription(itemParams.getDescription());
            todoItemService.save(todoItem);
            return ResponseEntity.ok().body(todoItem);
        } else {
            Map<String, String> message = new HashMap<>();
            message.put("message", idItems + "item not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/todoitems/{idItems}")
    public ResponseEntity<?> deleteItem(@PathVariable Long idItems) {
        if (todoItemService.existById(idItems)) {
            todoItemService.delete(idItems);
            Map<String, String> message = new HashMap<>();
            message.put("message", idItems + " item removed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            Map<String, String> message = new HashMap<>();
            message.put("message", idItems + " item not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }


}
