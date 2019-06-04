package MqttClient;

import org.eclipse.paho.client.mqttv3.MqttCallback; 
import org.eclipse.paho.client.mqttv3.MqttClient; 
import org.eclipse.paho.client.mqttv3.MqttConnectOptions; 
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken; 
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken; 
import org.eclipse.paho.client.mqttv3.MqttException; 
import org.eclipse.paho.client.mqttv3.MqttMessage; 
import org.eclipse.paho.client.mqttv3.MqttTopic; 
 

public class SimpleMqttClient implements MqttCallback { 
	 
	   MqttClient myClient; 
	MqttConnectOptions connOpt; 
	 
	  static final String BROKER_URL = "tcp://59.67.107.105:1883"; 
	  static final String M2MIO_DOMAIN = "<Insert m2m.io domain here>"; 
	  static final String M2MIO_STUFF = "things"; 
	  static final String M2MIO_THING = "<Unique device ID>"; 
	  static final String M2MIO_USERNAME = "<m2m.io username>"; 
	  static final String M2MIO_PASSWORD_MD5 = "<m2m.io password (MD5 sum of password)>"; 
	 
	  // the following two flags control whether this example is a publisher, a subscriber or both 
	  static final Boolean subscriber = true; 
	  static final Boolean publisher = true; 
	 
	 
	  public void connectionLost(Throwable t) { 
		  System.out.println("Connection lost!"); 
		  // code to reconnect to the broker would go here if desired 
		} 
		/** 
		  *   
		  * deliveryComplete 
		  * This callback is invoked when a message published by this client 
		  * is successfully received by the broker. 
		  *   
		  */ 
		 
		public void deliveryComplete(IMqttDeliveryToken token) { 
		  try{ 
		   
		  System.out.println("Pub complete" + new String(token.getMessage().getPayload())); 
		      } catch (MqttException e) { 
		    e.printStackTrace(); 
		    System.exit(-1); 
		  } 
		} 
		public void messageArrived(String topic, MqttMessage message) throws Exception { 
			  System.out.println("-------------------------------------------------"); 
			  System.out.println("| Topic:" + topic); 
			  System.out.println("| Message: " + new String(message.getPayload())); 
			  System.out.println("-------------------------------------------------"); 
			} 
		public static void main(String[] args) { 
			  SimpleMqttClient smc = new SimpleMqttClient(); 
			} 
			 
			/** 
			  *   
			  * runClient 
			  * The main functionality of this simple examp
			  * Create a MQTT client, connect to broker, pu
			  *   
			  */ 
		  public void runClient() { 
			    // setup MQTT Client 
			    String clientID = M2MIO_THING; 
			    connOpt = new MqttConnectOptions(); 
			     
			    connOpt.setCleanSession(true); 
			    connOpt.setKeepAliveInterval(30); 
			    //connOpt.setUserName(M2MIO_USERNAME);  
			    try { 
			      myClient = new MqttClient(BROKER_URL, clientID); 
			      myClient.setCallback(this); 
			      myClient.connect(connOpt); 
			    } catch (MqttException e) { 
			      e.printStackTrace(); 
			      System.exit(-1); 
			    } 
			    System.out.println("Connected to " + BROKER_URL);  
			    String myTopic = "MQTT Examples"; 
			    MqttTopic topic = myClient.getTopic(myTopic); 
			 
			    // subscribe to topic if subscriber 
			    if (subscriber) { 
			    	  try { 
			    	    int subQoS = 0; 
			    	    myClient.subscribe(myTopic, subQoS); 
			    	  } catch (Exception e) { 
			    	    e.printStackTrace(); 
			    	  } 
			    	} 
			 // publish messages if publisher 
			    if (publisher) { 
			      for (int i=1; i<=1; i++) { 
			            String pubMsg = "{\"pubmsg\":" + i + "}"; 
			            int pubQoS = 0; 
			        MqttMessage message = new MqttMessage(pubMsg.getBytes()); 
			           message.setQos(pubQoS); 
			           message.setRetained(false); 
			           // Publish the message 
			           System.out.println("Publishing to topic \"" + topic + "\" qos " + pubQoS); 
			           MqttDeliveryToken token = null; 
			           try { 
			             // publish message to broker 
			          token = topic.publish(message); 
			             // Wait until the message has been delivered to the broker 
			          token.waitForCompletion(); 
			          Thread.sleep(1000); 
			           } catch (Exception e) { 
			               e.printStackTrace(); 
			             } 
			           }       
			         } 
			          
			         // disconnect 
			         try { 
			           // wait to ensure subscribed messages are delivered 
			           if (subscriber) { 
			             Thread.sleep(5000); 
			           } 
			           myClient.disconnect(); 
			         } catch (Exception e) { 
			           e.printStackTrace(); 
			         } 
			       } 
}
				 
			
