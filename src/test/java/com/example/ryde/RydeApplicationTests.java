// package com.example.ryde;

// import com.example.ryde.dto.UserDto;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

// @SpringBootTest
// @AutoConfigureMockMvc
// class RegisterControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     void testUserRegistration() throws Exception {
//         UserDto userDto = new UserDto();
//         userDto.setUsername("testUser");
//         userDto.setPassword("password123");
//         userDto.setEmail("testuser@example.com");
//         userDto.setIban("DE89370400440532013000");

//         mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("{\"username\":\"testUser\",\"password\":\"password123\",\"email\":\"testuser@example.com\",\"iban\":\"DE89370400440532013000\"}"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/"));
//     }
// }