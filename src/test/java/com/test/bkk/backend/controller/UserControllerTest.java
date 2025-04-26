package com.test.bkk.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bkk.backend.config.TestAppConfig;
import com.test.bkk.backend.dto.*;
import com.test.bkk.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(UserController.class)
@Import(TestAppConfig.class)
public class UserControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private UserService userService;

        @Autowired
        private ObjectMapper objectMapper;

        private User sampleUser;

        @BeforeEach
        void setup() {
                Geo geo = new Geo();
                geo.setId(1);
                geo.setLat("-37.3159");
                geo.setLng("81.1496");
            
                Address address = new Address();
                address.setId(1);
                address.setStreet("Kulas Light");
                address.setSuite("Apt. 556");
                address.setCity("Gwenborough");
                address.setZipcode("92998-3874");
                address.setGeo(geo);
            
                Company company = new Company();
                company.setId(1);
                company.setName("Romaguera-Crona");
                company.setCatchPhrase("Multi-layered client-server neural-net");
                company.setBs("harness real-time e-markets");
            
                // ✅ FIXED: Now this sets the correct class-level sampleUser!
                sampleUser = new User();
                sampleUser.setId(1);
                sampleUser.setName("Leanne Graham");
                sampleUser.setUsername("Bret");
                sampleUser.setEmail("Sincere@april.biz");
                sampleUser.setPhone("1-770-736-8031 x56442");
                sampleUser.setWebsite("hildegard.org");
                sampleUser.setAddress(address);
                sampleUser.setCompany(company);
            

        }

        @Test
        void testGetAllUsers() throws Exception {
                when(userService.getAllUsers()).thenReturn(List.of(sampleUser));

                mockMvc.perform(get("/users"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].name").value("Leanne Graham"));
        }

        @Test
        void testGetUserById_Found() throws Exception {
                when(userService.getUserById(1)).thenReturn(Optional.of(sampleUser));

                mockMvc.perform(get("/users/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.username").value("Bret"));
        }

        @Test
        void testGetUserById_NotFound() throws Exception {
                when(userService.getUserById(99)).thenReturn(Optional.empty());

                mockMvc.perform(get("/users/99"))
                .andDo(print())
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.error").value("User not found"));
        }

        @Test
        void testCreateUser_Success() throws Exception {
                doNothing().when(userService).createUser(any(User.class));

                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleUser)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.message").value("User created successfully"));
        }

        @Test
        void testCreateUser_Conflict() throws Exception {
                doThrow(new IllegalArgumentException("User with this ID already exists"))
                                .when(userService).createUser(any(User.class));

                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleUser)))
                                .andExpect(status().isConflict())
                                .andExpect(jsonPath("$.error").value("User with this ID already exists"));
        }

        @Test
        void testUpdateUser_Success() throws Exception {
                doNothing().when(userService).updateUser(eq(1), any(User.class));

                mockMvc.perform(put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleUser)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("User updated successfully"));
        }

        @Test
        void testUpdateUser_NotFound() throws Exception {
                doThrow(new NoSuchElementException("User not found"))
                                .when(userService).updateUser(eq(99), any(User.class));

                mockMvc.perform(put("/users/99")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleUser)))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.error").value("User not found"));
        }

        @Test
        void testDeleteUser_Success() throws Exception {
                doNothing().when(userService).deleteUser(1);

                mockMvc.perform(delete("/users/1"))
                                .andExpect(status().isNoContent());
        }

        @Test
        void testDeleteUser_NotFound() throws Exception {
                doThrow(new NoSuchElementException("User not found"))
                                .when(userService).deleteUser(99);

                mockMvc.perform(delete("/users/99"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.error").value("User not found"));
        }

}
