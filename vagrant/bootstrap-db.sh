#!/usr/bin/env bash

echo "Installing MongoDB..."
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
echo "deb http://repo.mongodb.org/apt/debian jessie/mongodb-org/3.4 main" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.4.list
sudo apt-get update
sudo apt-get -y install mongodb-org

echo "Configuring MongoDB..."
sudo sed -e '/bindIp: 127.0.0.1/ s/^#*/#/' -i /etc/mongod.conf

echo "Starting MongoDB..."
sudo service mongod start