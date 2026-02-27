pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2"
    }

    parameters {
        choice(
            name: 'SUITE',
            choices: ['api-smoke', 'api-regression', 'ui-smoke', 'ui-regression'],
            description: 'Which suite to run'
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
                script {
                    def groups = ''

                    if (params.SUITE == 'ui-smoke') {
                        groups = 'ui,smoke'
                    } else if (params.SUITE == 'ui-regression') {
                        groups = 'ui,regression'
                    } else if (params.SUITE == 'api-smoke') {
                        groups = 'api,smoke'
                    } else if (params.SUITE == 'api-regression') {
                        groups = 'api,regression'
                    } else {
                        error("Unknown SUITE value: ${params.SUITE}")
                    }

                    bat """
                        mvn clean test ^
                          -Dgroups=${groups} ^
                          -Dprofile=ci
                    """
                }
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
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true,
                  keepLongStdio: false
        }
    }
}