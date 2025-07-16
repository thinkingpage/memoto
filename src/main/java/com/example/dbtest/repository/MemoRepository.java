package com.example.dbtest.repository;

import com.example.dbtest.model.Memo;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

    Memo getReferenceById(long id);
    Memo deleteById(long id);

    List<Memo> findFirst10ByOrderByCreatedOnDesc();
}
