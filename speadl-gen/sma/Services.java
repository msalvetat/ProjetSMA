package sma;

import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;

@SuppressWarnings("all")
public abstract class Services {
  public interface Requires {
  }
  
  public interface Component extends Services.Provides {
  }
  
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public ISchedulingService systemStrategy();
    
    /**
     * This can be called to access the provided port.
     * 
     */
    public IMessagingService agentMessaging();
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements Services.Component, Services.Parts {
    private final Services.Requires bridge;
    
    private final Services implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    private void init_systemStrategy() {
      assert this.systemStrategy == null: "This is a bug.";
      this.systemStrategy = this.implementation.make_systemStrategy();
      if (this.systemStrategy == null) {
      	throw new RuntimeException("make_systemStrategy() in sma.Services should not return null.");
      }
    }
    
    private void init_agentMessaging() {
      assert this.agentMessaging == null: "This is a bug.";
      this.agentMessaging = this.implementation.make_agentMessaging();
      if (this.agentMessaging == null) {
      	throw new RuntimeException("make_agentMessaging() in sma.Services should not return null.");
      }
    }
    
    protected void initProvidedPorts() {
      init_systemStrategy();
      init_agentMessaging();
    }
    
    public ComponentImpl(final Services implem, final Services.Requires b, final boolean doInits) {
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
    
    private ISchedulingService systemStrategy;
    
    public ISchedulingService systemStrategy() {
      return this.systemStrategy;
    }
    
    private IMessagingService agentMessaging;
    
    public IMessagingService agentMessaging() {
      return this.agentMessaging;
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
  
  private Services.ComponentImpl selfComponent;
  
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
  protected Services.Provides provides() {
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
  protected abstract ISchedulingService make_systemStrategy();
  
  /**
   * This should be overridden by the implementation to define the provided port.
   * This will be called once during the construction of the component to initialize the port.
   * 
   */
  protected abstract IMessagingService make_agentMessaging();
  
  /**
   * This can be called by the implementation to access the required ports.
   * 
   */
  protected Services.Requires requires() {
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
  protected Services.Parts parts() {
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
  public synchronized Services.Component _newComponent(final Services.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of Services has already been used to create a component, use another one.");
    }
    this.init = true;
    Services.ComponentImpl  _comp = new Services.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public Services.Component newComponent() {
    return this._newComponent(new Services.Requires() {}, true);
  }
}
