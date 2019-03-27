FROM debian:buster-slim
RUN mkdir /opt/visitsroutes
WORKDIR /opt/visitsroutes
COPY target/*-runner /opt/visitsroutes/visitsrouts_api
RUN chmod 775 -R /opt/visitsroutes
CMD ["./visitsrouts_api", "-Dquarkus.http.host=0.0.0.0"]