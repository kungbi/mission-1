### 미션 해결 전략 
#### 1. 본인이 이해하고 구현한 내용에 기반해 '다른 근무자와 순서를 바꿔야 하는 경우'를 자신만의 예시를 들어 설명하세요. (필수)       

예를 들어서 금요일(평일)에 A가 근무를 했고, 다음날 토요일에 순서상 A가 근무를 해야한다면 휴일 순상 다음에 있는 근무자가 배치됩니다.

#### 2. 요구사항에서 제시한 앞의 날짜부터 순서를 변경하는 방법 외에 다른 방법이 있다면 어떤 방식이 있는지, 이 방법은 기존에 제시된 방식과 비교해 어떤 차이가 있는지 설명하세요. (선택)

저는 큐를 사용했습니다.
순서가 밀린 인원은 큐에 배치가 되고, 다음 근무자 배치시 해당 큐에 있는 근무자부터 우선적으로 배치됩니다.