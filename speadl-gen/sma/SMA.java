package sma;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;
import sma.EcoAgents;
import sma.Environment;
import sma.Services;

@SuppressWarnings("all")
public abstract class SMA {
  public interface Requires {
  }
  
  public interface Component extends SMA.Provides {
  }
  
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public ISchedulingService strategyService();
    
    /**
     * This can be called to access the provided port.
     * 
     */
    public IEnvironment environmentService();
    
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
    public Environment.Component environment();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoAgents.Component agents();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public Services.Component services();
  }
  
  public static class ComponentImpl implements SMA.Component, SMA.Parts {
    private final SMA.Requires bridge;
    
    private final SMA implementation;
    
    public void start() {
      assert this.environment != null: "This is a bug.";
      ((Environment.ComponentImpl) this.environment).start();
      assert this.agents != null: "This is a bug.";
      ((EcoAgents.ComponentImpl) this.agents).start();
      assert this.services != null: "This is a bug.";
      ((Services.ComponentImpl) this.services).start();
      this.implementation.start();
      this.implementation.started = true;
    }
    
    private void init_environment() {
      assert this.environment == null: "This is a bug.";
      assert this.implem_environment == null: "This is a bug.";
      this.implem_environment = this.implementation.make_environment();
      if (this.implem_environment == null) {
      	throw new RuntimeException("make_environment() in sma.SMA should not return null.");
      }
      this.environment = this.implem_environment._newComponent(new BridgeImpl_environment(), false);
      
    }
    
    private void init_agents() {
      assert this.agents == null: "This is a bug.";
      assert this.implem_agents == null: "This is a bug.";
      this.implem_agents = this.implementation.make_agents();
      if (this.implem_agents == null) {
      	throw new RuntimeException("make_agents() in sma.SMA should not return null.");
      }
      this.agents = this.implem_agents._newComponent(new BridgeImpl_agents(), false);
      
    }
    
    private void init_services() {
      assert this.services == null: "This is a bug.";
      assert this.implem_services == null: "This is a bug.";
      this.implem_services = this.implementation.make_services();
      if (this.implem_services == null) {
      	throw new RuntimeException("make_services() in sma.SMA should not return null.");
      }
      this.services = this.implem_services._newComponent(new BridgeImpl_services(), false);
      
    }
    
    protected void initParts() {
      init_environment();
      init_agents();
      init_services();
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final SMA implem, final SMA.Requires b, final boolean doInits) {
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
    
    public ISchedulingService strategyService() {
      return this.services().systemStrategy();
    }
    
    public IEnvironment environmentService() {
      return this.environment().environment();
    }
    
    public ICreateAgent createAgent() {
      return this.agents().createAgent();
    }
    
    private Environment.Component environment;
    
    private Environment implem_environment;
    
    private final class BridgeImpl_environment implements Environment.Requires {
    }
    
    public final Environment.Component environment() {
      return this.environment;
    }
    
    private EcoAgents.Component agents;
    
    private EcoAgents implem_agents;
    
    private final class BridgeImpl_agents implements EcoAgents.Requires {
      public final IEnvironment environment() {
        return SMA.ComponentImpl.this.environment().environment();
      }
      
      public final IMessagingService messagingService() {
        return SMA.ComponentImpl.this.services().agentMessaging();
      }
      
      public final ISchedulingService strategyService() {
        return SMA.ComponentImpl.this.services().systemStrategy();
      }
    }
    
    public final EcoAgents.Component agents() {
      return this.agents;
    }
    
    private Services.Component services;
    
    private Services implem_services;
    
    private final class BridgeImpl_services implements Services.Requires {
    }
    
    public final Services.Component services() {
      return this.services;
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
  
  private SMA.ComponentImpl selfComponent;
  
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
  protected SMA.Provides provides() {
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
  protected SMA.Requires requires() {
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
  protected SMA.Parts parts() {
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
  protected abstract Environment make_environment();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoAgents make_agents();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract Services make_services();
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized SMA.Component _newComponent(final SMA.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of SMA has already been used to create a component, use another one.");
    }
    this.init = true;
    SMA.ComponentImpl  _comp = new SMA.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public SMA.Component newComponent() {
    return this._newComponent(new SMA.Requires() {}, true);
  }
}
