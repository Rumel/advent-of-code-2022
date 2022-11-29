#!/usr/bin/env bash

if [ -z "$1" ]
then
  clj -A:test
else
  problem=`printf %02d $1`
  clj -A:test -n "problems.problem-$problem-test"
fi