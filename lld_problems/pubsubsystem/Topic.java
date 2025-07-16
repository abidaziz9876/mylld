package lld_problems.pubsubsystem;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Topic {
    String name;
    Set<Subscriber> subscribers=new CopyOnWriteArraySet<>();
    public Topic(String name){
        this.name=name;
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public String getName(){
        return name;
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void publish(Message message){
        for(Subscriber subscriber: subscribers){
            subscriber.consume(message);
        }
    }
}
