# WEWE

### Outline

---

<img src="https://github.com/madcamp01/madcamp/assets/70465226/c3920f71-d96c-4936-b46b-ba55e910beac" width="300" height="550">

WEWE는 연락처가 있는 친친들의 취향을 엿볼 수 있는 앱입니다.

세 개의 탭은 각각 Contact, Feed, Map으로 구성되어 있습니다.

**개발환경**

- Android Studio (Kotlin)

### Team

---

기민수

[https://github.com/banna11](https://github.com/banna117)7

박지은

[https://github.com/jieunyy](https://github.com/jieunyy)

### DB(roomDB)

### Details

---

### Intro & TabLayout

<img src="https://github.com/madcamp01/madcamp/assets/70465226/ec730e4d-1ef3-4c0e-b1a5-810e2ca4e1a0" width="300" height="550">

앱을 처음 실행했을 때 2초간 나타나는 로딩화면을 `splash`로 구현

<img src="https://github.com/madcamp01/madcamp/assets/70465226/01af92c0-7fb5-46c8-a146-a13dc3fcad6d" width="300" height="550">

`TabLayout`을 적용해 `TabPagerAdapter`를 이용해 각 `Fragment`를 볼 수 있도록 구현

### Tab 1: 😀

내가 전화번호를 갖고 있는 친구들을 모아놓은 탭

# 상세 화면

---

# 구성 요소 : 검색 창, 사용자의 프로필 사진, 친구들의 프로필, 리뷰 작성 버튼

## 검색 창과 친구들의 프로필

- 이름 검색 기능

<img src="https://github.com/madcamp01/madcamp/assets/70465226/525b8490-3210-4a41-92be-8bedbbc62337" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/525de276-c9cf-49fd-8bd7-55f4eb11952b" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/6edd0736-d21a-4f5e-91fa-7ca502c6c131" width="300" height="550">

## 친구들의 프로필 상세페이지(사진 1)

- 편집 버튼
    - 이름, 번호, 메모 변경 가능(사진 2)
    - 첫 화면으로 돌아가면 바뀐 이름과 메모 확인 가능(사진 5)
    - delete를 통해 연락처 삭제 가능(사진 6)
- 친구가 쓴 리뷰들
    - 클릭 시 리뷰 상세화면을 볼 수 있음(사진 4)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/0c1229ad-c2ca-4b2b-9444-0067f7c780a8" width="300" height="550">

1

<img src="https://github.com/madcamp01/madcamp/assets/70465226/733af3dd-ea36-4211-9ff1-68323fa1093f" width="300" height="550">

4

<img src="https://github.com/madcamp01/madcamp/assets/70465226/9b57786b-407d-48c4-8839-0d514233caf7" width="300" height="550">

2

<img src="https://github.com/madcamp01/madcamp/assets/70465226/602b1158-d116-41d6-9339-fd8ccc169749" width="300" height="550">

5

<img src="https://github.com/madcamp01/madcamp/assets/70465226/4e9ff45e-e5b3-495c-9765-615c8d0e75ea" width="300" height="550">

3

<img src="https://github.com/madcamp01/madcamp/assets/70465226/37d7a82d-db5f-4407-96c0-13425807e396" width="300" height="550">

6

## 사용자의 프로필 탭

- 편집 버튼을 클릭해 편집 모드 활성화(사진 1, 2)
- 리뷰 편집 버튼을 클릭해 리뷰 편집 모드 활성화(사진 3)
    - 토글 버튼 클릭을 통해 리뷰 삭제 가능
    - 이미지 변경 방법 두 가지(사진 촬영, 이미지 선택)(사진 4)
- 변경된 사진과 삭제된 리뷰들(사진 5)
- 변경된 프로필 사진(사진 6, 우측 상단)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/38e4b2de-d2e4-49ac-8481-a966ea7894d2" width="300" height="550">

1

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-183225_WEWE.jpg" width="300" height="550">

4

<img src="https://github.com/madcamp01/madcamp/assets/70465226/bfe9f18e-bf19-4219-b774-6b91acdb658e" width="300" height="550">

2

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-192205_WEWE.jpg" width="300" height="550">

5

<img src="https://github.com/madcamp01/madcamp/assets/70465226/0c1229ad-c2ca-4b2b-9444-0067f7c780a8" width="300" height="550">

3

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-192321_WEWE.jpg" width="300" height="550">

## 리뷰 작성 페이지

- 리뷰 작성 페이지(사진 1)
- 이미지 선택시 사진 설정 가능(사진 2)
- 리뷰 작성 후 작성한 리뷰 프로필 탭에서 확인 가능(사진 3, 4)
- 작성한 리뷰 탭2에서 확인 가능(사진 5, 6)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/59e52367-9ccf-484d-8ff0-b1cdb25bc45e" width="300" height="550">

1

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-192455_WEWE.jpg" width="300" height="550">

4

<img src="https://github.com/madcamp01/madcamp/assets/70465226/f2ec7f2b-8056-4e5e-9382-f287e85f7711" width="300" height="550">

2

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-192506_WEWE.jpg" width="300" height="550">

5

<img src="https://github.com/madcamp01/madcamp/assets/70465226/60c3fdc1-be63-4222-96ec-093e147a44dd" width="300" height="550">

3

<img src="https://github.com/madcamp01/madcamp/assets/70465226/Screenshot_20240703-192512_WEWE.jpg" width="300" height="550">

6

상세 설명

---

**RecyclerView를 이용하여 친구들의 게시글을 목록 형태로 표시**

- **ContactAdapter를 사용하여 연락처 목록을 표시**:
    - `ContactAdapter`는 연락처 데이터를 표시하는 어댑터로, 각 연락처에는 이름(사용자 지정), 전화번호(user 지정), 상태(사용자 지정) 등으로 구성
    - `RecyclerView`를 이용하여 연락처 목록을 스크롤 가능한 형태로 표시합니다.

**LifecycleScope를 이용하여 비동기 작업 처리**:

- `AppDatabase`에서 연락처 데이터를 불러옵니다.
- 연락처 목록을 비동기적으로 불러와 UI에 설정합니다.

**FirstFragment에서 화면 구성 및 데이터 로드 관리**:

- `onCreateView`에서 `RecyclerView`를 초기화하고, `LinearLayoutManager`로 레이아웃을 설정합니다.
- `loadContacts` 함수에서 데이터베이스에서 연락처를 불러와 `RecyclerView`에 설정합니다.
- 검색 기능을 구현하여 입력된 텍스트에 따라 연락처를 필터링합니다.

**연락처의 프로필 이미지를 클릭하면 ProfileActivity로 이동하여 프로필 정보를 수정할 수 있음**:

- 편의상 ID 값이 1인 사람을 사용자로 지정
- `ProfileActivity`에서 프로필 정보를 표시하고 수정할 수 있습니다.

**연락처의 상세 정보를 보기 위해 ContactDetailActivity로 이동**:

- `Intent`를 사용하여 연락처 ID와 이름을 전달
- `ContactDetailActivity`에서 해당 연락처의 리뷰 목록과 상세 정보를 표시

**리뷰 작성 기능 구현**:

- `AddReviewActivity`를 통해 새로운 리뷰를 작성 및 저장
- 이미지를 첨부하거나 촬영하여 리뷰에 포함
    - Manifest 파일에 권한 명시
    - 권한 없을 시에 권한 허용 Dialog 팝업

---

### Tab 2: Feed

친구들의 게시글만 모아 볼 수 있는 탭😀

<img src="https://github.com/madcamp01/madcamp/assets/70465226/54d0ea34-0c61-4010-8848-2bf74d5eec88" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/0e3739e7-e2a1-438c-bd9e-9f4b2d12be24" width="300" height="550">

**`RecyclerView`를 이용하여 친구들의 게시글을 목록 형태로 표시**

- `ImageAdapter`와 `ReviewAdapter`를 사용하여 게시글과 리뷰 목록을 표시
- 각 게시글에는 이미지, 작성자, 내용, 별점, 작성일 등을 포함

**게시글 이미지를 클릭하면 `FullScreenImageActivity`로 이동하여 전체 화면으로 이미지와 관련 리뷰를 볼 수 있음**

- `Intent`를 사용하여 이미지 URI, 이미지 ID, 리뷰 ID를 전달
- `FullScreenImageActivity`에서 이미지를 전체 화면으로 표시하고, 관련 리뷰를 `RecyclerView`로 표시

**`LifecycleScope`를 이용하여 비동기 작업 처리**

- `AppDatabase`에서 게시글 데이터를 불러옴
- 게시글과 관련된 이미지를 비동기적으로 불러와 UI에 설정

**`AppDatabase`, `ImageDao`, `ReviewDao`를 사용하여 게시글과 리뷰 데이터를 관리**

- 게시글과 이미지를 데이터베이스에서 불러와 `Pair<Review, Image?>` 형태로 리스트 관리
- 게시글 목록을 최신 순으로 정렬하여 표시

**게시글 작성자 프로필 이미지를 클릭하면 `ContactDetailActivity`로 이동하여 작성자 정보를 볼 수 있음**

- `Intent`를 사용하여 작성자 ID와 이름을 전달
- `ContactDetailActivity`에서 작성자 정보를 표시

---

### Tab 3: Review

지도에서 친구들의 리뷰를 확인할 수 있는 탭😎

<img src="https://github.com/madcamp01/madcamp/assets/70465226/5b8ec994-d3aa-4119-8798-8fc9227a60b5" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/5c216414-ca8e-45ae-915e-3ef17099d475" width="300" height="550">

**`MapView`를 이용하여 지도 상에 표시된 장소와 해당 장소의 리뷰를 볼 수 있음**

- `KakaoMap`을 활용하여 지도 초기화
- 라벨 클릭 시 해당 장소 ID를 기준으로 리뷰를 보여줌

**`Intent`를 사용하여 라벨 클릭 시 리뷰 목록을 보여주는 액티비티로 이동**

- 클릭된 라벨의 위치를 기반으로 해당 장소의 리뷰를 모두 불러옴
- 불러온 리뷰 목록과 장소 이름을 `Serializable`로 인텐트에 담아 전달

**`RecyclerView`를 이용하여 선택된 장소의 리뷰 목록을 표시함**

- `ReviewAdapter`와 `ReviewViewHolder`를 사용하여 리뷰 목록을 표시
- 각 리뷰는 작성자, 내용, 별점, 작성일, 이미지 등을 포함

**`GlobalScope`와 `Dispatchers`를 사용하여 이미지 URI를 비동기적으로 불러와 `ImageView`에 설정**

- 리뷰 이미지가 존재하는 경우 해당 이미지를 표시
