package sma.ecoKnowledgeRoom;

import m2dl.jlm.projetsma.agent.impl.room.knowledge.IKnowledgeRoom;

@SuppressWarnings("all")
public abstract class EcoKnowledgeRoom {
  public interface Requires {
  }
  
  public interface Component extends EcoKnowledgeRoom.Provides {
  }
  
  public interface Provides {
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements EcoKnowledgeRoom.Component, EcoKnowledgeRoom.Parts {
    private final EcoKnowledgeRoom.Requires bridge;
    
    private final EcoKnowledgeRoom implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final EcoKnowledgeRoom implem, final EcoKnowledgeRoom.Requires b, final boolean doInits) {
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
  
  public static abstract class KnowledgeRoomS {
    public interface Requires {
    }
    
    public interface Component extends EcoKnowledgeRoom.KnowledgeRoomS.Provides {
    }
    
    public interface Provides {
      /**
       * This can be called to access the provided port.
       * 
       */
      public IKnowledgeRoom knowledge();
    }
    
    public interface Parts {
    }
    
    public static class ComponentImpl implements EcoKnowledgeRoom.KnowledgeRoomS.Component, EcoKnowledgeRoom.KnowledgeRoomS.Parts {
      private final EcoKnowledgeRoom.KnowledgeRoomS.Requires bridge;
      
      private final EcoKnowledgeRoom.KnowledgeRoomS implementation;
      
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
        	throw new RuntimeException("make_knowledge() in sma.ecoKnowledgeRoom.EcoKnowledgeRoom$KnowledgeRoomS should not return null.");
        }
      }
      
      protected void initProvidedPorts() {
        init_knowledge();
      }
      
      public ComponentImpl(final EcoKnowledgeRoom.KnowledgeRoomS implem, final EcoKnowledgeRoom.KnowledgeRoomS.Requires b, final boolean doInits) {
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
      
      private IKnowledgeRoom knowledge;
      
      public IKnowledgeRoom knowledge() {
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
    
    private EcoKnowledgeRoom.KnowledgeRoomS.ComponentImpl selfComponent;
    
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
    protected EcoKnowledgeRoom.KnowledgeRoomS.Provides provides() {
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
    protected abstract IKnowledgeRoom make_knowledge();
    
    /**
     * This can be called by the implementation to access the required ports.
     * 
     */
    protected EcoKnowledgeRoom.KnowledgeRoomS.Requires requires() {
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
    protected EcoKnowledgeRoom.KnowledgeRoomS.Parts parts() {
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
    public synchronized EcoKnowledgeRoom.KnowledgeRoomS.Component _newComponent(final EcoKnowledgeRoom.KnowledgeRoomS.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of KnowledgeRoomS has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoKnowledgeRoom.KnowledgeRoomS.ComponentImpl  _comp = new EcoKnowledgeRoom.KnowledgeRoomS.ComponentImpl(this, b, true);
      if (start) {
      	_comp.start();
      }
      return _comp;
    }
    
    private EcoKnowledgeRoom.ComponentImpl ecosystemComponent;
    
    /**
     * This can be called by the species implementation to access the provided ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeRoom.Provides eco_provides() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent;
    }
    
    /**
     * This can be called by the species implementation to access the required ports of its ecosystem.
     * 
     */
    protected EcoKnowledgeRoom.Requires eco_requires() {
      assert this.ecosystemComponent != null: "This is a bug.";
      return this.ecosystemComponent.bridge;
    }
    
    /**
     * This can be called by the species implementation to access the parts of its ecosystem and their provided ports.
     * 
     */
    protected EcoKnowledgeRoom.Parts eco_parts() {
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
  
  private EcoKnowledgeRoom.ComponentImpl selfComponent;
  
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
  protected EcoKnowledgeRoom.Provides provides() {
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
  protected EcoKnowledgeRoom.Requires requires() {
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
  protected EcoKnowledgeRoom.Parts parts() {
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
  public synchronized EcoKnowledgeRoom.Component _newComponent(final EcoKnowledgeRoom.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of EcoKnowledgeRoom has already been used to create a component, use another one.");
    }
    this.init = true;
    EcoKnowledgeRoom.ComponentImpl  _comp = new EcoKnowledgeRoom.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected abstract EcoKnowledgeRoom.KnowledgeRoomS make_KnowledgeRoomS(final String id);
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoKnowledgeRoom.KnowledgeRoomS _createImplementationOfKnowledgeRoomS(final String id) {
    EcoKnowledgeRoom.KnowledgeRoomS implem = make_KnowledgeRoomS(id);
    if (implem == null) {
    	throw new RuntimeException("make_KnowledgeRoomS() in sma.ecoKnowledgeRoom.EcoKnowledgeRoom should not return null.");
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
  protected EcoKnowledgeRoom.KnowledgeRoomS.Component newKnowledgeRoomS(final String id) {
    EcoKnowledgeRoom.KnowledgeRoomS _implem = _createImplementationOfKnowledgeRoomS(id);
    return _implem._newComponent(new EcoKnowledgeRoom.KnowledgeRoomS.Requires() {},true);
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public EcoKnowledgeRoom.Component newComponent() {
    return this._newComponent(new EcoKnowledgeRoom.Requires() {}, true);
  }
}
