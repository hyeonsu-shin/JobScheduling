# # 근사 알고리즘 (Approximation Algorithms)

​    

## NP-완전 문제의 해결

> - NP-완전 문제들은 실 세계의 광범위한 영역에 활용되지만,이문제들을 다항식 시간에 해결할 수 있는 알고리즘이 아직 발견되지 않았다.
>
> - 또한 아직까지 그 누구도 이 문제들을 다항식 시간에 해결할수 없다고 증명하지도 못했다.
>
> - 대부분의 학자들은 이 문제들을 해결할 다항식 시간 알고리즘이존재하지 않을 것이라고 추측하고 있다.
>
> - 이러한 NP-완전문제들을 어떤 방식으로든지 해결하려면 다음의 3가지 중에서 1가지는포기해야 한다.
>
>   –다항식시간에 해를 찾는 것
>
>   –모든입력에 대해 해를 찾는 것
>
>   –최적해를찾는 것
>
> - 근사 알고리즘은 NP-완전문제를 해결하기 위해 3 번째 것을 포기한다.
>
>   –즉,최적해에아주 근사한 (가까운)해를찾아주는 것이 근사 알고리즘 (Approximation algorithm)이다.

​    

# 근사 알고리즘

> - 근사 알고리즘은 근사해를 찾는 대신에 다항식 시간의 복잡도를가진다.
> - 근사 알고리즘은 근사해가 얼마나 최적해에 근사한지 (즉,최적해에얼마나 가까운지)를나타내는 근사 비율 (ApproximationRatio)을알고리즘과 함께 제시하여야 한다.
> - 근사 비율은 근사해의 값과 최적해의 값의 비율로서,1.0에가까울수록 정확도가 높은 알고리즘이다.
> - 그런데 근사 비율을 계산하려면 최적해를 알아야 하는 모순이생긴다.
> - 따라서 최적해를 대신할 수 있는 ‘간접적인’ 최적해를 찾고,이를최적해로 삼아서 근사 비율을 계산한다.

​    

# 간단한 설명 과 종류

> - 여행자문제를 위한 근사 알고리즘은 최소 신장 트리의 모든 점을 연결하는 특성과 최소 가중치의 특성을 이용한다.근사비율은2이다.
> - 정점커버 문제를 위한 근사 알고리즘은 그래프에서 극대 매칭을 이용하여 근사해를 찾는다. 근사비율은 2 이다.
> - 통채우기 문제는 최초 적합 (first fit), 다음적합 (nextfit), 최선적합 (bestfit), 최악적합 (worstfit)과같은 그리디 알고리즘으로 근사해를 찾는다. 근사비율은 각각 2이다.
> - 작업스케줄링 문제는 가장 빨리 끝나는 기계에 새 작업을 배정하는 그리디 알고리즘으로 근사해를 찾는다.근사비율은2이다.
> - 클러스터링문제는 현재까지 정해진 센터에서 가장 멀리 떨어진 점을 다음 센터로 정하는 그리디 알고리즘으로 근사해를 찾는다.근사비율은2이다.

​    

# 작업 스케줄링 문제

> - 작업 스케줄링(Job Scheduling)
>
>    -n개의작업,각작업의 수행 시간 ti, i =1, 2, 3, ⋯,n, 
>
>   -그리고m개의동일한 기계가 주어질 때, 
>
>   -모든작업이 가장 빨리 종료되도록 작업을 기계에 배정하는 문제이다.
>
>   -단,한작업은 배정된 기계에서 연속적으로 수행되어야 한다.
>
>   -또한기계는 1번에하나의 작업만을 수행한다.

​    

> - 작업을 어느 기계에 배정하여야 모든 작업이 가장 빨리종료될까?
>
>   –이에대한 간단한 답은 그리디 방법으로 작업을 배정하는 것이다.
>
>   –즉,현재까지배정된 작업에 대해서 가장 빨리 끝나는 기계에 새 작업을 배정하는 것이다.
>
>
> - 아래 예제에서는 2번째 기계가 가장 빨리 작업을 마치므로, 새 작업을 2번째 기계에 배정한다.
>
> ![작업스케줄링 1](https://user-images.githubusercontent.com/80369791/118576115-d630e880-b7c2-11eb-8bec-db7e62eb2f03.PNG)

​    

# 소스코드

> ```
> public static void main(String[] args) {
>             int n = 8;
>             int m = 16;
>             int[] t = new int[n];
>             Random random = new Random();
>             System.out.print("작업 시간 : ");
>             for (int i = 0; i < n; i++) {
>                 t[i] = random.nextInt(30) + 1;
>                 System.out.printf("%d ", t[i]);
>             }
>             System.out.println();
>             System.out.println(schedule(n, m, t));
>         }
> ```
>
> ```
> public static int schedule(int n, int m, int[] t) {
>             int[] L = new int[m];
>             for (int j = 0; j < m; j++) {
>                 L[j] = 0;
>             }
>             for (int i = 0; i < n; i++) {
>                 int min = 0;
>                 for (int j = 1; j < m; j++) {
>                     if (L[j] < L[min]) {
>                         min = j;
>                     }
>                 }
>                 L[min] = L[min] + t[i];
>             }
>
>             int max = L[0];
>             for (int i = 1; i < m; i++) {
>                 if (L[i] > max) {
>                     max = L[i];
>                 }
>             }
>             return max;
>         }
> ```

# 결과

> n={8,10,12,14,16}, m은 16으로 고정 t는 1~30 사이의 랜덤 값
>
> n=8 ![결과 8](https://user-images.githubusercontent.com/80369791/118578592-6113e200-b7c7-11eb-95d4-65f51b10e251.PNG)
>
> n=10![결과 10](https://user-images.githubusercontent.com/80369791/118578593-6113e200-b7c7-11eb-92bc-267a628c48af.PNG)
>
> n=12![결과 12](https://user-images.githubusercontent.com/80369791/118578594-61ac7880-b7c7-11eb-9700-3d372631c788.PNG)
>
> n=14![결과 14](https://user-images.githubusercontent.com/80369791/118578595-61ac7880-b7c7-11eb-94f8-616611f32c12.PNG)
>
> n=16![결과 16](https://user-images.githubusercontent.com/80369791/118578590-5fe2b500-b7c7-11eb-87e0-ecdadb48faa0.PNG)

​    

# 알고리즘

> –n개의작업을 하나씩 가장 빨리 끝나는 기계에 배정한다.
>
> –이러한기계를 찾기 위해 알고리즘의 line 5~7의for-루프가(m-1)번수행된다.
>
> –즉,모든기계의 마지막 작업 종료 시간인 L[j]를살펴보아야 하므로 O(m)시간이걸린다.

# 시간 복잡도

> –n개의작업을 배정해야하고,
>
> –line10에서배열 L을탐색해야하므로 
>
> –nx O(m) + O(m) = O(nm) 이다.

# 근사비율

> - Approx_JobScheduling 알고리즘의근사해를OPT'라하고,최적해를OPT라고할 때,        OPT' ≤ 2xOPT 이다.
>
>   –즉,근사해는최적해의 2배를넘지 않는다.
>
>   –이를다음 그림을 통해서 이해해보자. 단,ti는작업 i의수행 시간이다.
>
> #### ![작업스케줄링 2](https://user-images.githubusercontent.com/80369791/118577698-de3e5780-b7c5-11eb-8643-089b150e58dc.PNG)
>
> - 위의 그림은 Approx_JobScheduling알고리즘으로작업을 배정하였고,
> - 가장 마지막으로 배정된 작업 i가T부터수행되며,
> - 모든 작업이 T+ti에 종료된 것을 보이고 있다.
> - 그러므로 OPT'= T+ti이다.
>
> ![작업스케줄링 3](https://user-images.githubusercontent.com/80369791/118577699-de3e5780-b7c5-11eb-8aba-4627c07c3986.PNG)
>
> - 위 그림에서 T’는작업 i를제외한 모든 작업의 수행 시간의 합을 기계의 수 m으로 나눈 값이다.
>
>   –즉,T'는작업 i를제외한 평균 종료 시간이다.
>
> - 그러면 T≤T'이된다.
>
>   –왜냐하면작업 i가배정된 (가장늦게 끝나는)기계를제외한 모든 기계에 배정된 작업은 적어도 T 이후에종료되기 때문이다.
>
> - T와 T'의 관계인, T ≤T'를이용한 OPT' ≤ 2xOPT증명
>
> ![작업스케줄링 4](https://user-images.githubusercontent.com/80369791/118577693-dc749400-b7c5-11eb-98b8-61889c3339f8.PNG)
>
> - 첫 번째 부등식 ①
>
>   –위의그림에서 살펴본 T ≤ T'을이용한 것이다.
>
> - 식 ②로의 변환
>
>   –최적해OPT는모든 작업의 수행 시간의 합을 기계의 수로 나눈 값 (평균 종료시간)보다같거나 크고 또한 하나의 작업 수행 시간과 같거나 크다는 것을 부등식에 반영한 것이다.
