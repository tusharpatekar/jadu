import paho.mqtt.client as mqtt

BROKER = "test.mosquitto.org"
TOPIC = "example/pubsub"

def on_message(client, userdata, msg):
    print(f"Received message: {msg.payload.decode()}")

def subscribe_messages():
    client = mqtt.Client()
    client.on_message = on_message
    client.connect(BROKER, 1883, 60)
    client.subscribe(TOPIC)
    print("Subscribed to topic:", TOPIC)
    client.loop_forever()

if __name__ == "__main__":
    subscribe_messages()
'''
   pip install paho-mqtt
   
   python subscriber.py  
   (after this command open another terminal and run command python publisher.py) 
   Received message: HI
'''
