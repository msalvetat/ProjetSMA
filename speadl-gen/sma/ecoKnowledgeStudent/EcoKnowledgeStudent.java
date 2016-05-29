package sma.ecoKnowledgeStudent;

import m2dl.jlm.projetsma.agent.impl.student.knowledge.IKnowledgeStudent;

@SuppressWarnings("all")
public abstract class EcoKnowledgeStudent {
  public interface Requires {
  }
  
  public interface Component extends EcoKnowledgeStudent.Provides {
  }
  
  public interface Provides {
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements EcoKnowledgeStudent.Component, EcoKnowledgeStudent.Parts {
    private final EcoKnowledgeStudent.Requires bridge;
    
    private final EcoKnowledgeStudent implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final EcoKnowledgeStudent implem, final EcoKnowledgeStudent.Requires b, final boolean doInits) {
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
  
  public static abstract class KnowledgeStudentS {
    public interface Requires {
    }
    
    public interface Component extends EcoKnowledgeStudent.KnowledgeStudentS.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public IKnowledgeStudent knowledge();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoKnowledgeStudent.KnowledgeStudentS.Component, EcoKnowledgeStudent.KnowledgeStudentS.Parts {
      private final EcoKnowledgeStudent.KnowledgeStudentS.Requires bridge;
      
      private final EcoKnowledgeStudent.KnowledgeStudentS implementation;
      
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
        	throw new RuntimeException("make_knowledge() in sma.ecoKnowledgeStudent.EcoKnowledgeStudent$KnowledgeStudentS should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_knowledge();
      }
      
      public ComponentImpl(final EcoKnowledgeStudent.KnowledgeStudentS implem, final EcoKnowledgeStudent.KnowledgeStudentS.Requires b, final boolean doInits) {
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
      
      private IKnowledgeStudent knowledge;
      
      public IKnowledgeStudent knowledge() {
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
    
    private EcoKnowledgeStudent.KnowledgeStudentS.ComponentImpl selfComponent;
    
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
    protected EcoKnowledgeStudent.KnowledgeStudentS.Provides provides() {
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
    protected abstract IKnowledgeStudent make_knowledge();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoKnowledgeStudent.KnowledgeStudentS.Requires requires() {
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
    protected EcoKnowledgeStudent.KnowledgeStudentS.Parts parts() {
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
    public synchronized EcoKnowledgeStudent.KnowledgeStudentS.Component _newComponent(final EcoKnowledgeStudent.KnowledgeStudentS.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of KnowledgeStudentS has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoKnowledgeStudent.KnowledgeStudentS.ComponentImpl  _comp = new EcoKnowledgeStudent.KnowledgeStudentS.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoKnowledgeStudent.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeStudent.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeStudent.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoKnowledgeStudent.Parts eco_parts() {
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
  
  private EcoKnowledgeStudent.ComponentImpl selfComponent;
  
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
  protected EcoKnowledgeStudent.Provides provides() {
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
  protected EcoKnowledgeStudent.Requires requires() {
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
  protected EcoKnowledgeStudent.Parts parts() {
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
  public synchronized EcoKnowledgeStudent.Component _newComponent(final EcoKnowledgeStudent.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoKnowledgeStudent has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoKnowledgeStudent.ComponentImpl  _comp = new EcoKnowledgeStudent.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoKnowledgeStudent.KnowledgeStudentS make_KnowledgeStudentS(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoKnowledgeStudent.KnowledgeStudentS _createImplementationOfKnowledgeStudentS(final String id) {
    EcoKnowledgeStudent.KnowledgeStudentS implem = make_KnowledgeStudentS(id);
    if (implem == null) {
    	throw new RuntimeException("make_KnowledgeStudentS() in sma.ecoKnowledgeStudent.EcoKnowledgeStudent should not return null.");
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
  protected EcoKnowledgeStudent.KnowledgeStudentS.Component newKnowledgeStudentS(final String id) {
    EcoKnowledgeStudent.KnowledgeStudentS _implem = _createImplementationOfKnowledgeStudentS(id);
    return _implem._newComponent(new EcoKnowledgeStudent.KnowledgeStudentS.Requires() {},true);
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public EcoKnowledgeStudent.Component newComponent() {
    return this._newComponent(new EcoKnowledgeStudent.Requires() {}, true);
  }
}
