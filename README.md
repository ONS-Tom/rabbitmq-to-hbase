# rabbitmq-to-hbase
A repo for testing getting a message from a RabbitMQ queue and inserting it into HBase.

# Environment Setup

You will need the following:
- SBT (version `0.13.13`)
- Scala (version `2.11.8`)
- Docker

# Running RabbitMQ

To run RabbitMQ, run the following commands:

```shell
docker pull rabbitmq
docker run -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15672:15672 -d --hostname my-rabbit --name some-rabbit rabbitmq:3
```

Now that RabbitMQ is running, we can start the management UI:

```shell
docker exec some-rabbit rabbitmq-plugins enable rabbitmq_management
```

Go to the following URL to access the RabbitMQ admin user interface, with username/password as 'guest'.

http://localhost:15672

## Running Details

```shell
sbt run
```