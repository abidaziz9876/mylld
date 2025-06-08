

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
