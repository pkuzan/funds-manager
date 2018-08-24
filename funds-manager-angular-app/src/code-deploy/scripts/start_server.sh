#!/bin/bash

# Start server
# Source the properties that have been written into each of the hosts in the
# autoscale group by the cloud formation script. This is needed as the
# environment variables are not automatically picked up when running as root
# even through they are in the /etc/profile area
source /etc/profile.d/gt-cloud.sh
mkdir -p /var/log/gt-cloud-pricer-default
if [ -f /var/log/gt-cloud-pricer-default/gt-cloud-pricer.log ]; then mv /var/log/gt-cloud-pricer-default/gt-cloud-pricer.log "/var/log/gt-cloud-pricer-default/gt-cloud-pricer.log.$(date +"%Y%m%d_%H%M%S")"; fi
touch /var/log/gt-cloud-pricer-default/gt-cloud-pricer.log

# The application must not take over stdin, stdout or stderr streams otherwise
# code deploy will fail to work correctly.
# see http://docs.aws.amazon.com/codedeploy/latest/userguide/troubleshooting-deployments.html#troubleshooting-long-running-processes for details
java -jar /opt/gt-cloud-pricer-default/pricer-default.jar 2> /dev/null > /dev/null < /dev/null &

# Wait to see if a successful startup.
tail -f /var/log/gt-cloud-pricer/gt-cloud-pricer.log | while read LOGLINE
do
    [[ "${LOGLINE}" == *"Tomcat started on port(s)"* ]] && pkill -P $$ tail
done
exit 0
