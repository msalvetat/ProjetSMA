package m2dl.jlm.projetsma.agent.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl;
import m2dl.jlm.projetsma.environment.impl.Room;

public class EtudiantAgent implements ITwoStepsAgent {

	Knowledge knowledge;
	String id;
	IMsgBox msgBox;

	public EtudiantAgent(String id) {
		this.id = id;
		this.knowledge = new Knowledge();
		this.msgBox = AgentMessaging.getMsgBox(this.id, String.class);
	}

	public void perceive() {
		Iterator messagesIt = this.msgBox.getMsgs().iterator();
		//Read messages
		while (messagesIt.hasNext()) {
			String msg = (String) messagesIt.next();
			System.out.println(msg);
		}
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void decideAndAct() {
			
	}

	public String getId() {
		return id;
	}
	
}
