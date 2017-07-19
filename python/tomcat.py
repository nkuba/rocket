#!/usr/bin/python
import os
import sys
import subprocess
import platform
import shutil

tomcat_home_env_name = "CATALINA_HOME"

try:
    tomcat_home_dir = os.environ[tomcat_home_env_name]
    print(tomcat_home_env_name + "=" + tomcat_home_dir)
except KeyError:
    print("ERROR: Please set the environment variable %s" % tomcat_home_env_name)
    sys.exit(1)

tomcat_bin_dir = os.path.join(tomcat_home_dir, "bin")

os_platform = platform.system()
print("Current system platform: " + os_platform)
is_windows = os_platform == "Windows"

if is_windows:
    start_script_name = "startup.bat"
else:
    start_script_name = "startup.sh"

if is_windows:
    stop_script_name = "shutdown.bat"
else:
    stop_script_name = "shutdown.sh"


def start():
    os.chdir(tomcat_bin_dir)
    subprocess.call(os.path.join(tomcat_bin_dir, start_script_name), shell=True)


def stop():
    os.chdir(tomcat_bin_dir)
    subprocess.call(os.path.join(tomcat_bin_dir, stop_script_name), shell=True)


def deploy():
    # TODO: SWITCH TOOL TO CHEF OR DOCKER AND OTHERS
    src = os.path.abspath(os.path.join(".", "../rest/build/libs/rest.war"))
    dest = os.path.join(tomcat_home_dir, "webapps")
    print("Copy {} to {}".format(src, dest))
    shutil.copy2(src, dest)


arguments_error = "Please use one of 'start|stop|restart|deploy'"

if len(sys.argv) != 2:
    print("ERROR: Missing argument. %s" % arguments_error)
    sys.exit(1)
else:
    action = sys.argv[1]

if action == 'start':
    start()
elif action == 'stop':
    stop()
elif action == 'restart':
    stop()
    start()
elif action == 'deploy':
    deploy()
else:
    print("ERROR: Invalid argument '{}'. {}".format(action, arguments_error))
