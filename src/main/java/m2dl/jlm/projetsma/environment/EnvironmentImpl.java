package m2dl.jlm.projetsma.environment;

import sma.environment.Environment;

public class EnvironmentImpl extends Environment {

    @Override
    protected void start() {
        provides().environment().init();
    }

    @Override
    protected IEnvironment make_environment() {

        return new IEnvironment() {

            public void init() {
            };

        };
    }

}
