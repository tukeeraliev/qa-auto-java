pipeline {
  agent any

  environment {
    MAVEN_OPTS   = "-Dmaven.repo.local=.m2"
    API_BASE_URL = "https://restful-booker.herokuapp.com"
    UI_BASE_URL  = "https://www.saucedemo.com"
    CI = "true" // чтобы BaseUiTest включал headless
  }

  parameters {
    choice(name: 'SUITE', choices: ['api-smoke','api-regression','ui-smoke','ui-regression'])
  }

  tools { maven 'Maven' }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test') {
      steps {
        script {
          def groupsMap = [
            'ui-smoke'      : 'ui,smoke',
            'ui-regression' : 'ui,regression',
            'api-smoke'     : 'api,smoke',
            'api-regression': 'api,regression'
          ]
          def groups = groupsMap[params.SUITE]
          if (!groups) error("Unknown SUITE value: ${params.SUITE}")

          bat """
          mvn -B clean test ^
            -Dgroups=${groups} ^
            -Dprofile=ci ^
            -Dapi.base.url=%API_BASE_URL% ^
            -Dui.base.url=%UI_BASE_URL%
          """
        }
      }
    }

    stage('Allure Report') {
      when { expression { fileExists('target/allure-results') } }
      steps {
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
      }
    }
  }

  post {
    always {
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
    }
  }
}