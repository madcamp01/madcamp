# WEWE

### Outline

---

<img src="https://github.com/madcamp01/madcamp/assets/70465226/96307eda-f687-41da-af61-83976ae17aff" width="300" height="550">

WEWE는 연락처가 있는 친친들의 취향을 엿볼 수 있는 앱입니다.

세 개의 탭은 각각 Contact, Feed, Map으로 구성되어 있습니다.

**개발환경**

- Android Studio (Kotlin)

### Team

---

기민수

[https://github.com/banna117](https://github.com/banna117)

박지은

[https://github.com/jieunyy](https://github.com/jieunyy)

### DB(roomDB)

### Details

---

### Intro & TabLayout

<img src="https://github.com/madcamp01/madcamp/assets/70465226/69281c0a-c1a5-4512-804a-c04d63b660da" width="300" height="550">

앱을 처음 실행했을 때 2초간 나타나는 로딩화면을 `splash`로 구현

<img src="https://github.com/madcamp01/madcamp/assets/70465226/bdb91ef5-903e-46b6-99c3-69763a14509e" width="700" height="100">

`TabLayout`을 적용해 `TabPagerAdapter`를 이용해 각 `Fragment`를 볼 수 있도록 구현

### Tab 1: 😀

내가 전화번호를 갖고 있는 친구들을 모아놓은 탭

# 상세 화면

---

# 구성 요소 : 검색 창, 사용자의 프로필 사진, 친구들의 프로필, 리뷰 작성 버튼

## 검색 창과 친구들의 프로필

- 이름 검색 기능

<img src="https://github.com/madcamp01/madcamp/assets/70465226/b1ba246f-6d57-453a-8169-77fd7bc9d0dc" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/38a2f260-0940-42f4-a600-ee84603e824e" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/bf1dd1a7-5a3b-4a4a-9552-ac550219146a" width="300" height="550">

## 친구들의 프로필 상세페이지(사진 1)

- 편집 버튼
    - 이름, 번호, 메모 변경 가능(사진 2)
    - 첫 화면으로 돌아가면 바뀐 이름과 메모 확인 가능(사진 5)
    - delete를 통해 연락처 삭제 가능(사진 6)
- 친구가 쓴 리뷰들
    - 클릭 시 리뷰 상세화면을 볼 수 있음(사진 4)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/a64eb4e9-ef4f-4f0a-8f1f-04261154410f" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/9937864c-f855-4d47-b25f-22dbae841d81" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/243ff611-a4b1-40dd-bdba-2bb6c7ce7c0f" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/57492dfb-0bba-4a14-91e4-d72fa54cd22c" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/f4c1ebb8-0f5f-4f6c-812c-319551771db1" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/4c64c53b-67ca-42fd-9ff3-b91f346f8232" width="300" height="550">

## 사용자의 프로필 탭

- 편집 버튼을 클릭해 편집 모드 활성화(사진 1, 2)
- 리뷰 편집 버튼을 클릭해 리뷰 편집 모드 활성화(사진 3)
    - 토글 버튼 클릭을 통해 리뷰 삭제 가능
    - 이미지 변경 방법 두 가지(사진 촬영, 이미지 선택)(사진 4)
- 변경된 사진과 삭제된 리뷰들(사진 5)
- 변경된 프로필 사진(사진 6, 우측 상단)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/63d89eef-0d0f-4294-855c-ac67e9b7d15e" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/60d781b6-bbcd-4ea2-b65e-371b3eb01636" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/7f6cfa5e-3a26-480c-b3fb-f28ec5d2073f" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/1ed1b9ed-c81f-4c7d-b91b-92d485bcbc4d" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/05cb44b3-3424-4bab-8ade-23dfeec51cde" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/99ef6941-256b-43d7-b86a-7722cfe67760" width="300" height="550">


## 리뷰 작성 페이지

- 리뷰 작성 페이지(사진 1)
- 이미지 선택시 사진 설정 가능(사진 2)
- 리뷰 작성 후 작성한 리뷰 프로필 탭에서 확인 가능(사진 3, 4)
- 작성한 리뷰 탭2에서 확인 가능(사진 5, 6)

<img src="https://github.com/madcamp01/madcamp/assets/70465226/390b68e5-d40d-42ef-87b4-0866a142ab22" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/20abd282-c3ae-4ba6-a1a4-4720dc7aa83a" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/a40e0a97-9791-46b0-9a1c-fc0ff6121c67" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/e9ea83e0-bb65-4883-954a-ff8c4d867d0b" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/4c59c4b3-58c4-4879-b556-ba894e647ce2" width="300" height="550">
<img src="https://github.com/madcamp01/madcamp/assets/70465226/156b9d9e-ef17-4176-9708-1a21680c83fe" width="300" height="550">


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

<img src="https://github.com/madcamp01/madcamp/assets/70465226/5e933577-79ee-4ea7-8116-7c0df8a528d0" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/8155134f-8cd4-411d-9935-bdfd6e13aab0" width="300" height="550">

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

<img src="https://github.com/madcamp01/madcamp/assets/70465226/5fca831c-0f59-4024-831e-bc4a34616249" width="300" height="550">

<img src="https://github.com/madcamp01/madcamp/assets/70465226/07d88980-afeb-4b40-a2ec-b25fa68efd95" width="300" height="550">

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
