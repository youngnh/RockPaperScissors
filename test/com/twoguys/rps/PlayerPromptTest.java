package com.twoguys.rps;

import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;

public class PlayerPromptTest {

    @Test
    public void testPlayerPromptSendsPromptToUser() throws Exception {
	Prompt mockPrompt = mock(Prompt.class);
	Response mockResp = mock(Response.class);

	PlayerPrompt playerPrompt = new PlayerPrompt(mockPrompt, mockResp);
	playerPrompt.prompt();

	verify(mockPrompt).prompt();
    }

    @Test
    public void testPlayerPromptReadsResponse() throws Exception {
	Prompt mockPrompt = mock(Prompt.class);
	Response mockResp = mock(Response.class);

	PlayerPrompt playerPrompt = new PlayerPrompt(mockPrompt, mockResp);
	playerPrompt.prompt();

	verify(mockResp).read();
    }

    @Test
    public void testPlayerPromptFirstPromptsThenReads() throws Exception {
	Prompt mockPrompt = mock(Prompt.class);
	Response mockResp = mock(Response.class);

	PlayerPrompt playerPrompt = new PlayerPrompt(mockPrompt, mockResp);
	playerPrompt.prompt();

	InOrder inOrder = inOrder(mockPrompt, mockResp);
	
	inOrder.verify(mockPrompt).prompt();
	inOrder.verify(mockResp).read();
    }

}