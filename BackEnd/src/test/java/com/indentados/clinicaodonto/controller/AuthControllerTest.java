package com.indentados.clinicaodonto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indentados.clinicaodonto.DTO.TokenDTO;
import com.indentados.clinicaodonto.DTO.UsuarioDTO;
import com.indentados.clinicaodonto.config.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TokenService tokenService;

    @Test
    void autenticar() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO("admin","123456");

        ObjectMapper mapper = new ObjectMapper();
        String usuarioJson = mapper.writeValueAsString(usuarioDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .content(usuarioJson)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TokenDTO tokenDTO = mapper.readValue(mvcResult.getResponse().getContentAsString(), TokenDTO.class);

        String token = tokenDTO.getToken();

        Assertions.assertEquals(usuarioDTO.getUsername(),tokenService.getUsernameUsuario(token));
    }

}
