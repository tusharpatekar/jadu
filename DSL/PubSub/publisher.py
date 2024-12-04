import paho.mqtt.client as mqtt
import time

BROKER = "test.mosquitto.org"  # Public broker for testing
TOPIC = "example/pubsub"

def publish_message():
    client = mqtt.Client()
    client.connect(BROKER, 1883, 60)
    while True:
        message = input("Enter a message to publish: ")
        client.publish(TOPIC, message)
        print(f"Published: {message}")
        time.sleep(1)

if __name__ == "__main__":
    publish_message()
