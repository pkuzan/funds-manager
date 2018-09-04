#!/bin/bash

# Stop server
fm_running=`pgrep -f funds-manager`
if [[ -n  $fm_running ]]; then
    pkill -f funds-manager
fi

