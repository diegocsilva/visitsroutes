FROM diegocsilva/quarkus_builder
RUN apt-get update -y 
RUN apt-get install -y curl
RUN apt-get install -y nodejs npm
RUN npm install -g @angular/cli
RUN mkdir -p /opt/visitsroutes/
WORKDIR /opt/visitsroutes