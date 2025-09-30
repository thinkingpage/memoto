package com.example.memoto.service;

import com.example.memoto.exception.MemoNotFoundException;
import com.example.memoto.model.Memo;
import com.example.memoto.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
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

    @Transactional
    public Memo saveMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public List<Memo> findAllMemos() {
        return memoRepository.findAll();
    }

    public List<Memo> findAllMemosOfUser(String username) {
        return memoRepository.findByUser_Username(username);
    }

    public Memo findMemoById(long id) {
        return memoRepository.findMemoById(id).
                orElseThrow(() -> new MemoNotFoundException("MemoService not found"));
    }
}
