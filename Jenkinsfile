pipeline {
    agent any
    stages {
        stage('Package') {
            steps {
                echo 'packaging done'
                sleep 4
            }
        }
        stage('Update DB') {
            steps {
                echo 'Database update done'
                sleep 4
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deployment done'
                sleep 4
            }
        }
        stage('UpdateJIRA') {
            steps {
                echo 'updating JIRA ticket'
                sleep 4
            }
        }
    }
}