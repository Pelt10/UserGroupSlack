package fr.pelt10.usergroupslack;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

public class Bot {
    private SlackSession session;
    private String users;

    public Bot(String token, List<String> usernames) {
	try {
	    session = SlackSessionFactory.createWebSocketSlackSession(token);
	    session.connect();
	    users = usernames.stream()
		    	     .map(user -> ("<@" + session.findUserByUserName(user).getId() + ">"))
		    	     .collect(Collectors.joining(", "));

	    System.out.println("@" + session.sessionPersona().getUserName() + " enable.");

	    slackMessagePosted(session);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void slackMessagePosted(SlackSession session) {
	session.addMessagePostedListener((event, session1) -> {
	    if (session.sessionPersona().getId().equals(event.getSender().getId())
		    || !event.getMessageContent().contains("<@" + session1.sessionPersona().getId() + ">")) {
		return;
	    }
	    session.sendMessage(event.getChannel(), users);
	});
    }

}
