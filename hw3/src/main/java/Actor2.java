import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Actor2 extends AbstractBehavior<Actor1.Greeted> {

    public static Behavior<Actor1.Greeted> create(int max) {
        return Behaviors.setup(context -> new Actor2(context, max));
    }

    private final int max;
    private int greetingCounter;

    private Actor2(ActorContext<Actor1.Greeted> context, int max) {
        super(context);
        this.max = max;
    }

    @Override
    public Receive<Actor1.Greeted> createReceive() {
        return newReceiveBuilder().onMessage(Actor1.Greeted.class, this::onGreeted).build();
    }

    private Behavior<Actor1.Greeted> onGreeted(Actor1.Greeted message) {
        greetingCounter++;
        getContext().getLog().info("Greeting {} for {}", greetingCounter, message.from());
        if (greetingCounter == max) {
            return Behaviors.stopped();
        } else {
            message.from().tell(new Actor1.Greet(message.whom(), getContext().getSelf()));
            return this;
        }
    }
}