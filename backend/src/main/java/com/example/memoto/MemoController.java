package com.example.memoto;

import com.example.memoto.dto.MemoDTO;
import com.example.memoto.model.Memo;
import com.example.memoto.model.User;
import com.example.memoto.service.MemoService;
import com.example.memoto.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MemoController {

    MemoService memoService;
    UserService userService;

    public MemoController(MemoService memoService, UserService userService) {
        this.memoService = memoService;
        this.userService = userService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/memos")
    public ResponseEntity<List<Memo>> findAllMemos() {
        List<Memo> memos = memoService.findAllMemos();
        return new ResponseEntity<>(memos, HttpStatus.OK);
    }

    @GetMapping("/memosdto")
    public ResponseEntity<List<MemoDTO>> findAllMemosDTO() {
        List<MemoDTO> memos = memoService.findAllMemos()
                .stream()
                .map(MemoDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(memos, HttpStatus.OK);
    }

    @GetMapping("/memos/{id}")
    public ResponseEntity<Memo> findMemoById(@PathVariable long id) {
        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/memos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        memoService.deleteMemoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/memosdto/{id}")
    public ResponseEntity<MemoDTO> findMemoDTOById(@PathVariable long id) {
        return new ResponseEntity<>(new MemoDTO(memoService.findMemoById(id)), HttpStatus.OK);
    }

    @PostMapping("/memos")
    public ResponseEntity<MemoDTO> addMemo(@RequestBody Memo memo, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication);
        memo.setUser(currentUser);
        memo.setId(null);
        Memo savedMemo = memoService.addMemo(memo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemoDTO(savedMemo));
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> user(OAuth2AuthenticationToken token) {
        if (token != null) {
            OAuth2User principal = token.getPrincipal();
            return ResponseEntity.ok(principal.getAttributes());
        }
        return ResponseEntity.ok(Map.of("error", "not logged in"));
    }
}
