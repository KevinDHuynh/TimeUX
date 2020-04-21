# TimeUX

## Description
- TimeUX is a Time and Attendance product that allows for Employees to clock hours and set their current working status
- The Server is cloud-hosted and contains a database and REST API for communication and submission 
- The Client is an Android App that allows for users to easily sign in clock hours either on premises or remotely
- The goal of the project is to build upon our knowledge of app development, server architecture, and general project management skills to accomplish all of our specific use cases.

## Client Implementation
The Android client uses the following libraries:
- [Unirest-Android](https://github.com/zeeshanejaz/unirest-android)
- [Butterknife](https://jakewharton.github.io/butterknife/)



## Server Implementation
TimeUX's server is based upon [Restheart](https://restheart.org) and it's open-source release. Restheart is a server program that hosts a Mondo DB and it's own REST API for easy data management between client-server interactions. Installation is docker-based and ran on an Azure Ubuntu VM for development. A portion of the server implentation is hosted on [DockerHub](https://hub.docker.com/repository/docker/kevindhuynh/timeux-restheart-db/).
Due to RestHeart being split into 3 docker containers (RestHeart,RestHeart-Security, and RestHeartMondo) and DockerHub only allowing for 1 free repository for normal users the DB-related container is pushed since it has the most modifications.

## Demo
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/nbL7WfPMpbk/0.jpg)](https://www.youtube.com/watch?v=nbL7WfPMpbk)
