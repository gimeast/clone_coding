export function setupPagination(paginationContainerId, dataFetchURI, search) {
    const paginationContainer = document.getElementById(paginationContainerId);
    const totalPages = Number(paginationContainer.getAttribute('data-total-pages'));
    const currentPage = Number(paginationContainer.getAttribute('data-current-page'));
    const size = Number(paginationContainer.getAttribute('data-size'));

    // 페이지네이션 렌더링 함수 호출
    renderPagination(currentPage, totalPages, dataFetchURI);

    function renderPagination(currentPage, totalPages, URI) {
        const paginationHTML = [];

        // 페이지네이션 범위 계산
        const startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
        const endPage = Math.min(startPage + 9, totalPages);

        // 'Prev' 버튼 생성
        if (startPage > 1) {
            paginationHTML.push(`<a href="#" data-page="${startPage - 1}" class="page-link">Prev</a>`);
        }

        // 페이지 번호 생성
        for (let page = startPage; page <= endPage; page++) {
            if (page === currentPage) {
                paginationHTML.push(`<a href="#" data-page="${page}" class="page-link active">${page}</a>`);
            } else {
                paginationHTML.push(`<a href="#" data-page="${page}" class="page-link">${page}</a>`);
            }
        }

        // 'Next' 버튼 생성
        if (endPage < totalPages) {
            paginationHTML.push(`<a href="#" data-page="${endPage + 1}" class="page-link">Next</a>`);
        }

        // DOM에 HTML 업데이트
        paginationContainer.innerHTML = paginationHTML.join('');
        setupPaginationListeners(URI);
    }

    function setupPaginationListeners(URI) {
        const pageLinks = document.querySelectorAll('.page-link');

        pageLinks.forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                const page = Number(link.getAttribute('data-page'));

                fetch(`${URI}?page=${page}&size=${size}&search=${search}`)
                    .then(response => response.text())
                    .then(data => {
                        updateContentAndPagination(data, URI);
                    })
                    .catch(error => console.error('Error fetching data:', error));
            });
        });
    }

    function updateContentAndPagination(data, URI) {
        const parser = new DOMParser();
        const doc = parser.parseFromString(data, 'text/html');

        const newContent = doc.querySelector('#data-container').innerHTML;
        const newPagination = doc.querySelector(`#${paginationContainerId}`);

        // 콘텐츠 업데이트
        document.getElementById('data-container').innerHTML = newContent;

        // 페이지네이션 데이터 업데이트
        const totalPages = Number(newPagination.getAttribute('data-total-pages'));
        const currentPage = Number(newPagination.getAttribute('data-current-page'));

        // 새로운 데이터를 기준으로 페이지네이션 다시 렌더링
        renderPagination(currentPage, totalPages, URI);
    }
}