// nn Command interface
interface Command {
    void execute();
    void undo();       // optional but common in exam questions
}
// nn Receiver — performs the actual work
class Light {
    private String name;
    Light(String name) { this.name = name; }
    void on()  { System.out.println(name + " Light ON");  }
    void off() { System.out.println(name + " Light OFF"); }
}
class Fan {
    void start() { System.out.println("Fan started");  }
    void stop()  { System.out.println("Fan stopped"); }
}
// nn ConcreteCommands
class LightOnCommand implements Command {
    private Light light;
    LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo()    { light.off(); }
}
class LightOffCommand implements Command {
    private Light light;
    LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo()    { light.on(); }
}
class FanStartCommand implements Command {
    private Fan fan;
    FanStartCommand(Fan fan) { this.fan = fan; }
    public void execute() { fan.start(); }
    public void undo()    { fan.stop(); }
}
// nn Invoker — a remote control with history for undo
class RemoteControl {
    private Command lastCommand;
    public void pressButton(Command cmd) {
        cmd.execute();
        lastCommand = cmd;
    }
    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
            lastCommand = null;
        }
    }
}
