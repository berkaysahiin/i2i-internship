import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

 class Actor1 extends AbstractBehavior<Actor1.Greet> {

    public static record Greet(String whom, ActorRef<Greeted> replyTo) {}
    public static record Greeted(String whom, ActorRef<Greet> from) {}

    public static Behavior<Greet> create() {
        return Behaviors.setup(Actor1::new);
    }

    private Actor1(ActorContext<Greet> context) {
        super(context);
    }

    @Override
    public Receive<Greet> createReceive() {
        return newReceiveBuilder().onMessage(Greet.class, this::onGreet).build();
    }

    private Behavior<Greet> onGreet(Greet command) {
        getContext().getLog().info("Hello {}!", command.whom);
        command.replyTo.tell(new Greeted(command.whom, getContext().getSelf()));
        return this;
    }
}