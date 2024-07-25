import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;


public class Main extends AbstractBehavior<Main.SayHello> {

    public static record SayHello(String name) {}

    public static Behavior<SayHello> create() {
        return Behaviors.setup(Main::new);
    }

    private final ActorRef<Actor1.Greet> greeter;

    private Main(ActorContext<SayHello> context) {
        super(context);
        greeter = context.spawn(Actor1.create(), "greeter");
    }

    @Override
    public Receive<SayHello> createReceive() {
        return newReceiveBuilder().onMessage(SayHello.class, this::onSayHello).build();
    }

    private Behavior<SayHello> onSayHello(SayHello command) {
        ActorRef<Actor1.Greeted> replyTo =
                getContext().spawn(Actor2.create(3), command.name);
        greeter.tell(new Actor1.Greet(command.name, replyTo));
        return this;
    }

    public static void main(String[] args) {
        final ActorSystem<SayHello> system =
                ActorSystem.create(Main.create(), "hello");

        system.tell(new Main.SayHello("World"));
        system.tell(new Main.SayHello("Akka"));
    }
}