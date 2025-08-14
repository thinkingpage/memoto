package com.example.memoto.repository;

import com.example.memoto.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

    Optional<Memo> findMemoById(long id);
    void deleteById(long id);

    List<Memo> findFirst10ByOrderByCreatedOnDesc();

    List<Memo> findByTitleContainingAndCreatedOnBetween(String title, Instant start, Instant end);

    @Query(value="select m from Memo m where m.title like :title "
            + "and m.createdOn >= :start "
            + "and m.createdOn <= :end")
    List<Memo> test(
            @Param("title") String title,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

}
