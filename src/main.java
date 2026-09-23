// nn Client
class main {
    public static void main(String[] args) {
        Light bedroom = new Light("Bedroom");
        Fan   fan     = new Fan();
        Command lightOn  = new LightOnCommand(bedroom);
        Command lightOff = new LightOffCommand(bedroom);
        Command fanStart = new FanStartCommand(fan);
        RemoteControl remote = new RemoteControl();
        remote.pressButton(lightOn);   // Bedroom Light ON
        remote.pressButton(fanStart);  // Fan started
        remote.pressUndo();
// Fan stopped  (undo last)
        remote.pressButton(lightOff);  // Bedroom Light OFF
        remote.pressUndo();
// Bedroom Light ON (undo)
    }
}
