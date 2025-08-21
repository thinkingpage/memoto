package com.example.memoto.service;

import com.example.memoto.dto.MemoDTO;
import com.example.memoto.exception.MemoNotFoundException;
import com.example.memoto.model.Memo;
import com.example.memoto.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

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

    public List<MemoDTO> findAllMemosDTO() {
        return memoRepository.findAll().stream().
                map(MemoDTO::new).
                collect(Collectors.toList());
    }

    @Transactional
    public void deleteMemoById(long id) {
        memoRepository.deleteById(id);
        System.out.println("memo has been deleted");
    }

    @Transactional
    public Memo addMemo(Memo memo) {
        return memoRepository.save(memo);
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
