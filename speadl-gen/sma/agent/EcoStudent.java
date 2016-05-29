package sma.agent;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.student.knowledge.IKnowledgeStudent;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;

@SuppressWarnings("all")
public abstract class EcoStudent {
  public interface Requires {
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public ISchedulingService strategyService();
    
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IMessagingService messagingService();
  }
  
  public interface Component extends EcoStudent.Provides {
  }
  
  public interface Provides {
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements EcoStudent.Component, EcoStudent.Parts {
    private final EcoStudent.Requires bridge;
    
    private final EcoStudent implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final EcoStudent implem, final EcoStudent.Requires b, final boolean doInits) {
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
  }
  
  public static abstract class StudentS {
    public interface Requires {
      /**
       * This can be called by the implementation to access this required port.
       * 
       */
      public IKnowledgeStudent knowledge();
    }
    
    public interface Component extends EcoStudent.StudentS.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public ITwoStepsAgent student();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoStudent.StudentS.Component, EcoStudent.StudentS.Parts {
      private final EcoStudent.StudentS.Requires bridge;
      
      private final EcoStudent.StudentS implementation;
      
      public void start() {
        this.implementation.start();
        this.implementation.started = true;
      }
      
      protected void initParts() {
        
      }
      
      private void init_student() {
        assert this.student == null: "This is a bug.";
        this.student = this.implementation.make_student();
        if (this.student == null) {
        	throw new RuntimeException("make_student() in sma.agent.EcoStudent$StudentS should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_student();
      }
      
      public ComponentImpl(final EcoStudent.StudentS implem, final EcoStudent.StudentS.Requires b, final boolean doInits) {
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
      
      private ITwoStepsAgent student;
      
      public ITwoStepsAgent student() {
        return this.student;
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
    
    private EcoStudent.StudentS.ComponentImpl selfComponent;
    
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
    protected EcoStudent.StudentS.Provides provides() {
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
    protected abstract ITwoStepsAgent make_student();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoStudent.StudentS.Requires requires() {
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
    protected EcoStudent.StudentS.Parts parts() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    /**
     * Not meant to be used to manually instantiate components (except for testing).
     * 
     */
    public synchronized EcoStudent.StudentS.Component _newComponent(final EcoStudent.StudentS.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of StudentS has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoStudent.StudentS.ComponentImpl  _comp = new EcoStudent.StudentS.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoStudent.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoStudent.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoStudent.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoStudent.Parts eco_parts() {
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
  
  private EcoStudent.ComponentImpl selfComponent;
  
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
  protected EcoStudent.Provides provides() {
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
  protected EcoStudent.Requires requires() {
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
  protected EcoStudent.Parts parts() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
    }
    return this.selfComponent;
  }
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized EcoStudent.Component _newComponent(final EcoStudent.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoStudent has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoStudent.ComponentImpl  _comp = new EcoStudent.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoStudent.StudentS make_StudentS(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoStudent.StudentS _createImplementationOfStudentS(final String id) {
    EcoStudent.StudentS implem = make_StudentS(id);
    if (implem == null) {
    	throw new RuntimeException("make_StudentS() in sma.agent.EcoStudent should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    return implem;
  }
}
