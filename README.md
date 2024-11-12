# Config Server Git Push Notification System(EN)

## Overview
This project integrates a Config Server with a Git repository to automatically detect and notify backend servers of configuration changes upon each Git push to the `main` branch. This functionality ensures the backend is aware of any updates made to configuration files.

## Key Features
- **Config Server & Git Integration**: The Config Server is set up to monitor a Git repository for changes to configuration files.
- **Push Event Detection**: When changes are pushed to the `main` branch, a Git `post-receive` hook automatically notifies the backend server of the push event.
- **Backend Notification API**: The backend provides an API endpoint (`/api/git/notify`) that listens for push notifications and logs the update confirmation.

## How It Works
1. **Config Server Setup**: Config Server is configured to pull from a local Git repository (`application.yaml`).
2. **Git Hook (`post-receive`)**: This script runs on every push to `main` and sends a POST request to the backend's notification API.
3. **Backend Confirmation**: The backend logs and confirms the push event.

# Config Server Git Push Notification System (KR)

## 개요
이 프로젝트는 Config Server와 Git repository가 연동되어, Git에 YAML 파일 변경 사항이 푸시되면 이를 자동으로 감지하여 Config Server가 백엔드 서버에 푸시 알림을 보내는 기능을 포함하고 있다. 이를 통해 Config Server의 설정 변경이 자동으로 백엔드 서버에 전달되며, 설정 업데이트를 쉽게 관리할 수 있다.

## 기능 설명
1. **Config Server와 Git Repository 연동**
   - Config Server는 로컬에 위치한 Git Repository의 변경 사항을 감지하여 설정 정보를 관리한다.
   - Config Server의 설정 파일 (`application.yaml`)에서 Git repository의 URI 및 경로를 설정하여 Git과 연동한다.
   - Git repository의 `application.yaml` 파일 변경 사항이 푸시되면 Config Server가 이를 자동으로 감지한다.

2. **Git의 푸시 이벤트 감지 및 백엔드 서버 알림**
   - Git Hook (`post-receive`) 스크립트를 사용하여 `main` 브랜치에 푸시가 발생할 때마다 백엔드 서버에 API 요청을 보낸다.
   - Config Server가 푸시된 변경 사항을 감지하면, 설정 업데이트 후 자동으로 백엔드 서버에 API 요청을 통해 푸시가 완료되었음을 알린다.
   - 백엔드 서버는 해당 API 요청을 통해 받은 메시지를 콘솔에 출력하여 푸시 완료를 확인할 수 있다.

3. **백엔드 서버 알림 API**
   - 백엔드 서버는 `/api/git/notify` 엔드포인트를 통해 Config Server로부터 푸시 알림을 받는다.
   - Git repository에 변경 사항이 발생하고 Config Server가 이를 감지하면, 백엔드 서버에 푸시 완료 알림이 전송된다.

## 설정 파일 예시 (`application.yaml`)
Config Server의 설정 파일 예시는 다음과 같다.

```yaml
spring:
  application:
    name: demoConfigServer
  cloud:
    config:
      server:
        git:
          uri: <Git URL>  # Git repository 경로
          default-label: main
          force-pull: true
          clone-on-start: true
          basedir: <PATH>
          search-paths: /*
server:
  port: 8888
management:
  endpoints:
    web:
      exposure:
        include: refresh,health
logging:
  level:
    org.springframework.cloud: DEBUG
```
## Git Hook Script (post-receive)
Git repository의 post-receive 스크립트를 사용하여 푸시 이벤트를 감지하고, 백엔드 서버에 알림을 전송한다.

## 실행 방법
1. Config Server와 백엔드 서버를 실행한다.
2. Git의 `main` 브랜치에 변경 사항을 커밋하고 푸시한다.
3. Git repository의 `post-receive` hook이 실행되며, 백엔드 서버에 알림이 전송된다.
4. 백엔드 서버의 콘솔에 푸시 성공 메시지가 출력되며, 알림이 성공적으로 전송되었음을 확인할 수 있다.

## 참고 사항
- Config Server와 백엔드 서버가 같은 서버에서 실행되고 있는지, 네트워크 설정이 정확한지 확인한다.
- Git Hook의 `post-receive` 스크립트는 `main` 브랜치에 푸시될 때마다 실행되며, 백엔드 서버의 `/api/git/notify` 엔드포인트로 HTTP POST 요청을 전송한다.
- Config Server와 Git의 연동이 원활하게 이루어지도록 `application.yaml` 설정이 정확한지 확인한다.
