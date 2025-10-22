package com.example.memoto.service;

import com.example.memoto.exception.MemoNotFoundException;
import com.example.memoto.kafka.MemoEvent;
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

    public MemoService(MemoRepository memoRepository, MyKafkaProducer kafkaProducer) {
        this.memoRepository = memoRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional
    public void deleteMemoById(long id) {
        memoRepository.deleteById(id);
        System.out.println("MemoService.deleteMemoById");
        kafkaProducer.send("memo-topic", "memo-deleted");
    }

    @Transactional
    public Memo addMemo(Memo memo) {
        Memo savedMemo = memoRepository.save(memo);
        MemoEvent memoEvent = new MemoEvent("memo-created", savedMemo);
        System.out.println("MemoService.addMemo");
//        kafkaProducer.send("memo-topic", memoEvent.eventType(), memoEvent);
        kafkaProducer.send("memo-topic", "memo-created");
        return savedMemo;
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
