package edu.udacity.java.nano.chat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	private Message message;

	@Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("login"));
        
	}
	@Test
    public void chat() throws Exception {
        this.mockMvc.perform(get("/chat?username=me")).andDo(print()).andExpect(status().isOk())
        .andExpect(view().name("chat"));
        
    
    }

}
