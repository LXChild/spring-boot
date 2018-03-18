#!/usr/bin/env bash

# file:test.sh
# author:13
# date:2017-07-20

set -e

STR1="test string"
STR2="ter"

echo "STR1:"$STR1
echo "STR2:"$STR2

if [[ "$STR1" =~ "$STR2" ]];then
     echo "包含"
 else
     echo "不包含"
 fi