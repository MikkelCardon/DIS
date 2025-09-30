package DIS14_MQTT.egenKode.fraUndervisning;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;

public class MQTTprogramWithCallback{

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        String broker = "tcp://192.168.0.115:1883";
        String sensor1 = "tele/grp3336/SENSOR";
        String sensor2 = "tele/grp3254/SENSOR";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker,  MqttClient.generateClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);

            sampleClient.setCallback(new SimpleMqttCallBack(sampleClient));

            sampleClient.connect(connOpts);
            System.out.println("Connected");

            sampleClient.subscribe(sensor1);
            sampleClient.subscribe(sensor2);
            // put real stuff here        < -------- !!!!
            Thread.sleep(200_000);

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