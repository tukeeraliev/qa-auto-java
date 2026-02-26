pipeline {
    agent any

    parameters {
        choice(
            name: 'SUITE',
            choices: ['api-smoke', 'api-regression', 'ui-smoke', 'ui-regression'],
            description: 'Select TestNG group to run'
        )
    }

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -Dgroups=${params.SUITE}"
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', keepLongStdio: false
        }
    }
}