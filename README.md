# e-commerce
### [API DOCUMENT 바로가기](https://seokjun7410.github.io/e-commerce/redoc-2402151414.html)

# 주요사항
- 반복적인 RestDocs 코드를 Reflection API을 통해 테스트 코드 작성시간을 단축.
- 테스트 컨텍스트 로딩시간을 최소화하여 테스트 실행시간 개선 ( 30s - 21s )
- 테스트 컨테이너를 통해 운영 인프라와 비슷한 조건에서 테스트를 실행하여 테스트 정확성 향상
- API 테스트코드 기반으로 API 문서를 자동 생성, 가독성을 위해 Redocly UI 사용

# 테스트 코드 작성시간 단축
### RestDocs 방식의 코드작성을 DTO 클래스를 기반 리플렉션으로 구성하여 테스트코드 작성시간 단축
![스크린샷 2025-03-14 오후 4 58 07](https://github.com/user-attachments/assets/a4915712-0b75-4e18-a154-7a31166e0af6)


## 1. Docs 클래스 구성
 **DTO 클래스 객체**를 이용해서 RestDocs방식의 **명세를 생성**

<img width="400" alt="스크린샷 2024-08-29 오전 11 30 59" src="https://github.com/user-attachments/assets/b72c5226-2cbc-413c-9c83-87c22a440d0f">


## 2. DTO 계층에서 명세를 정의

<img width="400" alt="스크린샷 2024-08-29 오전 11 31 30" src="https://github.com/user-attachments/assets/f059e5a8-6aa6-4ecf-9072-e9976d08fcc3">

1. DTO 필드에 대한 설명은 **테스트 코드 영역이 아닌 DTO에** 커스텀 어노테이션을 통해 기재,
2. @DocsDesciption 을 통해 **nullable 여부를 표시** 가능 
3. **jakarta.validation** 을 이용할경우 유효성 검증과 더불어 **API명세도 자동으로 반영** 되도록 구성(swagger와 유사)
4. **Enum타입의 필드** 일경우 입력 가능한 값을 **자동으로 API문서에 노출**




# [자동 생성된 API문서]
<img width="600" alt="스크린샷 2024-08-29 오전 11 37 00" src="https://github.com/user-attachments/assets/ae30f248-ba7e-4749-8956-52b0f264b402">






# git 커밋 규칙 설정

 타입 이름      |내용|
|------------|---|
| 🔨FEAT     |새로운 기능에 대한 커밋|
| 🛠️FIX     |버그 수정에 대한 커밋|
| 🧱BUILD    |빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋|
| 🤖CI       |ci 관련 수정 커밋|
| 📚DOCS     | 문서 수정에 대한 커밋|
| 🚿STYLE    | 코드 스타일 혹은 포맷 등에 관한 커밋|
| 🪛REFACTOR | 코드 리팩토링에 대한 커밋|
| 🔬TEST     | 테스트 코드 수정에 대한 커밋|
| 📈PERF     |성능 개선에 대한 커밋|
| 📄CHORE    | 그 외 자잘한 커밋|


# 상품 ERD 작성
<img width="881" alt="ERD-초안" src="https://github.com/seokjun7410/e-commerce/assets/47974623/c0b1422a-e2ee-4f80-bdd5-b322b9fd92e0">
