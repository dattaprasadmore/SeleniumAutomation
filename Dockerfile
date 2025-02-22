FROM bellsoft/liberica-openjdk-alpine:17.0.8
#This is a Dockerfile used to generate image with SHELL RUN command
#Install Curl JQ
RUN apk add curl jq

#Workspace
WORKDIR /home/selenium-docker

#Add the required files
ADD target/docker ./
ADD runner.sh runner.sh

#Environment Variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

#Run the test
ENTRYPOINT sh runner.sh