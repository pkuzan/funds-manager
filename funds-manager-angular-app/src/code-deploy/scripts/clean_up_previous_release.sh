#!/bin/bash

# Remove old jar. Not normally needed, but if a previous deploy has failed it is possible for a hosts to be
# left in an unexpected state where CodeDeploy doesn't realize that the jar is deployed and so will not delete
# it, but will also not be able to install a new one.
if [ -f /opt/gt-cloud-pricer-default/pricer-default.jar ]; then rm /opt/gt-cloud-pricer-default/pricer-default.jar; fi

