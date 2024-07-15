# 💤 KKUMULKKUM-SERVER

> 이 세상 모든 지각쟁이 꾸물이들의 정시 도착 꿈을 이루어줄 꾸물꿈

## 💤 Developers

|                                           박상준                                            |                                            김채원                                            |
|:----------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------:|
| <img width="300" alt="image" src="https://avatars.githubusercontent.com/u/74230343?v=4"> | <img width="300" alt="image" src="https://avatars.githubusercontent.com/u/113420297?v=4"> | 
|                        [tkdwns414](https://github.com/tkdwns414)                         |                            [chaewonni](https://github.com/chaewonni)                            |


## 💤 Role Distribution
<div markdown="1">  

|      기능명       |    담당자    |
|:--------------:|:---------:|
|   프로젝트 기초 세팅   | `상준` `채원` |
| EC2 세팅, RDS 세팅 | `상준` `채원`  |
|    세부 준비 작업    | `상준` `채원` |
|    공동 코드 작업    | `상준` `채원` |
|     API 작업     | `상준` `채원` |
|       배포       | `상준` `채원` |

</div>

세부 내용은 추후 추가될 수 있음

## 💤 ERD

![KKUM_ERD](https://github.com/OMZigak/KKUM_SERVER/assets/74230343/a4d5b4b4-002e-4484-b31c-1c952dacf665)

## 💤 DEPLOYMENT ARCHITECTURE

![꾸물꿈 배포 아키텍쳐](https://github.com/user-attachments/assets/fa9d622f-0c26-440b-8e8f-bbeebfad7908)

## 💤 FOLDERING
```plaintext
📦KKUM_SERVER
 ┣ 📂.github
 ┃ ┣ 📂ISSUE_TEMPLATE
 ┃ ┃ ┗ 📜issue_template.md
 ┃ ┣ 📂workflows
 ┃ ┃ ┣ 📜dev-cd.yml
 ┃ ┃ ┗ 📜dev-ci.yml
 ┃ ┗ 📜pull_request_template.md
 ┣ 📂SERVER_YML // (submodule)
 ┣ 📂gradle
 ┣ 📂src.main.java.org.kkumulkkum.server
 ┃ ┣ 📂advice
 ┃ ┣ 📂annotation
 ┃ ┣ 📂auth
 ┃ ┣ 📂config
 ┃ ┣ 📂constant
 ┃ ┣ 📂controller
 ┃ ┣ 📂domain
 ┃ ┣ 📂dto
 ┃ ┣ 📂exception
 ┃ ┣ 📂external
 ┃ ┣ 📂repository
 ┃ ┗ 📂service
 ┣ 📜.gitignore
 ┣ 📜.gitmodules
 ┣ 📜Dockerfile-dev
 ┣ 📜README.md
 ┣ 📜build.gradle
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┗ 📜settings.gradle
```