//package com.example.memoto;
//
//import com.example.memoto.model.Memo;
//import com.example.memoto.model.User;
//import com.example.memoto.service.MemoService;
//import com.example.memoto.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@DisplayName("Memo Add Integration Tests")
//class MemoAddIntegrationTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private MemoService memoService;
//
//    @Test
//    @WithMockUser(username = "testuser")
//    @DisplayName("Should add memo successfully")
//    void shouldAddMemoSuccessfully() throws Exception {
//        User mockUser = new User();
//        mockUser.setId("1");
//        mockUser.setUsername("testuser");
//
//        Memo inputMemo = new Memo();
//        inputMemo.setTitle("Test Memo");
//        inputMemo.setContent("Test Content");
//
//        Memo savedMemo = new Memo();
//        savedMemo.setId(1L);
//        savedMemo.setTitle("Test Memo");
//        savedMemo.setContent("Test Content");
//        savedMemo.setUser(mockUser);
//
//        // Mock the services
//        when(userService.getCurrentUser(any())).thenReturn(mockUser);
//        when(userService.getCurrentUser(null)).thenReturn(mockUser);
//        when(userService.getCurrentUser(isNull())).thenReturn(mockUser);
//
//        when(memoService.addMemo(any(Memo.class))).thenReturn(savedMemo);
//
//        // When & Then
//        mvc.perform(post("/memos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputMemo)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.title", is("Test Memo")))
//                .andExpect(jsonPath("$.content", is("Test Content")))
//                .andExpect(jsonPath("$.user.username", is("testuser")));
//
//        // Verify interactions
//        verify(userService, times(1)).getCurrentUser(any(Authentication.class));
//        verify(memoService, times(1)).addMemo(any(Memo.class));
//    }