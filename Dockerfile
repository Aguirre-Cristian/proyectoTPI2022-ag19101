FROM payara/server-full:5.2022.1-jdk11
USER root
RUN apt update && apt install -y wget
USER payara
RUN wget -O $PAYARA_DIR/glassfish/lib/postgres.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
RUN echo "create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --property user=postgres:password=ag56485:serverName=db:portNumber=5432:databaseName=baches baches-pool" >> $PREBOOT_COMMANDS \
&& echo "create-jdbc-resource --connectionpoolid baches-pool jdbc/baches" >> $PREBOOT_COMMANDS
RUN echo "deploy $PAYARA_DIR/target/Baches-1.0-SNAPSHOT.war" >> $POSTBOOT_COMMANDS
COPY Baches-1.0-SNAPSHOT.war $PAYARA_DIR
