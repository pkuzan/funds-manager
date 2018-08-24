#!/bin/bash

# Stop server
pricer_running=`pgrep -f gt-cloud-pricer`
if [[ -n  $pricer_running ]]; then
    pkill -f gt-cloud-pricer
fi

