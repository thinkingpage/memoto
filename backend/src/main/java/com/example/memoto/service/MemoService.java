package com.example.memoto.service;

import com.example.memoto.exception.MemoNotFoundException;
import com.example.memoto.kafka.producer.MyKafkaProducer;
import com.example.memoto.model.Memo;
import com.example.memoto.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;
    private final MyKafkaProducer kafkaProducer;

    public MemoService(MemoRepository memoRepository,  MyKafkaProducer kafkaProducer) {
        this.memoRepository = memoRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional
    public void deleteMemoById(long id) {
        memoRepository.deleteById(id);
        System.out.println("memo has been deleted");
    }

    @Transactional
    public Memo addMemo(Memo memo) {
        Memo savedMemo = memoRepository.save(memo);

        String message = String.format("new memo with title: %s added!", savedMemo.getTitle());
        kafkaProducer.send("memo-created", message);

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
