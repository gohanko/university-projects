#!/bin/bash

CHECK_CONFIG_OUTPUT=$(sudo nginx -t 2>&1 | tail -n +2 | awk '{ print $7}')
if [ "$CHECK_CONFIG_OUTPUT" == "successful" ];
then
    echo "nginx config file OK"
    sudo systemctl restart nginx
    sudo nginx -s reload
    echo $(sudo systemctl status nginx | tail -n +3 | head -n +1)
else
    echo "nginx config file error"
fi
