package com.example.memoto.service;

import com.example.memoto.exception.MemoNotFoundException;
import com.example.memoto.kafka.MemoEvent;
import com.example.memoto.kafka.producer.MyKafkaProducer;
import com.example.memoto.model.Memo;
import com.example.memoto.model.User;
import com.example.memoto.repository.MemoRepository;
import com.example.memoto.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final MyKafkaProducer kafkaProducer;

    public MemoService(MemoRepository memoRepository, MyKafkaProducer kafkaProducer, UserService userService, UserRepository userRepository) {
        this.memoRepository = memoRepository;
        this.kafkaProducer = kafkaProducer;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteMemoById(long id) {
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        String userId = currentUser.getId();

        Optional<Memo> memo = memoRepository.findByIdAndUserId(id, userId);
        if(memo.isEmpty()) {
            System.out.println("Either you are not authorized or there is no memo with that id");
        }
        memoRepository.deleteById(id);
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
