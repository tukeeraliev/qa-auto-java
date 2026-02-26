pipeline {
    agent any

    parameters {
        choice(
            name: 'SUITE',
            choices: ['api-smoke', 'api-regression', 'ui-smoke', 'ui-regression'],
            description: 'Select test suite to run'
        )
    }

    tools {
        maven 'Maven'   // имя Maven из Jenkins Global Tool Configuration
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -P${params.SUITE}"
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'allure-results']]
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}