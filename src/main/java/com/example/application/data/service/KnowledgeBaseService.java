package com.example.application.data.service;

import com.example.application.data.entity.KnowledgeBase;
import com.example.application.data.repository.KnowledgeBaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {
    private final KnowledgeBaseRepository repository;

    public KnowledgeBaseService(KnowledgeBaseRepository repository) {
        this.repository = repository;
    }

    public Optional<KnowledgeBase> get(Long id) {
        return repository.findById(id);
    }

    public KnowledgeBase update(KnowledgeBase entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public int count() {
        return (int) repository.count();
    }

    public List<KnowledgeBase> findAll() {
        return repository.findAll();
    }

    public Optional<KnowledgeBase> findById(long id) {
        return repository.findById(id);
    }
}
