package com.example.memoto;

import com.example.memoto.dto.MemoDTO;
import com.example.memoto.model.Memo;
import com.example.memoto.model.User;
import com.example.memoto.service.MemoService;
import com.example.memoto.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


// https://docs.spring.io/spring-data/relational/reference/repositories/core-concepts.html
// https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html

@RestController
public class MemoController {

    // TODO: do not just return .OK. -> better handling in angular
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
        List<MemoDTO> memos = memoService.findAllMemosDTO();
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


    @PostMapping("/memos")
    public ResponseEntity<Memo> addMemo(@RequestBody Memo memo, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication);
        memo.setUser(currentUser);
        memo.setId(null);
        Memo savedMemo = memoService.addMemo(memo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMemo);
    }


    @GetMapping("/find10firstmemos")
    public ResponseEntity<List<Memo>> find10firstmemos() {
        return new ResponseEntity<>(memoService.find10FirstMemos(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Memo>> find(
            @RequestParam(name="title") String title,
            @RequestParam(name="year") int year
    ) {
        return new ResponseEntity<>(memoService.findByTitleContaining(title, year), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Memo>> finde(
            @RequestParam(name="title") String title,
            @RequestParam(name="year") int year
    ) {
        return new ResponseEntity<>(memoService.finde(title, year), HttpStatus.OK);
    }

    // keycloak tests
    @GetMapping("/")
    public String home(OAuth2AuthenticationToken token) {
        if (token != null) {
            String username = token.getPrincipal().getAttribute("preferred_username");
            return "Welcome " + username + "! </br>" +
                    "<a href='/secure'>Secured area</a> | " +
                    "<a href='/user'>User info</a> | " +
                    "<a href='/logout'>Logout</a>";
        }
        return "Welcome to public area! " +
                "<a href='/secure'>Secured area (requires login)</a> | " +
                "<a href='/oauth2/authorization/keycloak'>Login</a>";
    }

    @GetMapping("/secure")
    public String secure(OAuth2AuthenticationToken token) {
        return "Hello <b>" + token.getPrincipal().getAttribute("preferred_username") + "</b> you have access. </br>" + "You can <a href='/logout'>Logout</a> too";
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> user(OAuth2AuthenticationToken token) {
        if (token != null) {
            OAuth2User principal = token.getPrincipal();
            return ResponseEntity.ok(principal.getAttributes());
        }
        return ResponseEntity.ok(Map.of("error", "not logged in"));
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/realms/spring-realm/protocol/openid-connect/login?redirect_uri=http://localhost:8081/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/realms/spring-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8081/";
    }

    @GetMapping("/force-logout")
    public String forceLogout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/realms/spring-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8081/";
    }
}
