pipeline {
    agent none
    environment {
        AWS_ACCESS_KEY_ID       = credentials('aws-access-key')
        AWS_SECRET_ACCESS_KEY   = credentials('aws-secret-key')
        AWS_DEFAULT_REGION      = 'us-west-2'
        ARTIFACT_NAME = 'spring-petclinic-rest.jar'
        AWS_S3_BUCKET = 'elasticbeanstalk-us-west-2-231784247281'
        AWS_EB_APP_NAME = 'spring-petclinic-rest'
        AWS_EB_ENVIRONMENT = 'spring-petclinic-rest-env'
        AWS_EB_APP_VERSION = "${BUILD_ID}"
    }
    stages {
        stage('Build') {
            agent{
                docker {
                    image 'maven:3.6.3-jdk-11'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests -B -ntp'
            }
        }
        stage('Deploy EC2') {
            agent{
                docker {
                    image 'amazon/aws-cli'
                    args '--entrypoint=""'
                }
            }
            steps {
                sh 'aws --version'

                archiveArtifacts 'target/*.jar'

                sh "aws s3 cp ./target/spring-petclinic-rest-2.2.6.jar s3://$AWS_S3_BUCKET/$ARTIFACT_NAME"
            
                sh "aws elasticbeanstalk create-application-version --application-name $AWS_EB_APP_NAME --version-label $AWS_EB_APP_VERSION --source-bundle S3Bucket=$AWS_S3_BUCKET,S3Key=$ARTIFACT_NAME"
                
                sh "aws elasticbeanstalk update-environment --application-name $AWS_EB_APP_NAME --environment-name $AWS_EB_ENVIRONMENT --version-label $AWS_EB_APP_VERSION"

            }
        }
    }
}