# Hello Cloud
An AWS experiment.

This project is a runnable and deployable Pricer that injects default implementations.

This project depends on pricer-core which must be mvn install(ed) before this project can be used.

The Spring Boot Maven is included so it can be deployed using Beanstalk for example.


##Create Autoscale Group

```
aws cloudformation create-stack --stack-name pk-pricer \
--template-body file:///Users/pkuzan/dev/aws/hellocloud/pricer-default/cfn/pricer-autoscalegroup.cfn.json \
--parameters ParameterKey=ResourcesStackName,ParameterValue=pk-pricer ParameterKey=KeyName,ParameterValue=MyKey \
--role-arn arn:aws:iam::151194741754:role/gt-cloud-cloud-formation-service-role \
--capabilities CAPABILITY_IAM
```

##Create Application
You only need to do this once.
```
aws deploy create-application --application-name "Pricer"
```
##Create Deployment Group
A deployment group links an application to an auto scaling group and a service role.
```
aws deploy create-deployment-group --application-name "Pricer" --deployment-group-name "PricerDeploymentGroup" \
--auto-scaling-groups "SC-151194741754-pp-vj6vowmuoqfme-AutoScalingGroup-I9DLBWWU8TH1"  \
--service-role-arn arn:aws:iam::151194741754:role/SC-151194741754-pp-vj6vowmuoqf-CodeDeployTrustRole-WAL97CBP7ET5 \
--deployment-config-name "CodeDeployDefault.OneAtATime"
```
##Push
```
aws deploy push --application-name Pricer --s3-location s3://gt-cloud-testbed-eu-west-1/public/codedeploy/PricerCodeDeployV1.zip --source \ /Users/pkuzan/dev/aws/hellocloud/pricer-default/target/code-deploy
```
##Deploy
```
aws deploy create-deployment --application-name Pricer --s3-location \ bucket=gt-cloud-testbed-eu-west-1,key=public/codedeploy/PricerCodeDeployV3.zip,bundleType=zip,eTag=d26048aeef6d3d5c8c8dca1feaecbcb5-12 \
--deployment-group-name PricerDeploymentGroup --deployment-config-name CodeDeployDefault.OneAtATime --description "Pricer"
```
SSH on to EC2 instance
```
ssh -i ~/.ssh/user19-ireland.pem ec2-user@ec2-34-251-203-21.eu-west-1.compute.amazonaws.com
```
