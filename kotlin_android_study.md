안드로이드 코틀린 공부
======================
(공부하면서 정리 순서 무작위)
-----------------------------

## [1] 앱 아키텍쳐
#### 1) 모바일 앱 사용자 환경
- 일반적인 Android 앱에는 활동, 프래그먼트, 서비스, 콘텐츠 제공업체, broadcast receiver를 비롯하여 여러 앱 구성요소가 포함
- 개발자는 manifest파일에서 이러한 앱 구성요소 대부분을 선언하며, Android에서 이 파일을 사용하여 기기의 전반적인 사용자 환경에 앱을 통합하는 방법을 결정
- 사용자가 짧은 시간 내에 여러 앱과 상호작용할 때도 많다는 점을 고려하면, 앱은 사용자 중심의 다양한 워크플로 및 작업에 맞게 조정가능해야 함

- 휴대기기는 리소스가 제한되어 있으므로, 운영체제에서 새로운 앱을 위한 공간을 확보하도록 언제든지 일부 앱 프로세스를 종료가능해야 함

- 이러한 환경 조건을 고려해 볼 때 앱 구성요소는 개별적이고 비순차적으로 실행될 수 있으며, 운영체제나 사용자가 언제든지 앱 구성요소를 제거할 수 있어야 함
- 이러한 이벤트는 직접 제어할 수 없기 때문에 앱 구성요소에 애플리케이션 데이터나 상태를 저장해서는 안 되며 앱 구성요소가 서로 종속되면 안 됨

#### 2) 일반적인 아키텍처 원칙
- 관심사 분리 
  + 클래스를 최대한 가볍게 유지하며 클래스에 대한 의존성을 최소화 해야함
- 데이터 모델에서 UI도출
  + 데이터 모델은 앱의 데이터를 의미
  + 목적1) 리소스 확보를 위해 앱을 제거해도 사용자의 데이터가 삭제되지 않음
  + 목적2) 네트워크 에러가 생겨도 앱은 계속 작동

#### 3) 권장 앱 아키텍쳐
![image](https://user-images.githubusercontent.com/83804417/155471966-75136904-0793-480c-9f6f-5c25916404e7.png)
- UI Layer : 화면에 표현되는 레이어
  + 화면에 렌더링되는 UI 요소
  + 데이터를 보유하며 이를 UI에 노출하며 로직을 처리하는 상태 홀더(state holder) (ex. ViewModel 클래스)
- Data Layer : 비즈니스 로직을 포함하고 데이터를 노출하는 레이어
  + 앱의 데이터 생성, 저장, 변경 방식을 결정하는 규칙으로 구성
  + 다양한 repository 존재
- Domain Layer : UI Layer 와 Data Layer간의 상호작용을 간소화 및 재사용하기 위한 레이어
  + 선택사항(의무X)
  + 복잡한 비즈니스 로직과 여러 ViewModel에서 재사용되는 비즈니스 로직의 캡슐화를 담당

#### 4) 일반적인 권장사항
- Android클래스의 종속 항목을 줄인다
- 모듈간 경계를 확실히 정의한다
- 앱의 고유한 핵심에 초점을 맞춘다
- 앱의 각 부분을 독립적으로 테스트하는 방법을 고려한다
- 가능한 한 관련성이 높은 최신 데이터를 보존한다


## [2]코루틴

#### 1) 개념: 비동기적으로 실행되는 코드를 간소화하기 위해 Android에서 사용할 수 있는 concurrency design pattern
#### 비동기처리(async)를 순차적(sequential)인 코드로 진행할수있게끔 도와줌
- 기본스레드를 차단하여 앱이 응답하지 않게 만들 수도 있는 장기 실행 작업을 관리하는 데 도움
- 다시말해 메인스레드가 blocking되는 부분에 대해 도움을 줌
- 진입점과 출구가 각각 하나인 루틴(메소드)와 달리 진입점, 출구를 여러개 가짐
- 코루틴의 일반형이 루틴 이라는 표현도 있더라...
###### 실전개념 : 루틴의 중단  재개를 자유자재로 할수있는 기능, 중단되었던 시점다음에서 바로 재개가능

#### 2) 기능
- 경량 : 코루틴을 실행중인 스레드를 차단하지 않는 suspension(정지)을 지원하므로 단일 스레드에서 많은 코루틴을 실행할 수 있음
         suspension은 많은 동시 작업을 지원하면서도 blocking(차단)보다 메모리를 절약함
- 메모리 leak감소 : 구조화된 concurrency실행을 통하여 범위 내에서 작업 실행
- 기본으로 제공되는 cancellation(취소) 지원 : 실행중인 코루틴을 통해 자동으로 cancellation 전달
- JetPack통합 : 많은 JetPack라이브러리에 코루틴을 완전히 지원하는 extension이 포함되어있음
               구조화된 concurrency실행에 사용할 수 있는 자체 coroutine scope도 제공
###### 비유 : coroutines are light-weight threads
- 스레드와 비슷한 기능을 제공한다 
               
#### 3) 사용법(구글)

```
import kotlinx.coroutines.*

fun main(args: Array<String>) {
    GlobalScope.launch {
        delay(1000L)
        println("World")
    }

    println("Hello")
    Thread.sleep(2000L)
}
```
- launch부분 : 코루틴 빌더
  - launch 블럭에서부터 코루틴 생성 및 실행
  - launch를 생성하기 위해서는 scope설정이 필요
- scope : GlobalScope(전역스코프) -> "lifetime이 프로그램 전체"라는 뜻 
  - GlobalScope를 luanch하면 프로그램이 끝날때까지 계속 돌아감
- 참고 : 
  - delay는 suspend 메소드 : 일시중지, 코루틴 스코프 혹은 다른 suspend메소드 안에서만 실행 가능
  - sleep은 blocking 메소드


## [3]SharedPreference

#### 1) 개념 : key-value로 이루어진 딕셔너리 형태의 데이터를 저장 및 조회 할 수 있는 API
- value부분에 json형태로 이루어진 복잡한 데이터를 저장할 수 있음
- json형태의 데이터를 serialize 및 deserialize하기위해 Gson라이브러리가 필요 
- 

## [4]APK(Android Package Kit)

#### 1) 개념 : Android OS에 앱을 배포하는데에 사용되는 패키지파일의 확장(window의 .exe파일과 비슷)
- 어플리케이션의 모든 데이터가 포함됨
- 


## [5] Git 브랜치 방식

#### 1) 개념 : Git 브랜치를 효과적으로 나누고, 관리하는 방법

#### 2) 종류 : Git-flow 방식, GitHub-flow 방식

- Git-flow방식
  + 개념 : feature, develop, release, hotfix, master 5가지의 브랜치를 가지는 브랜치 전략
  + 브랜치란..? : 협업시 git을 사용하여 분산 버전관리를 하기 위해 독자적인 개발을 도와주는 도구
    * feature : 기능의 구현을 담당
    * develop : 개발을 진행하는 브랜치로, 중심적인 브랜치 / feature브랜치가 merge 될때마다 develop브랜치에 해당기능이 더해지며 살을 붙여간다
    * develop브랜치는 배포할 수준의 기능을 갖추면 release브랜치로 merge 됨
    * release : 개발된 내용을 배포하기 위해 준비하는 브랜치 / 배포준비가 되면 master로 merge, 충분한 테스트를 통해 버그를 검사하고 수정
    * hotfix : 배포된 소스에서 버그가 발생하면 생성되는 브랜치 / master브랜치에서 생성
    * master : 최종적으로 배포되는 가장 중심의 브랜치 / 
  + 특징 : 여러 브랜치들이 존재하고 각각의 상황이 명확하게 분류되지만, 그만큼 복잡하게 흐름을 만든다는 단점이 있음
  + 프로젝트의 규모가 커질수록 소스코드를 관리하기에 용이

- Github-flow방식
  + 개념 : master브랜치 하나만을 가지고 진행하는 방식
  + 특징 
    * master브랜치에 항상 업데이트 된 상태가 유지된다
    * 기능 구현이나 버그가 발생하면 issue를 작성한다
    * master브랜치에서 pull하고 기능구현하고, merge하는 과정의 반복
    * pull request과정에서 팀원간의 충분한 리뷰와 피드백이 진행되어야함, 그렇지 않으면 배포된 자체에서 버그가 발생할 수 있음


## [6] 다양한 메소드 정리

#### 1) init : 매개변수가 없고 반환되는 값이 없는 특별한 함수
- 사용법 : 
``` 
init {
  
  }
```
- 생성자를 통해 인스턴스가 만들어 질 때 호출되는 함수

## [7] LiveData

#### 1) 개념 : Activity, Fragment등의 LifeCycle을 인식하여 LifeCycle 내에서만 동작하는 요소

#### 2) 특징 
- LifeCycle이 종료되면 같이 삭제
- memory leak이 없음
- 수명주기에 따른 데이터 관리를 개발자가 하지 않아도 됨

#### 3) 사용법
- build.gradle(Module:app)에서 종속성 추가 후 LiveData를 사용할 곳에서 LiveData정의
```
var pageNumber = MutableLiveData<String>()
private var liveText : MutableLiveData<String> = MutableLiveData()
//LiveData는 추상클래스이기 때문에 LiveData class를 상속받은 MutableLiveData를 사용해야함
```

- LiveData에 Observer달기 : 
첫번째 매개변수인 this 는 lifeCycleOwner인 Activity를 의미(해당 액티비티)
두번째 매개변수인 Observer Callback은 LiveData(liveText)의 value의 변경을 감지하고 호출되는 부분

- LiveData의 value의 변경을 감지하고 호출
```
liveText.observe(this, Observer{
  
}
```

- 버튼을 누를때마다 번호가 증가하는 코드(예제)
```
private var count = 0 //button을 누르면 증가할 숫자
liveText.observe(this, Observer{
  text_test.text = it // it이 의미하는것 : LiveData의 value
})

btn_change.setOnClickListener{
  liveText.value = "${++count}" //liveText를 직접 변경하는게 아닌 liveText의 value값에 접근해서 작업해야함
}
```

## [8] 프래그먼트 생명주기
![image](https://user-images.githubusercontent.com/83804417/161879141-042e7fc2-4756-49c8-a06f-8fc2b73399ca.png)

#### 프래그먼트 콜백함수
- onCreate() : Fragment만 create된 상황, 이전에 onAttach()가 먼저 호출됨, Fragment View가 생성되지 않았기때문에 View와 관련된 작업은 하지 않음
- onCreateView() : onCreate()이후에 호출됨, onCreateView()로 인해서 정상적인 FragmentView 객체가 반환되었을때 FragmentView의 LifeCycle이 생성, 반환된 View객체는 onViewCreated()의 매개변수로 전달, Activity에서 onCreate()에 코드를 적는것과 유사
- onViewCreated() : 이 시점부터는 Fragment View의 생명주기가 초기화된 상태로 업데이트 되기때문에 View의 초기값을 설정해줄수있음, onCreateView로부터 전달받은 View를 가지고 설계
- onResume() : Fragment가 보이는 상태에서 모든 Animator와 Transition효과가 종료되고, 사용자와 상호작용할 수 있을때 호출됨
- onPause() : 사용자가 Fragment를 떠나기 시작했지만 Fragment는 여전히 visible일때 호출됨, 주의할점은 Fragment와 View의 LifeCycle은 Paused가 아닌 Started가 됨
- onStop() : 
- onDestroyView() : 
- onDestroy() : 

## [9] MVVM패턴
- Activity나 Fragment에 너무 많은 코드를 넣게 되면 점점 무거워져 다루기 힘들어지게됨을 방지
- 각각의 역할에 맞게 코드를 나누어 관리하기위한 디자인 패턴, 유지보수 용이, 개발효율 향상
- Model, View, ViewModel을 합친용어
- Model 
  * application에서 사용되는 데이터와 그 데이터를 처리하는 부분
  * ViewModel이 요청한 데이터를 반환
  * Room, Realm 과 같은 DB 사용이나 Retrofit 을 통한 백엔드 API 호출 (네트워킹) 이 보편적
  
- View 
  * 사용자에게 보여지는 UI부분
  * Activity, Fragment가 view역할을 함
  * ViewModel의 데이터를 관찰하여 UI갱신
- viewModel 
  * View를 표현하기 위해 만든 View를 위한 Model 
  * View를 나타내기 위한 데이터를 처리하는 부분
  * Model로 부터 요청한 데이터를 받음
  * View가 요청한 데이터를 Model로 요청함

#### 1) 동작순서
- 사용자들의 action이 view를 통해 들어온다
- view에 action이 들어오면, Command패턴으로 view model에 action을 전달
- view model은 model에게 데이터를 요청
- model은 view model에게 요청받은 데이터를 응답
- view model은 응답받은 데이터를 가공하여 저장
- view는 view model과 data binding하여 화면을 나타냄

#### 2) 장점
- View 와 Model사이의 의존성이 없음
- View 와 ViewModel사이의 의존성이 없음
- 각각의 부분이 독립적이기 때문에 모듈화 하여 개발 가능

#### 3) 단점
- ViewModel의 설계가 쉽지 않음

## [10] AAC
- Android Architecture Component
- 테스트와 유지보수가 쉬운 앱을 디자인할 수 있도록 돕는 라이브러리 모음
- JetPack의 섹션 중 하나

![image](https://blog.kakaocdn.net/dn/v1c7O/btq21oxPUUg/jM8Yd0JioonHLZdFMbeaFK/img.png)

  * ViewModel 
    + 앱의 lifeCycle을 고려하여 UI관련데이터를 저장하고 관리하는 컴포넌트, activity가 종료될때 까지 유지
    + 생명주기의 영향을 받지않고 데이터를 유지, 보관하기 위해 사용
  * LiveData
    + 기본 데이터베이스가 변경되면 뷰에 알리는 데이터 객체 빌드
    + activity, fragment의 생명주기를 알고 있는 간단한 observer
  * LifeCycle : 앱의 수명주기 관리



## 코틀린 문법

#### 1) ${} : 문자열 안에서 변수 바로 사용하기
```
println("a+b = ${a+b}") // a+b값이 문자열 안에 바로 들어감
```
Timber로 로그 찍어볼때도 쓰이는 그것
```
Timber.tag("surveyList").d("${surveyList}")
```

#### 2) apply, with, let, also, run
- 개념 : 객체를 사용할때 scope를 일시적으로 만들어서 속성이나 함수를 처리하는 용도로 사용되는 함수
- 특징 : 코드 간편화, 가독성, 후처리 등을 하는데에 유용



#### 3) 세이프 콜
-개념 : 값이 존재하지 않는 변수를 호출할때 발생하는  NullPointerException(NPE)를 해결하기 위한 문법


#### 4) "lateinit" vs "by lazy"
- 값을 나중에 초기화 해준다는 목적은 동일
- lateinit : var 로만 선언
- by lazy : val 로만 선언
- 상황에 따라 쓰이는 용도가 다름
- 초기화이후에 값이 변할수 있으면 lateinit
- 처음 초기화 된 이후 부터 계속 read-only로만 쓰이는 변수에는 by lazy를 사용하는것이 좋음

#### 5) open과 abstract의 차이
- open : 상속받을수있는 class / function
- abstract : 상속받아야만 하는 class / function

#### 6) Serialization(직렬화)
- 객체를 저장하거나 메모리, DB로 옮기기위해 직렬화 하는것
- 객체를 바이트 스트림으로 바꾸는 것
- 객체에 저장된 데이터를 Stream에 쓰기위해 연속적인 데이터로 변환하는 것
- 목적 : 객체를 상태 그대로 저장하고 필요할때 다시 생성하여 사용하는 것
- Serializable 인터페이스는 어떠한 멤버변수나 메소드도 가지지 않는 마커 인터페이스임, 해당 인터페이스를 구현한 클래스가 특정 기능을 한다는 것을 표시(mark)하기위해 쓰인다는 뜻

#### 7) Handler
- 워커스레드(!메인스레드)에서 UI처리를 해줄때 메인스레드와 연결해주기 위한것
- ※ 안드로이드에서 UI작업은 메인스레드에서 작업을 해야함, 워크쓰레드는 UI요소에 직접 접근할 수 없음
- ※ 안드로이드에서 main thread는 주로 UI작업, work thread는 백그라운드 작업을 함
- UI를 담당하는 메인쓰레드와 백그라운드 작업을 하는 워크쓰레드 사이에서 메신저 역할
