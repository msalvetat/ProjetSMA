package m2dl.jlm.projetsma.agent

import static org.junit.Assert.*;

import org.junit.Test

import sma.EcoAgents
import spock.lang.Specification;;

class EcoAgentsTest extends Specification {

	def 'check if the ecoAgents is correctly initialised'() {
		
		when:
		EcoAgents.Component ecoAgents = new EcoAgentsImpl().newComponent();
		
		then:
		ecoAgents instanceof EcoAgents.Component
	}

}
