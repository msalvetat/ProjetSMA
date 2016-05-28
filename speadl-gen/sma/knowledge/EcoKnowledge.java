package sma.knowledge;

import m2dl.jlm.projetsma.agent.IKnowledge;
import m2dl.jlm.projetsma.environment.IEnvironment;

@SuppressWarnings("all")
public abstract class EcoKnowledge {
  public interface Requires {
    /**
     * This can be called by the implementation to access this required port.
     * 
     */
    public IEnvironment environment();
  }
  
  public interface Component extends EcoKnowledge.Provides {
  }
  
  public interface Provides {
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements EcoKnowledge.Component, EcoKnowledge.Parts {
    private final EcoKnowledge.Requires bridge;
    
    private final EcoKnowledge implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final EcoKnowledge implem, final EcoKnowledge.Requires b, final boolean doInits) {
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
  
  public static abstract class KnowledgeS {
    public interface Requires {
    }
    
    public interface Component extends EcoKnowledge.KnowledgeS.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public IKnowledge knowledge();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoKnowledge.KnowledgeS.Component, EcoKnowledge.KnowledgeS.Parts {
      private final EcoKnowledge.KnowledgeS.Requires bridge;
      
      private final EcoKnowledge.KnowledgeS implementation;
      
      public void start() {
        this.implementation.start();
        this.implementation.started = true;
      }
      
      protected void initParts() {
        
      }
      
      private void init_knowledge() {
        assert this.knowledge == null: "This is a bug.";
        this.knowledge = this.implementation.make_knowledge();
        if (this.knowledge == null) {
        	throw new RuntimeException("make_knowledge() in sma.knowledge.EcoKnowledge$KnowledgeS should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_knowledge();
      }
      
      public ComponentImpl(final EcoKnowledge.KnowledgeS implem, final EcoKnowledge.KnowledgeS.Requires b, final boolean doInits) {
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
      
      private IKnowledge knowledge;
      
      public IKnowledge knowledge() {
        return this.knowledge;
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
    
    private EcoKnowledge.KnowledgeS.ComponentImpl selfComponent;
    
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
    protected EcoKnowledge.KnowledgeS.Provides provides() {
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
    protected abstract IKnowledge make_knowledge();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoKnowledge.KnowledgeS.Requires requires() {
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
    protected EcoKnowledge.KnowledgeS.Parts parts() {
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
    public synchronized EcoKnowledge.KnowledgeS.Component _newComponent(final EcoKnowledge.KnowledgeS.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of KnowledgeS has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoKnowledge.KnowledgeS.ComponentImpl  _comp = new EcoKnowledge.KnowledgeS.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoKnowledge.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoKnowledge.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoKnowledge.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoKnowledge.Parts eco_parts() {
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
  
  private EcoKnowledge.ComponentImpl selfComponent;
  
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
  protected EcoKnowledge.Provides provides() {
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
  protected EcoKnowledge.Requires requires() {
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
  protected EcoKnowledge.Parts parts() {
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
  public synchronized EcoKnowledge.Component _newComponent(final EcoKnowledge.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoKnowledge has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoKnowledge.ComponentImpl  _comp = new EcoKnowledge.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoKnowledge.KnowledgeS make_KnowledgeS(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoKnowledge.KnowledgeS _createImplementationOfKnowledgeS(final String id) {
    EcoKnowledge.KnowledgeS implem = make_KnowledgeS(id);
    if (implem == null) {
    	throw new RuntimeException("make_KnowledgeS() in sma.knowledge.EcoKnowledge should not return null.");
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
  protected EcoKnowledge.KnowledgeS.Component newKnowledgeS(final String id) {
    EcoKnowledge.KnowledgeS _implem = _createImplementationOfKnowledgeS(id);
    return _implem._newComponent(new EcoKnowledge.KnowledgeS.Requires() {},true);
  }
}
