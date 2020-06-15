# HoboHealthCheck

The health check is for a website, not the hobo. The hobo needs more than a health check! Jenkins pipeline lib.

This was used in a small presentation stored in https://github.com/Cervator/modern-jenkins

It consisted of a Jenkins job with the below inlined Jenkinsfile. When run it would use the hobo healthcheck to check on a target URL (now long gone)

If the check fails the job sends a notification to OpsGenie

```
@Library('HoboHealthCheck') _

pipeline {
    agent { label 'master' }
    environment {
        success = hoboWebCheck("http://other-widget.jx-go2group-other-widget-pr-5.35.196.145.46.nip.io", "Cool Jenkins X demo")
    }
    stages {
        stage('post') {
            when {
                environment name: 'success', value: 'false'
            }
            steps {
                echo "Success? $success"
                error "The hobos call for aid!"
            }
        }
    }
    post {
        success {
            echo 'All is well no need to do anything'
        }
        failure {
            echo 'This will run only if failed'
            opsgenie(tags: "failure, critical", teams: "Hobo GitOps", priority:"P3")
        }
    }
} 

```
