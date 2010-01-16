package com.twoguys.rps;

import static org.mockito.Mockito.*;

import org.junit.*;

public class PlayerPromptTest {

    @Test
    public void testPlayerPromptSendsPromptToUser() throws Exception {
	Prompt mockPrompt = mock(Prompt.class);
	Response mockResp = mock(Response.class);

	PlayerPrompt playerPrompt = new PlayerPrompt(mockPrompt, mockResp);
	playerPrompt.prompt();

	verify(mockPrompt).prompt();
    }

}