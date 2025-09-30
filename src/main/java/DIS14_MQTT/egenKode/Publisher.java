package DIS14_MQTT.egenKode;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Scanner;

public class Publisher {
    public static void main(String[] args) {
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = "JavaPublisher123";
        String topic = "test/topic";

        try {
            IMqttClient client = new MqttClient(broker, clientId);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            client.connect(options);
            System.out.println("Publisher connected to broker.");

            Scanner input = new Scanner(System.in);
            // Publish a message
            while (true) {
                String payload = input.nextLine();

                if (payload.equalsIgnoreCase("exit")){
                    break;
                }

                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(1);
                client.publish(topic, message);
                System.out.println("Message published: " + payload);
            }

            client.disconnect();
            System.out.println("Publisher disconnected.");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
