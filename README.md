# secure-city


Product Name: SecureCity

SecureCity is a microservice-based system that enables real-time detection and reporting of critical events. It is capable of detecting various critical events, such as shootings, fires, and car accidents. The system uses Kafka/Spring to ensure fast and accurate message routing to the appropriate responders.

The system comprises of the following components:

Incident Microservice: This component receives messages from sensors and extracts relevant information, such as the incident ID, latitude, longitude, event type, and status.
Alert Microservice: This component processes the messages received from the Incident Microservice and determines the appropriate course of action based on the event type. Depending on the event type, the Alert Microservice sends a message to either the Police Microservice or the Ambulance Microservice.
Police Microservice: This component maintains a database of police machines and their current locations. When a message is received from the Alert Microservice, this component matches the event location with the locations of nearby police machines and sends a message to all of them to drive to the event location.
Ambulance Microservice: This component maintains a database of ambulance machines and their current locations. When a message is received from the Alert Microservice, this component matches the event location with the locations of nearby ambulance machines and sends a message to all of them to drive to the event location.
SecureCity is a powerful tool for real-time event detection and response, enabling rapid deployment of resources to emergency situations.


