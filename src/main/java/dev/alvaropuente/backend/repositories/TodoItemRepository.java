package dev.alvaropuente.backend.repositories;

import dev.alvaropuente.backend.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
