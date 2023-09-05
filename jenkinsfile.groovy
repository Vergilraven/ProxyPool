pipeline {
    agent any

    stages {
        stage('Check') {
            steps {
                script {
                    def repositoryURL = 'https://github.com/Vergilraven/ProxyPool.git'
                    def gitCommand = "git ls-remote ${repositoryURL}"
                    def result = sh(script: gitCommand, returnStatus: true)

                    if (result == 0) {
                        echo "收到触发条件,开始更新代码"
                        sh 'git pull origin master'
                        // 执行其他操作

                    } else {
                        error "仓库不存在或无法访问,开始执行python3脚本为您重新配置登录的权限配置"
                        echo '******************************开始克隆仓库代码******************************'
                        sh "git clone ${repositoryURL}"
                        // 执行其他操作或中断构建
                        // 或者关联其他测试脚本的items
                    }
                }
            }

        }
    }
}
// stage(调k8s接口)
// stage(发布服务)
// stage(自动传字典进去吧)