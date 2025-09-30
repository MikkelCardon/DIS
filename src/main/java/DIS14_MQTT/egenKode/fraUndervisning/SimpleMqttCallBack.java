package DIS14_MQTT.egenKode.fraUndervisning;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SimpleMqttCallBack implements MqttCallback {
    int fan0 = 0;
    String topicFan0 = "cmnd/grp3336/Power1";

    int fan1 = 0;
    String topicFan1 = "cmnd/grp3254/Power1";

    List<Double> humidityList = new ArrayList<>();

    private MqttClient client;
    public SimpleMqttCallBack(MqttClient sampleClient) {
        this.client = sampleClient;
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String res= new String(mqttMessage.getPayload());
        System.out.println("NEW MESSAGE FROM PUB : "+res);
        //System.out.println(res);
        // res indeholder en m ling som et JSON-object
        // put real stuff here     < --------    !!!!!!!!!!
        try{
            JSONObject json = new JSONObject(res);

            if (json.has("AM2301")){
                sensorRead(json);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

//        else if(json.has("POWER")){
//            //stateRead(json);
//        }
    }

    private void stateRead(JSONObject json) {
        String power = json.getString("POWER");

        int newStatus = power.equals("ON") ? 1 : 0;
        System.out.println("newStatus = " + newStatus);
        fan1 = newStatus;
    }

    private void sensorRead(JSONObject jsonObject){
        JSONObject object = jsonObject.getJSONObject("AM2301");

        double temp = object.getDouble("Temperature");
        double humidity = object.getDouble("Humidity");
        System.out.println("temperature: " + temp + " --- humidity: " + humidity);
        double averageHumidity = newReading(humidity);
        System.out.println("averageHumidity = " + averageHumidity);

        if (averageHumidity > 85){ //Begge kører
            publishToFan(0, 1);
            publishToFan(1, 1);
        }else if(averageHumidity > 60){ //En kører
            publishToFan(0, 1);
            publishToFan(1, 0);
        }else { //Begge slukket
            publishToFan(0, 0);
            publishToFan(1, 0);
        }
    }

    private synchronized double newReading(double newReading){
        if(humidityList.size() == 2){
            humidityList.removeLast();
            humidityList.addFirst(newReading);
        }
        else {
            humidityList.addFirst(newReading);
        }

        if (humidityList.size() == 2){
            return (humidityList.get(0) + humidityList.get(1)) / 2;
        }
        return -1;
    }

    private void publishToFan(int fanId, int newState) {
        String topic = fanId == 0 ? topicFan0 : topicFan1;
        String stateMessage = String.valueOf(newState);

        MqttMessage message = new MqttMessage(stateMessage.getBytes());
        message.setQos(1);
        try {
            client.publish(topic, message);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }
}
