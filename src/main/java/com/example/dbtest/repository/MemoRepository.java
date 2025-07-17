package com.example.dbtest.repository;

import com.example.dbtest.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

    Memo getReferenceById(long id);
    void deleteById(long id);

    List<Memo> findFirst10ByOrderByCreatedOnDesc();
}
