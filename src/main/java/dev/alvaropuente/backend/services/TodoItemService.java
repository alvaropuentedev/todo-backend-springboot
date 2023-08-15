package dev.alvaropuente.backend.services;

import dev.alvaropuente.backend.models.TodoItem;
import dev.alvaropuente.backend.repositories.TodoItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoItemService {


    private final TodoItemRepository todoItemRepository;

    @Transactional(readOnly = true)
    public List<TodoItem> getAllItems() {
        return todoItemRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }
    @Transactional
    public TodoItem save(TodoItem todoItem) {
        if(todoItem.getIdItem() == null) {
            todoItem.setCreatedAt(LocalDateTime.now());
        }

        todoItem.setUpdatedAt(LocalDateTime.now());
        return todoItemRepository.save(todoItem);
    }
    @Transactional
    public void delete(Long id) {
        todoItemRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return todoItemRepository.existsById(id);
    }
}
