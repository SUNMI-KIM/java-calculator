# 문자열 덧셈 계산기

### 문제 요구 사항

입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
- 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 예시

| 입력 | 결과 |
| --- | --- |
| "" | 0 |
| "1,2" | 3 |
| "1,2:3" | 6 |
| "//;\n1;2;3" | 6 |

## 동작 시나리오


1. **사용자 입력 단계**
    - 사용자가 문자열을 입력합니다.
        - `"//;\n1;2;3"` → 커스텀 구분자(`;`)를 인식 후 파싱 단계로 이동
        - `"1,2:3"` → 기본 구분자(`,`, `:`)를 사용하므로 바로 파싱 단계로 이동
2. **입력 검증 단계**
    - `Check` 클래스에서 입력 문자열의 형식을 검증합니다.
        - `//`와 `\n`의 위치가 올바른지 확인
        - 음수, 중복 구분자(`1::2:3`), 숫자형 구분자 등 잘못된 입력 시 `IllegalArgumentException` 발생
3. **파싱 단계**
    - `Parser` 클래스에서 구분자를 기준으로 문자열을 분리합니다.
    - 기본 구분자(`,`, `:`) 또는 커스텀 구분자(`;`, `#` 등)를 사용해 숫자 문자열을 추출합니다.
4. **계산 단계**
    - `Calculator` 클래스가 파싱된 숫자 배열을 받아 모든 값을 더합니다.
    - `Stream`을 이용해 리스트 내 모든 요소를 합산합니다.
5. **출력 단계**
    - `InputOutput` 클래스에서 계산된 결과를 출력합니다.
    - 사용자는 콘솔에서 최종 결과를 확인할 수 있습니다.

## 프로젝트 구조


![](https://velog.velcdn.com/images/hariaus/post/04fd4511-91b0-4c90-83f7-ef09cb730235/image.png)

```
src
 └─ main
     └─ java
         └─ calculator
             ├─ calculator/
             │   └─ Calculator.java        # 덧셈 로직 수행
             ├─ check/
             │   └─ Check.java             # 입력 형식 및 예외 검증
             ├─ facade/
             │   └─ Facade.java            # 프로그램 전체 실행 흐름 관리
             ├─ factory/
             │   └─ Factory.java           # 객체 생성 책임 분리
             ├─ io/
             │   └─ InputOutput.java       # 입출력 담당
             ├─ parser/
             │   └─ Parser.java            # 문자열 및 구분자 파싱
             └─ Application.java           # main 실행 진입점
```

## 기능 요구 사항


| 카테고리 | 기능 | 설명 |
| --- | --- | --- |
| 입출력 | 문자열 입력 | //(구분자)\n(문자열)의 형태를 파싱 |
| 입출력 | 숫자 출력 | 합산된 숫자 출력 |
| 문자열 파싱 | 문자열 파싱 | 구분자를 통해 문자열에서 숫자만 추출 |
| 문자열 파싱 | 문자열과 숫자 문자열 분리 | \n을 통해 문자열과 숫자 문자열 분리, 빈 문자열은 기본 구분자 반환 |
| 문자열 파싱 | 문자열을 통한 정규표현식 생성 | 문자열에 기본 구분자를 더해 정규표현식 생성 |
| 계산 | 덧셈 | 파싱된 숫자 리스트의 합을 계산 |
| 결과 출력 | 결과 출력 | 계산 결과를 콘솔에 출력 |
| 검증 | 포맷 검사 | 잘못된 형식(//나 \n이 누락 등) 시 예외 발생 |
| 검증 | 커스텀 구분자 검사 | 기본 구분자 외의 다른 구분자 입력 시 예외 발생 |

## 예외 처리


| 예외 상황 | 예외 타입 | 설명 |
| --- | --- | --- |
| 음수 입력 | IllegalArgumentException | 모든 숫자는 양수여야 함 |
| 커스텀 구분자가 숫자인 경우 | IllegalArgumentException | 구분자와 숫자 혼동 방지 |
| 잘못된 포맷 (`//`나 `\n` 둘 중 하나만 누락) | IllegalArgumentException | 입력 형식 유효성 검사 |
| 중복 구분자 (`1::2:3`) | IllegalArgumentException | 구분자 중복 방지 |
| 커스텀 구분자가 없는 경우(`//;\\n1!2:3`) | IllegalArgumentException | 커스텀 구분자가 숫자 문자열에 없는 경우 |
| 숫자 문자열에 문자가 들어가 있는 경우(`1,a,3`) | IllegalArgumentException | 숫자 + 구분자 조합으로 구성되어야 함 |

## 핵심 설계 및 고민 과정



### 1️⃣ Facade 패턴 적용

- 여러 클래스로 분리된 로직을 하나의 진입점(`Facade`)에서 관리하도록 설계했습니다.
- 사용자는 `Facade.run()` 한 줄로 프로그램 전체를 실행할 수 있습니다.
- 이를 통해 복잡한 내부 구조를 감추고, 유지보수를 용이하게 했습니다.

```java
public class Facade {
    private final InputOutput inputOutput;
    private final Check check;
    private final Parser parser;
    private final Calculator calculator;

    public void run() {
        final String inputString = inputOutput.input();
        check.checkFormat(inputString);
        final String[] regExpAndNumber = parser.parseRegExpNumber(inputString);
        final String regExp = regExpAndNumber[0];
        final String numString = regExpAndNumber[1];
        check.checkNumberFormat(numString, regExp);
        final List<Long> numList = parser.parseNumber(numString, regExp);
        inputOutput.output(calculator.add(numList));
    }
}
```

---

### 2️⃣ Factory Method로 객체 생성 책임 분리

- `Facade` 내부에서 `new` 예약어를 사용하지 않도록 `Factory` 클래스를 별도로 분리했습니다.
- 객체 생성 로직이 변경되어도 `Factory`만 수정하면 되므로 결합도가 낮아집니다.

```java
public class Factory {
    public static Facade createCalculatorFacade() {
        final InputOutput io = new InputOutput();
        final Check check = new Check();
        final Parser parser = new Parser();
        final Calculator calculator = new Calculator();
        return new Facade(io, check, parser, calculator);
    }
}
```

---

### 3️⃣ 테스트 코드 기반 개발 (TDD)

- **Postman 없이 순수 Java 환경**에서 테스트를 수행하기 위해 JUnit5를 적극적으로 활용했습니다.
- 예외 처리, 다양한 입력값 검증, 커스텀 구분자 파싱 등을 테스트 케이스로 작성했습니다.

### 예시 — 합산 기능 테스트

```java
@DisplayName("양수 리스트를 더하면 올바른 합을 반환한다.")
@Test
void addPositiveNumbersInList() {
    Calculator calculator = new Calculator();
    List<Long> list = List.of(1L, 2L, 3L);
    assertEquals(6L, calculator.add(list));
}
```

### 예시 — 예외 발생 테스트

```java
@DisplayName("음수 문자열을 파싱하면 에러를 발생시킨다")
@Test
void parseNumbers_WithNegativeString_ThrowsException() {
    Parser parser = new Parser();
    assertThrows(IllegalArgumentException.class,
        () -> parser.parseNumber("1;2,-3", "[;,]"));
}
```

---

### 4️⃣ 클린 코드 & 컨벤션

- [Woowacourse Java Style Guide](https://github.com/woowacourse/woowacourse-docs/tree/main/styleguide/java)를 참고하여 작성했습니다.
- IntelliJ 자동 포매팅 및 코드 검사 설정을 통해 일관성 있는 코드 스타일을 유지했습니다.
- `else` 예약어 사용 금지, 원시값 포장, 일급 컬렉션 적용 등 객체지향적 설계를 실천했습니다.

## 세부 설계에서의 판단과 선택



- **숫자 문자열과 구분자 문자열을 분리했을 때, 하나라도 없다면 어떻게 처리할지**
    
    → `Parser` 객체에서 `inputString.startsWith("//")` 조건을 통해 구분
    
    → `//`로 시작하지 않으면 전체를 숫자 문자열로 처리
    
- **`Check` 클래스의 역할이 `Facade` 내부에서 적절히 위치했는가**
    
    → `Check` 객체가 `Facade`의 중간에서 입력을 검증하는 구조에 대한 고민
    
    → 검증 로직을 `Parser` 내부로 옮길 경우 **검증과 파싱 간의 의존성이 강해져 역할이 모호해지는 문제** 발생
    
    → **각 객체의 책임을 명확히 분리하기 위해 현재 구조 유지 결정**
    
- **정규표현식을 사용하지 않았다면 어떤 대체 방법을 썼을지**
    
    → 반복문(`for`)으로 문자를 하나씩 검사하는 방법 고려
    
    → 비효율적이라고 판단하여 정규표현식 사용 결정