#!/usr/bin/env bash

# apt-get update
# apt-get install -y apache2
# if ! [ -L /var/www ]; then
#   rm -rf /var/www
#   ln -fs /vagrant /var/www
# fi

echo "Installing Java 8"
sudo apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
sudo add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"
sudo apt-get update
sudo apt-get install oracle-java8-installer
echo "Setting environment variables for Java 8"
sudo apt-get install -y oracle-java8-set-default

echo "Installing Tomcat 8"
sudo apt-get update
sudo apt-get -y install tomcat8
