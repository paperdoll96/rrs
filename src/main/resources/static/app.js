// API 기본 URL 설정
const apiUrl = 'http://localhost:8080';

// 게시글 목록 불러오기 (기존 레스토랑 목록)
async function fetchRestaurants() {
  try {
    const response = await fetch(`${apiUrl}/restaurants`);
    const posts = await response.json();
    displayPosts(posts);
  } catch (error) {
    console.error('Error fetching posts:', error);
  }
}

// 게시글 목록을 HTML에 표시하는 함수 (Bootstrap 카드)
function displayPosts(posts) {
  const postList = document.getElementById('restaurant-list');
  postList.innerHTML = '';

  posts.forEach((post) => {
    const postCard = `
      <div class="col">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">${post.name || '제목 없음'}</h5>
            <p class="card-text">${post.address || '내용 없음'}</p>
          <!-- <button class="btn btn-primary" onclick="goToPostDetail(${post.id})">상세 보기</button> -->
          </div>
        </div>
      </div>
    `;
    postList.innerHTML += postCard;
  });
}

// 게시글 상세 페이지로 이동하는 함수 (기존 레스토랑 상세 페이지로 이동)
function goToPostDetail(id) {
  window.location.href = `restaurant-detail.html?restaurantId=${id}`;
}

// 새 게시글 추가 함수 (기존 맛집 추가 함수 사용)
async function addPost() {
  const name = document.getElementById('post-title').value;
  const address = document.getElementById('post-content').value;

  const requestData = {
    name,
    address,
    menus: []
  };

  try {
    const response = await fetch(`${apiUrl}/restaurant`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requestData)
    });

    if (response.ok) {
      alert('게시글이 성공적으로 추가되었습니다!');
      fetchRestaurants(); // 새로고침하여 목록에 추가된 게시글 표시
      const modal = bootstrap.Modal.getInstance(document.getElementById('addPostModal'));
      modal.hide(); // 모달 닫기
      document.getElementById('addPostForm').reset(); // 폼 초기화
    } else {
      alert('게시글 추가에 실패했습니다.');
    }
  } catch (error) {
    console.error('Error adding post:', error);
    alert('서버에 문제가 발생했습니다.');
  }
}

// 게시글 상세 정보 불러오기 (기존 레스토랑 상세 정보)
async function fetchRestaurantDetail(id) {
  try {
    const response = await fetch(`${apiUrl}/restaurant/${id}`);
    const post = await response.json();
    displayPostDetail(post);
  } catch (error) {
    console.error('Error fetching post detail:', error);
  }
}

// 게시글 상세 정보를 HTML에 표시하는 함수
function displayPostDetail(post) {
  document.getElementById('post-title').textContent = post.name || '제목 없음';
  document.getElementById('post-content').textContent = post.address || '내용 없음';
}

// 댓글 목록 불러오기 (기존 리뷰 목록 사용)
async function fetchReviews(restaurantId) {
  try {
    const response = await fetch(`${apiUrl}/restaurant/${restaurantId}/reviews?timestamp=${new Date().getTime()}`);
    const comments = await response.json();
    console.log('Fetched comments:', comments); // 댓글 목록 확인용 로그
    displayComments(comments);
  } catch (error) {
    console.error('Error fetching comments:', error);
  }
}

// 댓글 목록을 HTML에 표시하는 함수
function displayComments(comments) {
  const commentList = document.getElementById('comment-list');
  commentList.innerHTML = comments.map(comment => `
    <div class="list-group-item">
      <p>${comment.content}</p>
      <p><strong>점수:</strong> ${comment.score}</p>
    </div>
  `).join('');
}

// 댓글 작성 함수 (기존 리뷰 작성 함수 사용)
async function submitReview() {
  const restaurantId = new URLSearchParams(window.location.search).get('restaurantId');
  const content = document.getElementById('comment-content').value;
  const score = 5; // 기본 평점 5로 설정

  if (!content) {
    alert("댓글 내용을 입력해주세요.");
    return;
  }

  try {
    const response = await fetch(`${apiUrl}/review`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        restaurantId: parseInt(restaurantId),
        content: content,
        score: parseFloat(score)
      })
    });

    if (response.ok) {
      alert('댓글이 성공적으로 추가되었습니다!');
      document.getElementById('comment-content').value = '';
      fetchReviews(restaurantId); // 새로운 댓글 목록 불러오기
    } else {
      alert('댓글 추가에 실패했습니다.');
    }
  } catch (error) {
    console.error('Error submitting comment:', error);
    alert('서버에 문제가 발생했습니다.');
  }
}
async function fetchReviews(restaurantId, offset = 0, limit = 10) {
  try {
    const response = await fetch(`${apiUrl}/restaurant/${restaurantId}/reviews?offset=${offset}&limit=${limit}`);
    const comments = await response.json();
    displayComments(comments);
  } catch (error) {
    console.error('Error fetching comments:', error);
  }
}

