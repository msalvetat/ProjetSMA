package sma.ecoAgents;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.agent.room.knowledge.IKnowledgeRoom;
import m2dl.jlm.projetsma.agent.student.knowledge.IKnowledgeStudent;
import m2dl.jlm.projetsma.agent.teacher.knowledge.IKnowledgeTeacher;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;
import sma.agent.EcoRoom;
import sma.agent.EcoStudent;
import sma.agent.EcoTeacher;
import sma.ecoKnowledgeRoom.EcoKnowledgeRoom;
import sma.ecoKnowledgeStudent.EcoKnowledgeStudent;
import sma.ecoKnowledgeTeacher.EcoKnowledgeTeacher;

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
    public EcoKnowledgeRoom.Component ecoKnowledgeRoom();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoKnowledgeTeacher.Component ecoKnowledgeTeacher();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoKnowledgeStudent.Component ecoKnowledgeStudent();
    
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
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public EcoRoom.Component ecoRoom();
  }
  
  public static class ComponentImpl implements EcoAgents.Component, EcoAgents.Parts {
    private final EcoAgents.Requires bridge;
    
    private final EcoAgents implementation;
    
    public void start() {
      assert this.ecoKnowledgeRoom != null: "This is a bug.";
      ((EcoKnowledgeRoom.ComponentImpl) this.ecoKnowledgeRoom).start();
      assert this.ecoKnowledgeTeacher != null: "This is a bug.";
      ((EcoKnowledgeTeacher.ComponentImpl) this.ecoKnowledgeTeacher).start();
      assert this.ecoKnowledgeStudent != null: "This is a bug.";
      ((EcoKnowledgeStudent.ComponentImpl) this.ecoKnowledgeStudent).start();
      assert this.ecoTeacher != null: "This is a bug.";
      ((EcoTeacher.ComponentImpl) this.ecoTeacher).start();
      assert this.ecoStudent != null: "This is a bug.";
      ((EcoStudent.ComponentImpl) this.ecoStudent).start();
      assert this.ecoRoom != null: "This is a bug.";
      ((EcoRoom.ComponentImpl) this.ecoRoom).start();
      this.implementation.start();
      this.implementation.started = true;
    }
    
    private void init_ecoKnowledgeRoom() {
      assert this.ecoKnowledgeRoom == null: "This is a bug.";
      assert this.implem_ecoKnowledgeRoom == null: "This is a bug.";
      this.implem_ecoKnowledgeRoom = this.implementation.make_ecoKnowledgeRoom();
      if (this.implem_ecoKnowledgeRoom == null) {
      	throw new RuntimeException("make_ecoKnowledgeRoom() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoKnowledgeRoom = this.implem_ecoKnowledgeRoom._newComponent(new BridgeImpl_ecoKnowledgeRoom(), false);
      
    }
    
    private void init_ecoKnowledgeTeacher() {
      assert this.ecoKnowledgeTeacher == null: "This is a bug.";
      assert this.implem_ecoKnowledgeTeacher == null: "This is a bug.";
      this.implem_ecoKnowledgeTeacher = this.implementation.make_ecoKnowledgeTeacher();
      if (this.implem_ecoKnowledgeTeacher == null) {
      	throw new RuntimeException("make_ecoKnowledgeTeacher() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoKnowledgeTeacher = this.implem_ecoKnowledgeTeacher._newComponent(new BridgeImpl_ecoKnowledgeTeacher(), false);
      
    }
    
    private void init_ecoKnowledgeStudent() {
      assert this.ecoKnowledgeStudent == null: "This is a bug.";
      assert this.implem_ecoKnowledgeStudent == null: "This is a bug.";
      this.implem_ecoKnowledgeStudent = this.implementation.make_ecoKnowledgeStudent();
      if (this.implem_ecoKnowledgeStudent == null) {
      	throw new RuntimeException("make_ecoKnowledgeStudent() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoKnowledgeStudent = this.implem_ecoKnowledgeStudent._newComponent(new BridgeImpl_ecoKnowledgeStudent(), false);
      
    }
    
    private void init_ecoTeacher() {
      assert this.ecoTeacher == null: "This is a bug.";
      assert this.implem_ecoTeacher == null: "This is a bug.";
      this.implem_ecoTeacher = this.implementation.make_ecoTeacher();
      if (this.implem_ecoTeacher == null) {
      	throw new RuntimeException("make_ecoTeacher() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoTeacher = this.implem_ecoTeacher._newComponent(new BridgeImpl_ecoTeacher(), false);
      
    }
    
    private void init_ecoStudent() {
      assert this.ecoStudent == null: "This is a bug.";
      assert this.implem_ecoStudent == null: "This is a bug.";
      this.implem_ecoStudent = this.implementation.make_ecoStudent();
      if (this.implem_ecoStudent == null) {
      	throw new RuntimeException("make_ecoStudent() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoStudent = this.implem_ecoStudent._newComponent(new BridgeImpl_ecoStudent(), false);
      
    }
    
    private void init_ecoRoom() {
      assert this.ecoRoom == null: "This is a bug.";
      assert this.implem_ecoRoom == null: "This is a bug.";
      this.implem_ecoRoom = this.implementation.make_ecoRoom();
      if (this.implem_ecoRoom == null) {
      	throw new RuntimeException("make_ecoRoom() in sma.ecoAgents.EcoAgents should not return null.");
      }
      this.ecoRoom = this.implem_ecoRoom._newComponent(new BridgeImpl_ecoRoom(), false);
      
    }
    
    protected void initParts() {
      init_ecoKnowledgeRoom();
      init_ecoKnowledgeTeacher();
      init_ecoKnowledgeStudent();
      init_ecoTeacher();
      init_ecoStudent();
      init_ecoRoom();
    }
    
    private void init_createAgent() {
      assert this.createAgent == null: "This is a bug.";
      this.createAgent = this.implementation.make_createAgent();
      if (this.createAgent == null) {
      	throw new RuntimeException("make_createAgent() in sma.ecoAgents.EcoAgents should not return null.");
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
    
    private EcoKnowledgeRoom.Component ecoKnowledgeRoom;
    
    private EcoKnowledgeRoom implem_ecoKnowledgeRoom;
    
    private final class BridgeImpl_ecoKnowledgeRoom implements EcoKnowledgeRoom.Requires {
    }
    
    public final EcoKnowledgeRoom.Component ecoKnowledgeRoom() {
      return this.ecoKnowledgeRoom;
    }
    
    private EcoKnowledgeTeacher.Component ecoKnowledgeTeacher;
    
    private EcoKnowledgeTeacher implem_ecoKnowledgeTeacher;
    
    private final class BridgeImpl_ecoKnowledgeTeacher implements EcoKnowledgeTeacher.Requires {
    }
    
    public final EcoKnowledgeTeacher.Component ecoKnowledgeTeacher() {
      return this.ecoKnowledgeTeacher;
    }
    
    private EcoKnowledgeStudent.Component ecoKnowledgeStudent;
    
    private EcoKnowledgeStudent implem_ecoKnowledgeStudent;
    
    private final class BridgeImpl_ecoKnowledgeStudent implements EcoKnowledgeStudent.Requires {
    }
    
    public final EcoKnowledgeStudent.Component ecoKnowledgeStudent() {
      return this.ecoKnowledgeStudent;
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
    
    private EcoRoom.Component ecoRoom;
    
    private EcoRoom implem_ecoRoom;
    
    private final class BridgeImpl_ecoRoom implements EcoRoom.Requires {
      public final ISchedulingService strategyService() {
        return EcoAgents.ComponentImpl.this.bridge.strategyService();
      }
      
      public final IMessagingService messagingService() {
        return EcoAgents.ComponentImpl.this.bridge.messagingService();
      }
    }
    
    public final EcoRoom.Component ecoRoom() {
      return this.ecoRoom;
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
      public EcoKnowledgeTeacher.KnowledgeTeacherS.Component knowledgeS();
      
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
        ((EcoKnowledgeTeacher.KnowledgeTeacherS.ComponentImpl) this.knowledgeS).start();
        assert this.teacherS != null: "This is a bug.";
        ((EcoTeacher.TeacherS.ComponentImpl) this.teacherS).start();
        this.implementation.start();
        this.implementation.started = true;
      }
      
      private void init_knowledgeS() {
        assert this.knowledgeS == null: "This is a bug.";
        assert this.implementation.use_knowledgeS != null: "This is a bug.";
        this.knowledgeS = this.implementation.use_knowledgeS._newComponent(new BridgeImpl_ecoKnowledgeTeacher_knowledgeS(), false);
        
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
      
      private EcoKnowledgeTeacher.KnowledgeTeacherS.Component knowledgeS;
      
      private final class BridgeImpl_ecoKnowledgeTeacher_knowledgeS implements EcoKnowledgeTeacher.KnowledgeTeacherS.Requires {
      }
      
      public final EcoKnowledgeTeacher.KnowledgeTeacherS.Component knowledgeS() {
        return this.knowledgeS;
      }
      
      private EcoTeacher.TeacherS.Component teacherS;
      
      private final class BridgeImpl_ecoTeacher_teacherS implements EcoTeacher.TeacherS.Requires {
        public final IKnowledgeTeacher knowledge() {
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
    
    private EcoKnowledgeTeacher.KnowledgeTeacherS use_knowledgeS;
    
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
      public EcoKnowledgeStudent.KnowledgeStudentS.Component knowledgeS();
      
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
        ((EcoKnowledgeStudent.KnowledgeStudentS.ComponentImpl) this.knowledgeS).start();
        assert this.studentS != null: "This is a bug.";
        ((EcoStudent.StudentS.ComponentImpl) this.studentS).start();
        this.implementation.start();
        this.implementation.started = true;
      }
      
      private void init_knowledgeS() {
        assert this.knowledgeS == null: "This is a bug.";
        assert this.implementation.use_knowledgeS != null: "This is a bug.";
        this.knowledgeS = this.implementation.use_knowledgeS._newComponent(new BridgeImpl_ecoKnowledgeStudent_knowledgeS(), false);
        
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
      
      private EcoKnowledgeStudent.KnowledgeStudentS.Component knowledgeS;
      
      private final class BridgeImpl_ecoKnowledgeStudent_knowledgeS implements EcoKnowledgeStudent.KnowledgeStudentS.Requires {
      }
      
      public final EcoKnowledgeStudent.KnowledgeStudentS.Component knowledgeS() {
        return this.knowledgeS;
      }
      
      private EcoStudent.StudentS.Component studentS;
      
      private final class BridgeImpl_ecoStudent_studentS implements EcoStudent.StudentS.Requires {
        public final IKnowledgeStudent knowledge() {
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
    
    private EcoKnowledgeStudent.KnowledgeStudentS use_knowledgeS;
    
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
  
  public static class RoomAgent {
    public interface Requires {
    }
    
    public interface Component extends EcoAgents.RoomAgent.Provides {
    }
    
    public interface Provides {
    }
    
    public interface Parts {
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoKnowledgeRoom.KnowledgeRoomS.Component knowledgeS();
      
      /**
       * This can be called by the implementation to access the part and its provided ports.
       * It will be initialized after the required ports are initialized and before the provided ports are initialized.
       * 
       */
      public EcoRoom.RoomS.Component roomS();
    }
    
    public static class ComponentImpl implements EcoAgents.RoomAgent.Component, EcoAgents.RoomAgent.Parts {
      private final EcoAgents.RoomAgent.Requires bridge;
      
      private final EcoAgents.RoomAgent implementation;
      
      public void start() {
        assert this.knowledgeS != null: "This is a bug.";
        ((EcoKnowledgeRoom.KnowledgeRoomS.ComponentImpl) this.knowledgeS).start();
        assert this.roomS != null: "This is a bug.";
        ((EcoRoom.RoomS.ComponentImpl) this.roomS).start();
        this.implementation.start();
        this.implementation.started = true;
      }
      
      private void init_knowledgeS() {
        assert this.knowledgeS == null: "This is a bug.";
        assert this.implementation.use_knowledgeS != null: "This is a bug.";
        this.knowledgeS = this.implementation.use_knowledgeS._newComponent(new BridgeImpl_ecoKnowledgeRoom_knowledgeS(), false);
        
      }
      
      private void init_roomS() {
        assert this.roomS == null: "This is a bug.";
        assert this.implementation.use_roomS != null: "This is a bug.";
        this.roomS = this.implementation.use_roomS._newComponent(new BridgeImpl_ecoRoom_roomS(), false);
        
      }
      
      protected void initParts() {
        init_knowledgeS();
        init_roomS();
      }
      
      protected void initProvidedPorts() {
        
      }
      
      public ComponentImpl(final EcoAgents.RoomAgent implem, final EcoAgents.RoomAgent.Requires b, final boolean doInits) {
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
      
      private EcoKnowledgeRoom.KnowledgeRoomS.Component knowledgeS;
      
      private final class BridgeImpl_ecoKnowledgeRoom_knowledgeS implements EcoKnowledgeRoom.KnowledgeRoomS.Requires {
      }
      
      public final EcoKnowledgeRoom.KnowledgeRoomS.Component knowledgeS() {
        return this.knowledgeS;
      }
      
      private EcoRoom.RoomS.Component roomS;
      
      private final class BridgeImpl_ecoRoom_roomS implements EcoRoom.RoomS.Requires {
        public final IKnowledgeRoom knowledge() {
          return EcoAgents.RoomAgent.ComponentImpl.this.knowledgeS().knowledge();
        }
      }
      
      public final EcoRoom.RoomS.Component roomS() {
        return this.roomS;
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
    
    private EcoAgents.RoomAgent.ComponentImpl selfComponent;
    
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
    protected EcoAgents.RoomAgent.Provides provides() {
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
    protected EcoAgents.RoomAgent.Requires requires() {
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
    protected EcoAgents.RoomAgent.Parts parts() {
      assert this.selfComponent != null: "This is a bug.";
      if (!this.init) {
      	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
      }
      return this.selfComponent;
    }
    
    private EcoKnowledgeRoom.KnowledgeRoomS use_knowledgeS;
    
    private EcoRoom.RoomS use_roomS;
    
    /**
     * Not meant to be used to manually instantiate components (except for testing).
     * 
     */
    public synchronized EcoAgents.RoomAgent.Component _newComponent(final EcoAgents.RoomAgent.Requires b, final boolean start) {
      if (this.init) {
      	throw new RuntimeException("This instance of RoomAgent has already been used to create a component, use another one.");
      }
      this.init = true;
      EcoAgents.RoomAgent.ComponentImpl  _comp = new EcoAgents.RoomAgent.ComponentImpl(this, b, true);
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
  protected abstract EcoKnowledgeRoom make_ecoKnowledgeRoom();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoKnowledgeTeacher make_ecoKnowledgeTeacher();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoKnowledgeStudent make_ecoKnowledgeStudent();
  
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
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract EcoRoom make_ecoRoom();
  
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
    	throw new RuntimeException("make_TeacherAgent() in sma.ecoAgents.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    assert this.selfComponent.implem_ecoKnowledgeTeacher != null: "This is a bug.";
    assert implem.use_knowledgeS == null: "This is a bug.";
    implem.use_knowledgeS = this.selfComponent.implem_ecoKnowledgeTeacher._createImplementationOfKnowledgeTeacherS(id);
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
    	throw new RuntimeException("make_StudentAgent() in sma.ecoAgents.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    assert this.selfComponent.implem_ecoKnowledgeStudent != null: "This is a bug.";
    assert implem.use_knowledgeS == null: "This is a bug.";
    implem.use_knowledgeS = this.selfComponent.implem_ecoKnowledgeStudent._createImplementationOfKnowledgeStudentS(id);
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
  
  /**
   * This should be overridden by the implementation to instantiate the implementation of the species.
   * 
   */
  protected EcoAgents.RoomAgent make_RoomAgent(final String id) {
    return new EcoAgents.RoomAgent();
  }
  
  /**
   * Do not call, used by generated code.
   * 
   */
  public EcoAgents.RoomAgent _createImplementationOfRoomAgent(final String id) {
    EcoAgents.RoomAgent implem = make_RoomAgent(id);
    if (implem == null) {
    	throw new RuntimeException("make_RoomAgent() in sma.ecoAgents.EcoAgents should not return null.");
    }
    assert implem.ecosystemComponent == null: "This is a bug.";
    assert this.selfComponent != null: "This is a bug.";
    implem.ecosystemComponent = this.selfComponent;
    assert this.selfComponent.implem_ecoKnowledgeRoom != null: "This is a bug.";
    assert implem.use_knowledgeS == null: "This is a bug.";
    implem.use_knowledgeS = this.selfComponent.implem_ecoKnowledgeRoom._createImplementationOfKnowledgeRoomS(id);
    assert this.selfComponent.implem_ecoRoom != null: "This is a bug.";
    assert implem.use_roomS == null: "This is a bug.";
    implem.use_roomS = this.selfComponent.implem_ecoRoom._createImplementationOfRoomS(id);
    return implem;
  }
  
  /**
   * This can be called to create an instance of the species from inside the implementation of the ecosystem.
   * 
   */
  protected EcoAgents.RoomAgent.Component newRoomAgent(final String id) {
    EcoAgents.RoomAgent _implem = _createImplementationOfRoomAgent(id);
    return _implem._newComponent(new EcoAgents.RoomAgent.Requires() {},true);
  }
}
