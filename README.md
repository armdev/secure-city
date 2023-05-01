# secure-city


Product Name: SecureCity

SecureCity is a microservice-based system that enables real-time detection and reporting of critical events based on data received from sensors. The system comprises of the following components:

Geo Microservice: This component receives messages from sensors and extracts relevant information such as IT, Latitude, Longitude, Event Type, and Status.
Alert Microservice: This component processes the messages received from the Geo Microservice and determines the appropriate course of action based on the event type. Depending on the event type, the Alert Microservice sends a message to either the Police Microservice or the Ambulance Microservice using Kafka/Spring.
Police Microservice: This component maintains a database of police machines and their current locations. When a message is received from the Alert Microservice, this component matches the event location with the locations of nearby police machines and sends a message to all of them to drive to the event location.
Ambulance Microservice: This component maintains a database of ambulance machines and their current locations. When a message is received from the Alert Microservice, this component matches the event location with the locations of nearby ambulance machines and sends a message to all of them to drive to the event location.
GeoAlert is capable of detecting various critical events such as SHOOTING, FIRING, FIRE, HOOLIGANS, and CAR_INCIDENT with possible statuses such as STARTED, IN_PROGRESS, and FINISHED. The system uses Kafka/Spring to ensure fast and accurate message routing to the appropriate responders.

GeoAlert is a powerful tool for real-time event detection and response, enabling rapid deployment of resources to emergency situations.





Regenerate response
