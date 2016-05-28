package sma;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.agent.IKnowledge;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;
import sma.agent.EcoStudent;
import sma.agent.EcoTeacher;
import sma.knowledge.EcoKnowledge;

@SuppressWarnings("all")
public abstract class EcoAgents {
  public interface Requires {
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IEnvironment environment();
    
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IMessagingService messagingService();
    
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public ISchedulingService strategyService();
  }
  
  public interface Component extends EcoAgents.Provides {
  }
  
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public ICreateAgent createAgent();
  }
  
  public interface Parts {
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoKnowledge.Component ecoKnowledge();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoTeacher.Component ecoTeacher();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoStudent.Component ecoStudent();
  }
  
  public static class ComponentImpl implements EcoAgents.Component, EcoAgents.Parts {
    private final EcoAgents.Requires bridge;
    
    private final EcoAgents implementation;
    
    public void start() {
      assert this.ecoKnowledge != null: "This is a bug.";
      ((EcoKnowledge.ComponentImpl) this.ecoKnowledge).start();
      assert this.ecoTeacher != null: "This is a bug.";
      ((EcoTeacher.ComponentImpl) this.ecoTeacher).start();
      assert this.ecoStudent != null: "This is a bug.";
      ((EcoStudent.ComponentImpl) this.ecoStudent).start();
      this.implementation.start();
      this.implementation.started = true;
    }
    
    private void init_ecoKnowledge() {
      assert this.ecoKnowledge == null: "This is a bug.";
      assert this.implem_ecoKnowledge == null: "This is a bug.";
      this.implem_ecoKnowledge = this.implementation.make_ecoKnowledge();
      if (this.implem_ecoKnowledge == null) {
      	throw new RuntimeException("make_ecoKnowledge() in sma.EcoAgents should not return null.");
      }
      this.ecoKnowledge = this.implem_ecoKnowledge._newComponent(new BridgeImpl_ecoKnowledge(), false);
      
    }
    
    private void init_ecoTeacher() {
      assert this.ecoTeacher == null: "This is a bug.";
      assert this.implem_ecoTeacher == null: "This is a bug.";
      this.implem_ecoTeacher = this.implementation.make_ecoTeacher();
      if (this.implem_ecoTeacher == null) {
      	throw new RuntimeException("make_ecoTeacher() in sma.EcoAgents should not return null.");
      }
      this.ecoTeacher = this.implem_ecoTeacher._newComponent(new BridgeImpl_ecoTeacher(), false);
      
    }
    
    private void init_ecoStudent() {
      assert this.ecoStudent == null: "This is a bug.";
      assert this.implem_ecoStudent == null: "This is a bug.";
      this.implem_ecoStudent = this.implementation.make_ecoStudent();
      if (this.implem_ecoStudent == null) {
      	throw new RuntimeException("make_ecoStudent() in sma.EcoAgents should not return null.");
      }
      this.ecoStudent = this.implem_ecoStudent._newComponent(new BridgeImpl_ecoStudent(), false);
      
    }
    
    protected void initParts() {
      init_ecoKnowledge();
      init_ecoTeacher();
      init_ecoStudent();
    }
    
    private void init_createAgent() {
      assert this.createAgent == null: "This is a bug.";
      this.createAgent = this.implementation.make_createAgent();
      if (this.createAgent == null) {
      	throw new RuntimeException("make_createAgent() in sma.EcoAgents should not return null.");
      }
    }
    
    protected void initProvidedPorts() {
      init_createAgent();
    }
    
    public ComponentImpl(final EcoAgents implem, final EcoAgents.Requires b, final boolean doInits) {
      this.bridge = b;
      this.implementation = implem;
      
      assert implem.selfComponent == null: "This is a bug.";
      implem.selfComponent = this;
      
      // prevent them to be called twice if we are in
      // a specialized component: only the last of the
      // hierarchy will call them after everything is initialised
      if (doInits) {
      	initParts();
      	initProvidedPorts();
      }
    }
    
    private ICreateAgent createAgent;
    
    public ICreateAgent createAgent() {
      return this.createAgent;
    }
    
    private EcoKnowledge.Component ecoKnowledge;
    
    private EcoKnowledge implem_ecoKnowledge;
    
    private final class BridgeImpl_ecoKnowledge implements EcoKnowledge.Requires {
      public final IEnvironment environment() {
        return EcoAgents.ComponentImpl.this.bridge.environment();
      }
    }
    
    public final EcoKnowledge.Component ecoKnowledge() {
      return this.ecoKnowledge;
    }
    
    private EcoTeacher.Component ecoTeacher;
    
    private EcoTeacher implem_ecoTeacher;
    
    private final class BridgeImpl_ecoTeacher implements EcoTeacher.Requires {
      public final ISchedulingService strategyService() {
        return EcoAgents.ComponentImpl.this.bridge.strategyService();
      }
      
      public final IMessagingService messagingService() {
        return EcoAgents.ComponentImpl.this.bridge.messagingService();
      }
    }
    
    public final EcoTeacher.Component ecoTeacher() {
      return this.ecoTeacher;
    }
    
    private EcoStudent.Component ecoStudent;
    
    private EcoStudent implem_ecoStudent;
    
    private final class BridgeImpl_ecoStudent implements EcoStudent.Requires {
      public final ISchedulingService strategyService() {
        return EcoAgents.ComponentImpl.this.bridge.strategyService();
      }
      
      public final IMessagingService messagingService() {
        return EcoAgents.ComponentImpl.this.bridge.messagingService();
      }
    }
    
    public final EcoStudent.Component ecoStudent() {
      return this.ecoStudent;
    }
  }
  
  public static class TeacherAgent {
    public interface Requires {
    }
    
    public interface Component extends EcoAgents.TeacherAgent.Provides {
    }
    
    public interface Provides {
    }
    
    public interface Parts {
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoKnowledge.KnowledgeS.Component knowledgeS();
      
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoTeacher.TeacherS.Component teacherS();
    }
    
    public static class ComponentImpl implements EcoAgents.TeacherAgent.Component, EcoAgents.TeacherAgent.Parts {
      private final EcoAgents.TeacherAgent.Requires bridge;
      
      private final EcoAgents.TeacherAgent implementation;
      
      public void start() {
        assert this.knowledgeS != null: "This is a bug.";
        ((EcoKnowledge.KnowledgeS.ComponentImpl) this.knowledgeS).start();
        assert this.teacherS != null: "This is a bug.";
        ((EcoTeacher.TeacherS.ComponentImpl) this.teacherS).start();
        this.implementation.start();
        this.implementation.started = true;
      }
      
      private void init_knowledgeS() {
        assert this.knowledgeS == null: "This is a bug.";
        assert this.implementation.use_knowledgeS != null: "This is a bug.";
        this.knowledgeS = this.implementation.use_knowledgeS._newComponent(new BridgeImpl_ecoKnowledge_knowledgeS(), false);
        
      }
      
      private void init_teacherS() {
        assert this.teacherS == null: "This is a bug.";
        assert this.implementation.use_teacherS != null: "This is a bug.";
        this.teacherS = this.implementation.use_teacherS._newComponent(new BridgeImpl_ecoTeacher_teacherS(), false);
        
      }
      
      protected void initParts() {
        init_knowledgeS();
        init_teacherS();
      }
      
      protected void initProvidedPorts() {
        
      }
      
      public ComponentImpl(final EcoAgents.TeacherAgent implem, final EcoAgents.TeacherAgent.Requires b, final boolean doInits) {
        this.bridge = b;
        this.implementation = implem;
        
        assert implem.selfComponent == null: "This is a bug.";
        implem.selfComponent = this;
        
        // prevent them to be called twice if we are in
        // a specialized component: only the last of the
        // hierarchy will call them after everything is initialised
        if (doInits) {
        	initParts();
        	initProvidedPorts();
        }
      }
      
      private EcoKnowledge.KnowledgeS.Component knowledgeS;
      
      private final class BridgeImpl_ecoKnowledge_knowledgeS implements EcoKnowledge.KnowledgeS.Requires {
      }
      
      public final EcoKnowledge.KnowledgeS.Component knowledgeS() {
        return this.knowledgeS;
      }
      
      private EcoTeacher.TeacherS.Component teacherS;
      
      private final class BridgeImpl_ecoTeacher_teacherS implements EcoTeacher.TeacherS.Requires {
        public final IKnowledge knowledge() {
          return EcoAgents.TeacherAgent.ComponentImpl.this.knowledgeS().knowledge();
        }
      }
      
      public final EcoTeacher.TeacherS.Component teacherS() {
        return this.teacherS;
      }
    }
    
    /**
     * Used to check that two components are not created from the same implementation,
     * that the component has been started to call requires(), provides() and parts()
     * and that the component is not started by hand.
     * 
     */
    private boolean init = false;;
    
    /**
     * Used to check that the component is not started by hand.
     * 
     */
    private boolean started = false;;
    
    private EcoAgents.TeacherAgent.ComponentImpl selfComponent;
    
    /**
     * Can be overridden by the implementation.
     * It will be called automatically after the component has been instantiated.
     * 
     */
    protected void start() {
      if (!this.init || this.started) {
      	throw new RuntimeException("start() should not be called by hand: to create a new component, use newComponent().");
      }
    }
    
    /**
     * This can be called by the implementation to access the provided ports.
     * 
     */
    protected EcoAgents.TeacherAgent.Provides provides() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoAgents.TeacherAgent.Requires requires() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("requires() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if requires() is needed to initialise the component.");
      }
      return this.selfComponent.bridge;
    }
    
    /**
     * This can be called by the implementation to access the parts and their provided ports.
     * 
     */
    protected EcoAgents.TeacherAgent.Parts parts() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    private EcoKnowledge.KnowledgeS use_knowledgeS;
    
    private EcoTeacher.TeacherS use_teacherS;
    
    /**
     * Not meant to be used to manually instantiate components (except for testing).
     * 
     */
    public synchronized EcoAgents.TeacherAgent.Component _newComponent(final EcoAgents.TeacherAgent.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of TeacherAgent has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoAgents.TeacherAgent.ComponentImpl  _comp = new EcoAgents.TeacherAgent.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoAgents.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoAgents.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoAgents.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoAgents.Parts eco_parts() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
  }
  
  public static class StudentAgent {
    public interface Requires {
    }
    
    public interface Component extends EcoAgents.StudentAgent.Provides {
    }
    
    public interface Provides {
    }
    
    public interface Parts {
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoKnowledge.KnowledgeS.Component knowledgeS();
      
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoStudent.StudentS.Component studentS();
    }
    
    public static class ComponentImpl implements EcoAgents.StudentAgent.Component, EcoAgents.StudentAgent.Parts {
      private final EcoAgents.StudentAgent.Requires bridge;
      
      private final EcoAgents.StudentAgent implementation;
      
      public void start() {
        assert this.knowledgeS != null: "This is a bug.";
        ((EcoKnowledge.KnowledgeS.ComponentImpl) this.knowledgeS).start();
        assert this.studentS != null: "This is a bug.";
        ((EcoStudent.StudentS.ComponentImpl) this.studentS).start();
        this.implementation.start();
        this.implementation.started = true;
      }
      
      private void init_knowledgeS() {
        assert this.knowledgeS == null: "This is a bug.";
        assert this.implementation.use_knowledgeS != null: "This is a bug.";
        this.knowledgeS = this.implementation.use_knowledgeS._newComponent(new BridgeImpl_ecoKnowledge_knowledgeS(), false);
        
      }
      
      private void init_studentS() {
        assert this.studentS == null: "This is a bug.";
        assert this.implementation.use_studentS != null: "This is a bug.";
        this.studentS = this.implementation.use_studentS._newComponent(new BridgeImpl_ecoStudent_studentS(), false);
        
      }
      
      protected void initParts() {
        init_knowledgeS();
        init_studentS();
      }
      
      protected void initProvidedPorts() {
        
      }
      
      public ComponentImpl(final EcoAgents.StudentAgent implem, final EcoAgents.StudentAgent.Requires b, final boolean doInits) {
        this.bridge = b;
        this.implementation = implem;
        
        assert implem.selfComponent == null: "This is a bug.";
        implem.selfComponent = this;
        
        // prevent them to be called twice if we are in
        // a specialized component: only the last of the
        // hierarchy will call them after everything is initialised
        if (doInits) {
        	initParts();
        	initProvidedPorts();
        }
      }
      
      private EcoKnowledge.KnowledgeS.Component knowledgeS;
      
      private final class BridgeImpl_ecoKnowledge_knowledgeS implements EcoKnowledge.KnowledgeS.Requires {
      }
      
      public final EcoKnowledge.KnowledgeS.Component knowledgeS() {
        return this.knowledgeS;
      }
      
      private EcoStudent.StudentS.Component studentS;
      
      private final class BridgeImpl_ecoStudent_studentS implements EcoStudent.StudentS.Requires {
        public final IKnowledge knowledge() {
          return EcoAgents.StudentAgent.ComponentImpl.this.knowledgeS().knowledge();
        }
      }
      
      public final EcoStudent.StudentS.Component studentS() {
        return this.studentS;
      }
    }
    
    /**
     * Used to check that two components are not created from the same implementation,
     * that the component has been started to call requires(), provides() and parts()
     * and that the component is not started by hand.
     * 
     */
    private boolean init = false;;
    
    /**
     * Used to check that the component is not started by hand.
     * 
     */
    private boolean started = false;;
    
    private EcoAgents.StudentAgent.ComponentImpl selfComponent;
    
    /**
     * Can be overridden by the implementation.
     * It will be called automatically after the component has been instantiated.
     * 
     */
    protected void start() {
      if (!this.init || this.started) {
      	throw new RuntimeException("start() should not be called by hand: to create a new component, use newComponent().");
      }
    }
    
    /**
     * This can be called by the implementation to access the provided ports.
     * 
     */
    protected EcoAgents.StudentAgent.Provides provides() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoAgents.StudentAgent.Requires requires() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("requires() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if requires() is needed to initialise the component.");
      }
      return this.selfComponent.bridge;
    }
    
    /**
     * This can be called by the implementation to access the parts and their provided ports.
     * 
     */
    protected EcoAgents.StudentAgent.Parts parts() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    private EcoKnowledge.KnowledgeS use_knowledgeS;
    
    private EcoStudent.StudentS use_studentS;
    
    /**
     * Not meant to be used to manually instantiate components (except for testing).
     * 
     */
    public synchronized EcoAgents.StudentAgent.Component _newComponent(final EcoAgents.StudentAgent.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of StudentAgent has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoAgents.StudentAgent.ComponentImpl  _comp = new EcoAgents.StudentAgent.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoAgents.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoAgents.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoAgents.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoAgents.Parts eco_parts() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
  }
  
  /**
   * Used to check that two components are not created from the same implementation,
   * that the component has been started to call requires(), provides() and parts()
   * and that the component is not started by hand.
   * 
   */
  private boolean init = false;;
  
  /**
   * Used to check that the component is not started by hand.
   * 
   */
  private boolean started = false;;
  
  private EcoAgents.ComponentImpl selfComponent;
  
  /**
   * Can be overridden by the implementation.
   * It will be called automatically after the component has been instantiated.
   * 
   */
  protected void start() {
    if (!this.init || this.started) {
    	throw new RuntimeException("start() should not be called by hand: to create a new component, use newComponent().");
    }
  }
  
  /**
   * This can be called by the implementation to access the provided ports.
   * 
   */
  protected EcoAgents.Provides provides() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
    }
    return this.selfComponent;
  }
  
  /**
   * This should be overridden by the implementation to define the provided port.
   * This will be called once during the construction of the component to initialize the port.
   * 
   */
  protected abstract ICreateAgent make_createAgent();
  
  /**
   * This can be called by the implementation to access the required ports.
   * 
   */
  protected EcoAgents.Requires requires() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("requires() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if requires() is needed to initialise the component.");
    }
    return this.selfComponent.bridge;
  }
  
  /**
   * This can be called by the implementation to access the parts and their provided ports.
   * 
   */
  protected EcoAgents.Parts parts() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
    }
    return this.selfComponent;
  }
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoKnowledge make_ecoKnowledge();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoTeacher make_ecoTeacher();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoStudent make_ecoStudent();
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized EcoAgents.Component _newComponent(final EcoAgents.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoAgents has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoAgents.ComponentImpl  _comp = new EcoAgents.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected EcoAgents.TeacherAgent make_TeacherAgent(final String id) {
    return new EcoAgents.TeacherAgent();
  }
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoAgents.TeacherAgent _createImplementationOfTeacherAgent(final String id) {
    EcoAgents.TeacherAgent implem = make_TeacherAgent(id);
    if (implem == null) {
    	throw new RuntimeException("make_TeacherAgent() in sma.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    assert this.selfComponent.implem_ecoKnowledge != null: "This is a bug.";
    assert implem.use_knowledgeS == null: "This is a bug.";
    implem.use_knowledgeS = this.selfComponent.implem_ecoKnowledge._createImplementationOfKnowledgeS(id);
    assert this.selfComponent.implem_ecoTeacher != null: "This is a bug.";
    assert implem.use_teacherS == null: "This is a bug.";
    implem.use_teacherS = this.selfComponent.implem_ecoTeacher._createImplementationOfTeacherS(id);
    return implem;
  }
  
  /**
   * This can be called to create an instance of the species from inside the implementation of the ecosystem.
   * 
   */
  protected EcoAgents.TeacherAgent.Component newTeacherAgent(final String id) {
    EcoAgents.TeacherAgent _implem = _createImplementationOfTeacherAgent(id);
    return _implem._newComponent(new EcoAgents.TeacherAgent.Requires() {},true);
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected EcoAgents.StudentAgent make_StudentAgent(final String id) {
    return new EcoAgents.StudentAgent();
  }
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoAgents.StudentAgent _createImplementationOfStudentAgent(final String id) {
    EcoAgents.StudentAgent implem = make_StudentAgent(id);
    if (implem == null) {
    	throw new RuntimeException("make_StudentAgent() in sma.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    assert this.selfComponent.implem_ecoKnowledge != null: "This is a bug.";
    assert implem.use_knowledgeS == null: "This is a bug.";
    implem.use_knowledgeS = this.selfComponent.implem_ecoKnowledge._createImplementationOfKnowledgeS(id);
    assert this.selfComponent.implem_ecoStudent != null: "This is a bug.";
    assert implem.use_studentS == null: "This is a bug.";
    implem.use_studentS = this.selfComponent.implem_ecoStudent._createImplementationOfStudentS(id);
    return implem;
  }
  
  /**
   * This can be called to create an instance of the species from inside the implementation of the ecosystem.
   * 
   */
  protected EcoAgents.StudentAgent.Component newStudentAgent(final String id) {
    EcoAgents.StudentAgent _implem = _createImplementationOfStudentAgent(id);
    return _implem._newComponent(new EcoAgents.StudentAgent.Requires() {},true);
  }
}
