package DIS14_MQTT.egenKode.fraUndervisning;

import java.io.IOException;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTprogram {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        String broker = "tcp://192.168.0.115:1883";
        String topic = "cmnd/grp3336/Power1";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker,  MqttClient.generateClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            // put real stuff here        < -------- !!!!
            Scanner input = new Scanner(System.in);

            while (true){
                String stringMessage = input.nextLine();
                if (stringMessage.equals("exit")) break;

                MqttMessage message = new MqttMessage(stringMessage.getBytes());
                message.setQos(1);
                sampleClient.publish(topic, message);
            }

            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();

        }
    }
}