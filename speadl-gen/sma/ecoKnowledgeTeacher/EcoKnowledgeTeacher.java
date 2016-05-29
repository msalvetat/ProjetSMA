package sma.ecoKnowledgeTeacher;

import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.IKnowledgeTeacher;

@SuppressWarnings("all")
public abstract class EcoKnowledgeTeacher {
  public interface Requires {
  }
  
  public interface Component extends EcoKnowledgeTeacher.Provides {
  }
  
  public interface Provides {
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements EcoKnowledgeTeacher.Component, EcoKnowledgeTeacher.Parts {
    private final EcoKnowledgeTeacher.Requires bridge;
    
    private final EcoKnowledgeTeacher implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final EcoKnowledgeTeacher implem, final EcoKnowledgeTeacher.Requires b, final boolean doInits) {
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
  
  public static abstract class KnowledgeTeacherS {
    public interface Requires {
    }
    
    public interface Component extends EcoKnowledgeTeacher.KnowledgeTeacherS.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public IKnowledgeTeacher knowledge();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoKnowledgeTeacher.KnowledgeTeacherS.Component, EcoKnowledgeTeacher.KnowledgeTeacherS.Parts {
      private final EcoKnowledgeTeacher.KnowledgeTeacherS.Requires bridge;
      
      private final EcoKnowledgeTeacher.KnowledgeTeacherS implementation;
      
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
        	throw new RuntimeException("make_knowledge() in sma.ecoKnowledgeTeacher.EcoKnowledgeTeacher$KnowledgeTeacherS should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_knowledge();
      }
      
      public ComponentImpl(final EcoKnowledgeTeacher.KnowledgeTeacherS implem, final EcoKnowledgeTeacher.KnowledgeTeacherS.Requires b, final boolean doInits) {
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
      
      private IKnowledgeTeacher knowledge;
      
      public IKnowledgeTeacher knowledge() {
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
    
    private EcoKnowledgeTeacher.KnowledgeTeacherS.ComponentImpl selfComponent;
    
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
    protected EcoKnowledgeTeacher.KnowledgeTeacherS.Provides provides() {
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
    protected abstract IKnowledgeTeacher make_knowledge();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoKnowledgeTeacher.KnowledgeTeacherS.Requires requires() {
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
    protected EcoKnowledgeTeacher.KnowledgeTeacherS.Parts parts() {
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
    public synchronized EcoKnowledgeTeacher.KnowledgeTeacherS.Component _newComponent(final EcoKnowledgeTeacher.KnowledgeTeacherS.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of KnowledgeTeacherS has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoKnowledgeTeacher.KnowledgeTeacherS.ComponentImpl  _comp = new EcoKnowledgeTeacher.KnowledgeTeacherS.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoKnowledgeTeacher.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeTeacher.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeTeacher.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoKnowledgeTeacher.Parts eco_parts() {
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
  
  private EcoKnowledgeTeacher.ComponentImpl selfComponent;
  
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
  protected EcoKnowledgeTeacher.Provides provides() {
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
  protected EcoKnowledgeTeacher.Requires requires() {
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
  protected EcoKnowledgeTeacher.Parts parts() {
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
  public synchronized EcoKnowledgeTeacher.Component _newComponent(final EcoKnowledgeTeacher.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoKnowledgeTeacher has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoKnowledgeTeacher.ComponentImpl  _comp = new EcoKnowledgeTeacher.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoKnowledgeTeacher.KnowledgeTeacherS make_KnowledgeTeacherS(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoKnowledgeTeacher.KnowledgeTeacherS _createImplementationOfKnowledgeTeacherS(final String id) {
    EcoKnowledgeTeacher.KnowledgeTeacherS implem = make_KnowledgeTeacherS(id);
    if (implem == null) {
    	throw new RuntimeException("make_KnowledgeTeacherS() in sma.ecoKnowledgeTeacher.EcoKnowledgeTeacher should not return null.");
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
  protected EcoKnowledgeTeacher.KnowledgeTeacherS.Component newKnowledgeTeacherS(final String id) {
    EcoKnowledgeTeacher.KnowledgeTeacherS _implem = _createImplementationOfKnowledgeTeacherS(id);
    return _implem._newComponent(new EcoKnowledgeTeacher.KnowledgeTeacherS.Requires() {},true);
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public EcoKnowledgeTeacher.Component newComponent() {
    return this._newComponent(new EcoKnowledgeTeacher.Requires() {}, true);
  }
}
