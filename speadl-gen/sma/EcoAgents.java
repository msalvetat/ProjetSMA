package sma;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;

@SuppressWarnings("all")
public abstract class EcoAgents {
  public interface Requires {
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IEnvironment environmentService();
    
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IMessagingService agentMessaging();
    
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public ISchedulingService strategy();
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
  }
  
  public static class ComponentImpl implements EcoAgents.Component, EcoAgents.Parts {
    private final EcoAgents.Requires bridge;
    
    private final EcoAgents implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
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
  }
  
  public static abstract class Teacher {
    public interface Requires {
    }
    
    public interface Component extends EcoAgents.Teacher.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public ITwoStepsAgent agent();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoAgents.Teacher.Component, EcoAgents.Teacher.Parts {
      private final EcoAgents.Teacher.Requires bridge;
      
      private final EcoAgents.Teacher implementation;
      
      public void start() {
        this.implementation.start();
        this.implementation.started = true;
      }
      
      protected void initParts() {
        
      }
      
      private void init_agent() {
        assert this.agent == null: "This is a bug.";
        this.agent = this.implementation.make_agent();
        if (this.agent == null) {
        	throw new RuntimeException("make_agent() in sma.EcoAgents$Teacher should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_agent();
      }
      
      public ComponentImpl(final EcoAgents.Teacher implem, final EcoAgents.Teacher.Requires b, final boolean doInits) {
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
      
      private ITwoStepsAgent agent;
      
      public ITwoStepsAgent agent() {
        return this.agent;
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
    
    private EcoAgents.Teacher.ComponentImpl selfComponent;
    
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
    protected EcoAgents.Teacher.Provides provides() {
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
    protected abstract ITwoStepsAgent make_agent();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoAgents.Teacher.Requires requires() {
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
    protected EcoAgents.Teacher.Parts parts() {
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
    public synchronized EcoAgents.Teacher.Component _newComponent(final EcoAgents.Teacher.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of Teacher has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoAgents.Teacher.ComponentImpl  _comp = new EcoAgents.Teacher.ComponentImpl(this, b, true);
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
  
  public static abstract class Student {
    public interface Requires {
    }
    
    public interface Component extends EcoAgents.Student.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public ITwoStepsAgent agent();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoAgents.Student.Component, EcoAgents.Student.Parts {
      private final EcoAgents.Student.Requires bridge;
      
      private final EcoAgents.Student implementation;
      
      public void start() {
        this.implementation.start();
        this.implementation.started = true;
      }
      
      protected void initParts() {
        
      }
      
      private void init_agent() {
        assert this.agent == null: "This is a bug.";
        this.agent = this.implementation.make_agent();
        if (this.agent == null) {
        	throw new RuntimeException("make_agent() in sma.EcoAgents$Student should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_agent();
      }
      
      public ComponentImpl(final EcoAgents.Student implem, final EcoAgents.Student.Requires b, final boolean doInits) {
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
      
      private ITwoStepsAgent agent;
      
      public ITwoStepsAgent agent() {
        return this.agent;
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
    
    private EcoAgents.Student.ComponentImpl selfComponent;
    
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
    protected EcoAgents.Student.Provides provides() {
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
    protected abstract ITwoStepsAgent make_agent();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoAgents.Student.Requires requires() {
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
    protected EcoAgents.Student.Parts parts() {
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
    public synchronized EcoAgents.Student.Component _newComponent(final EcoAgents.Student.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of Student has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoAgents.Student.ComponentImpl  _comp = new EcoAgents.Student.ComponentImpl(this, b, true);
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
  protected abstract EcoAgents.Teacher make_Teacher(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoAgents.Teacher _createImplementationOfTeacher(final String id) {
    EcoAgents.Teacher implem = make_Teacher(id);
    if (implem == null) {
    	throw new RuntimeException("make_Teacher() in sma.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    return implem;
  }
  
  /**
   * This can be called to create an instance of the species from inside the implementation of the ecosystem.
   * 
   */
  protected EcoAgents.Teacher.Component newTeacher(final String id) {
    EcoAgents.Teacher _implem = _createImplementationOfTeacher(id);
    return _implem._newComponent(new EcoAgents.Teacher.Requires() {},true);
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoAgents.Student make_Student(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoAgents.Student _createImplementationOfStudent(final String id) {
    EcoAgents.Student implem = make_Student(id);
    if (implem == null) {
    	throw new RuntimeException("make_Student() in sma.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    return implem;
  }
  
  /**
   * This can be called to create an instance of the species from inside the implementation of the ecosystem.
   * 
   */
  protected EcoAgents.Student.Component newStudent(final String id) {
    EcoAgents.Student _implem = _createImplementationOfStudent(id);
    return _implem._newComponent(new EcoAgents.Student.Requires() {},true);
  }
}
