pipeline {
    agent none
    environment {
        AWS_ACCESS_KEY_ID       = credentials('aws-access-key')
        AWS_SECRET_ACCESS_KEY   = credentials('aws-secret-key')
        AWS_DEFAULT_REGION      = "us-west-2"
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
                // sh 'aws configure set region us-west-2'
                sh 'aws s3 cp ./target/spring-petclinic-rest-2.2.6.jar s3://elasticbeanstalk-us-west-2-231784247281/spring-petclinic-rest.jar'
            }
        }
    }
}