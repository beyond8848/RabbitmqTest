package com.yourwork.demo;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String QUEUE_NAME="rabbit";

    public static void Send()
    {
        Connection connection;
        Channel channel ;
        try
        {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");

            connection = factory.newConnection();

            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello Rabbit!";

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }catch (IOException ex)
        {
           System.out.print(ex);
        }
        catch (TimeoutException timeOu )
        {
            System.out.print(timeOu);
        }
        finally {

        }
    }
}
