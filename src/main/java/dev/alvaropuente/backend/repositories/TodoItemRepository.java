package dev.alvaropuente.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alvaropuente.backend.models.TodoItem;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
	List<TodoItem> findByUserId(Long userId);
	Optional<TodoItem> findByIdAndUserId(Long id, Long userId);
}
