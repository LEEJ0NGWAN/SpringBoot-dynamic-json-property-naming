# Dynamic Request Json Property Naming

가변적인 리퀘스트 데이터 json에 대해 deserialization 및 validation 수행하는 샘플 프로젝트 입니다.

예를 들어, User DTO 객체의 필드로 lastName가 있다고 가정합니다.  
해당 필드를 파싱하기 위해서는 요청 데이터 json에 이름이 같은 프로퍼티 `lastName` 혹은 `last_name`을 고정적으로 받아와야 합니다.  
이런점을 개선하여 `lastName` 외에도 `surname` 및 `family_name` 처럼 다른 프로퍼티도 가변적으로 처리하여 동일한 필드로 설정해주고 동일한 검증을 수행할 수 있도록 처리해주는 커스텀 Deserializer를 구현합니다.

![](./dynamic%20json%20property.png)

이러한 설정은 yml 파일(혹은 환경변수)을 통해 변경할 수 있으며 소스 수정없이 애플리케이션 재기동 만으로도 api 요구 데이터 항목명을 쉽게 변경하여 기능 확장성 개선에 도움을 줍니다.
