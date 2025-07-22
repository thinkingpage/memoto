package com.example.dbtest.service;

import com.example.dbtest.exception.MemoNotFoundException;
import com.example.dbtest.model.Memo;
import com.example.dbtest.repository.MemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Transactional
    public Memo saveMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public List<Memo> findAllMemos() {
        return memoRepository.findAll();
    }

    @Transactional
    public void deleteMemoById(long id) {
        memoRepository.deleteById(id);
        System.out.println("memo has been deleted");
    }

    @Transactional
    public void addMemo(Memo memo) {
        memoRepository.save(memo);
    }

    public Memo findMemoById(long id) {
        return memoRepository.findMemoById(id).
                orElseThrow(() -> new MemoNotFoundException("MemoService not found"));
    }

    public List<Memo> find10FirstMemos() {
        return memoRepository.findFirst10ByOrderByCreatedOnDesc();
    }

    public List<Memo> findByTitleContaining(String title, int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = startDate.plusYears(1);
        return memoRepository.findByTitleContainingAndCreatedOnBetween(title, startDate.atStartOfDay().toInstant(ZoneOffset.UTC), endDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    public List<Memo> finde(String title, int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = startDate.plusYears(1);
        return memoRepository.test(title, startDate.atStartOfDay().toInstant(ZoneOffset.UTC), endDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

}
