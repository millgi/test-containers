
pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh "./gradlew check"
      }
      post {
        always {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
        }
      }
    }
  }
}
