#!/bin/bash
######################################
#AUTHOR: ADRIEN
#DATE:25-02-2024
#VERSION: V1

#THIS SCRIPT WILL REPORT AWS USAGE 
######################################

set -x
#AWS S3
#AWS EC2
#AWS LAMBDA
#AWS IAM USERS

#LIST S3 BUCKETS

echo "print list of s3 buckets"
aws s3 ls

#AWS INSTANCES
echo "print list ec2 instances"
aws ec2 describe-instances | jq '.Reservation[].Instances[].InstanceId'

#LIST AWS LAMBDA
echo "print list of lambda functions"
aws lambda list-functions

#AWS IAM USERS 
echo "print list of iam users"
aws iam list-users


