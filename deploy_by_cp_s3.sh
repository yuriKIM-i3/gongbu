AWS_PROFILE_NAME=gongbu_prd
AWS_APP_NAME=gongbu-app

git archive -o build.zip HEAD

aws s3 cp ./build.zip s3://$AWS_APP_NAME-pipeline-src/build.zip --profile $AWS_PROFILE_NAME