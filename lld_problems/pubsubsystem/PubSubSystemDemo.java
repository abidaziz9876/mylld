package lld_problems.pubsubsystem;


/*
1-The Pub-Sub system should allow publishers to publish messages to specific topics.
2-Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
3-The system should support multiple publishers and subscribers.
4-Messages should be delivered to all subscribers of a topic in real-time.
5-The system should handle concurrent access and ensure thread safety.
6-The Pub-Sub system should be scalable and efficient in terms of message delivery.
*/
public class PubSubSystemDemo {
    public static void main(String[] args) {

        Topic topic1= new Topic("topic1");
        Topic topic2= new Topic("topic2");
        Publisher publisher1= new Publisher();
        Publisher publisher2= new Publisher();

        Subscriber subscriber1 = new LoggingSubscriber("Subscriber1");
        Subscriber subscriber2 = new LoggingSubscriber("Subscriber2");
        Subscriber subscriber3 = new LoggingSubscriber("Subscriber3");


        publisher1.registerTopic(topic1);
        publisher2.registerTopic(topic2);

        topic1.addSubscriber(subscriber1);
        topic1.addSubscriber(subscriber2);
        topic2.addSubscriber(subscriber2);
        topic2.addSubscriber(subscriber3);

        // Publish messages
        publisher1.publish(topic1, new Message("Message1 for Topic1"));
        publisher1.publish(topic1, new Message("Message2 for Topic1"));
        publisher2.publish(topic2, new Message("Message1 for Topic2"));
    }
}
