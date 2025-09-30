package DIS14_MQTT.egenKode;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class Subscriber {
    public static void main(String[] args) {
        String broker = "tcp://broker.hivemq.com:1883"; // Public test broker
        String clientId = "JavaSubscriber123";
        String topic = "test/topic";

        try {
            IMqttClient client = new MqttClient(broker, clientId);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            client.connect(options);
            System.out.println("Subscriber connected to broker.");

            // Subscribe to topic
            client.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message: " + new String(message.getPayload()));
                }
            });

            // Keep the subscriber running
            System.out.println("Waiting for messages...");
            while (true) {
                Thread.sleep(1000);
            }

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

