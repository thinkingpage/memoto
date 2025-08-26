package com.example.memoto;

import com.example.memoto.model.Memo;
import com.example.memoto.model.User;
import com.example.memoto.service.MemoService;
import com.example.memoto.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MemotoApplicationTests {

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        public UserService userService() {
            return mock(UserService.class);
        }

        @Bean
        @Primary
        public MemoService memoService() {
            return mock(MemoService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private MemoService memoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void isAddMemoPossible_WithoutLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/memos"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void isAddMemoPossible_WithLogin() throws Exception {

        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setId("1-2-3");

        Memo inputMemo = new Memo();
        inputMemo.setTitle("testTitle");
        inputMemo.setContent("testContent");

        Memo savedMemo = new Memo();
        savedMemo.setId(1L);
        savedMemo.setTitle("testTitle");
        savedMemo.setContent("testContent");
        savedMemo.setUser(testUser);

        when(userService.getCurrentUser(any(Authentication.class))).thenReturn(testUser);
        when(memoService.addMemo(any(Memo.class))).thenReturn(savedMemo);

        mockMvc.perform(MockMvcRequestBuilders.post("/memos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputMemo)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("testTitle"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("testContent"));
    }
}